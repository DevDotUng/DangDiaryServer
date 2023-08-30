package com.dangdiary.api.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Base64.Decoder;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangdiary.api.dao.LoginDAO;
import com.dangdiary.api.dto.login.DogInfoDTO;
import com.dangdiary.api.dto.login.LoginDTO;
import com.dangdiary.api.dto.login.LoginResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.ECDSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LoginServiceImp implements LoginService {

    @Autowired
    LoginDAO loginDAO;

    @Transactional
    public LoginResponseDTO kakaoLogin(String accessToken, String refreshToken) {
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        LoginResponseDTO loginResponse = new LoginResponseDTO();
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);
            
            int responseCode = conn.getResponseCode();
            
            if (responseCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
                StringBuilder sb = new StringBuilder();
                String result = "";
                String line = "";
                
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                result = sb.toString();
                br.close();

                LoginDTO loginDTO = getJSONFromString(result);
                loginDTO.setAccessToken(accessToken);
                loginDTO.setRefreshToken(refreshToken);

                String socialId = loginDTO.getId();

                if (loginDAO.existId(socialId) == 1) {
                    int userId = loginDAO.getUserId(socialId);
                    loginDTO.setUserId(userId);
                    loginDAO.updateUserWithKakao(loginDTO);
                } else {
                    loginDAO.addUserWithKakao(loginDTO);
                }

                loginResponse = loginDAO.getLoginResponseDTO(socialId);

                int userId = loginDAO.getUserId(socialId);
                if (loginDAO.existDog(userId) == 1) {
                    loginResponse.setExistDog(true);
                } else {
                    loginResponse.setExistDog(false);
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loginResponse;
    }

    @Override
    public LoginResponseDTO appleLogin(String userIdentifier, String authorizationCode, String identityToken, String familyName, String givenName) {

        String clientId = "com.uniqueone.dangdiary";
        String reqURL = "https://appleid.apple.com/auth/token";

        String teamId = "R2M3DTM6K7";
        String keyId = "HD987X6833";
        String keyPath = "apple/AuthKey_HD987X6833.p8";
        String authURL = "https://appleid.apple.com";

        String clientSecret = createClientSecret(teamId, clientId, keyId, keyPath, authURL);

        int responseCode = 401;
        LoginResponseDTO loginResponse = new LoginResponseDTO();
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            StringBuilder usb = new StringBuilder();
            usb.append("client_id").append("=").append(clientId).append("&");
            usb.append("client_secret").append("=").append(clientSecret).append("&");
            usb.append("code").append("=").append(authorizationCode).append("&");
            usb.append("grant_type").append("=").append("authorization_code");

            PrintWriter pw = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            pw.write(usb.toString());
            pw.flush();

            responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                
                StringBuilder sb = new StringBuilder();
                String result = "";
                String line = "";
                
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                result = sb.toString();

                String updatedRefreshToken = "";
                String idToken = "";
                ObjectMapper mapper = new ObjectMapper();
                try {
                    JsonNode node = mapper.readTree(result);
                    updatedRefreshToken = node.get("refresh_token").asText();
                    idToken = node.get("id_token").asText();
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

                Decoder decoder = Base64.getUrlDecoder();
                String payload = idToken.split("[.]", 3)[1];
                String decodedPayload = new String(decoder.decode(payload));

                String socialId = getSocialIdApple(decodedPayload);

                String name = null;
                if (!familyName.equals("null")) {
                    name = familyName;
                }
                if (!givenName.equals("null")) {
                    name += givenName;
                }

                LoginDTO loginDTO = new LoginDTO(0, socialId, name, null, updatedRefreshToken);

                if (loginDAO.existId(socialId) == 1) {
                    int userId = loginDAO.getUserId(socialId);
                    loginDTO.setUserId(userId);
                    loginDAO.updateUserWithApple(loginDTO);
                } else {
                    loginDAO.addUserWithApple(loginDTO);
                }

                loginResponse = loginDAO.getLoginResponseDTO(socialId);

                int userId = loginDAO.getUserId(socialId);
                if (loginDAO.existDog(userId) == 1) {
                    loginResponse.setExistDog(true);
                } else {
                    loginResponse.setExistDog(false);
                }

                br.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return loginResponse;
    }

    @Override
    public int autoLogin(int userId) {
        int responseCode = 401;
        String loginDate = loginDAO.getLoginDate(userId);

        if (isExpire(loginDate)) {
            return responseCode;
        }

        if (loginDAO.getLoginType(userId).equals("kakao")) {

            String apiKey = "c62730797df0ff3fcc1f7303775a846d";
            String reqURL = "https://kauth.kakao.com/oauth/token";
            String refreshToken = loginDAO.getRefreshToken(userId);

            try {
                URL url = new URL(reqURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                StringBuilder usb = new StringBuilder();
                usb.append("grant_type=refresh_token").append("&");
                usb.append("client_id").append("=").append(apiKey).append("&");
                usb.append("refresh_token").append("=").append(refreshToken);

                PrintWriter pw = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
                pw.write(usb.toString());
                pw.flush();

                responseCode = conn.getResponseCode();

                if (responseCode == 200) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    
                    StringBuilder sb = new StringBuilder();
                    String result = "";
                    String line = "";
                    
                    while ((line = br.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    result = sb.toString();

                    String updatedAccessToken = "";
                    String updatedRefreshToken = "";
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        JsonNode node = mapper.readTree(result);
                        updatedAccessToken = node.get("access_token").asText();
                        if (result.contains("refresh_token")) {
                            updatedRefreshToken = node.get("refresh_token").asText();
                        } else {
                            updatedRefreshToken = refreshToken;
                        }
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }

                    kakaoLogin(updatedAccessToken, updatedRefreshToken);

                    br.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (loginDAO.getLoginType(userId).equals("apple")) {
            String refreshToken = loginDAO.getRefreshToken(userId);

            String clientId = "com.uniqueone.dangdiary";
            String reqURL = "https://appleid.apple.com/auth/token";

            String teamId = "R2M3DTM6K7";
            String keyId = "HD987X6833";
            String keyPath = "apple/AuthKey_HD987X6833.p8";
            String authURL = "https://appleid.apple.com";

            String clientSecret = createClientSecret(teamId, clientId, keyId, keyPath, authURL);

            try {
                URL url = new URL(reqURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
                conn.setDoInput(true);
                conn.setDoOutput(true);
    
                StringBuilder usb = new StringBuilder();
                usb.append("client_id").append("=").append(clientId).append("&");
                usb.append("client_secret").append("=").append(clientSecret).append("&");
                usb.append("grant_type").append("=").append("refresh_token").append("&");
                usb.append("refresh_token").append("=").append(refreshToken);
    
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
                pw.write(usb.toString());
                pw.flush();
    
                responseCode = conn.getResponseCode();

                if (responseCode == 200) {
                    loginDAO.updateLoginDateNow(userId);
                }
    
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (responseCode == 200 && loginDAO.existDog(userId) == 0) {
            responseCode = 300;
        }

        return responseCode;
    }

    @Override
    public void logout(int userId) {
        if (loginDAO.getLoginType(userId).equals("kakao")) {

            String apiKey = "c62730797df0ff3fcc1f7303775a846d";
            String reqURL = "https://kapi.kakao.com/v1/user/logout";
            String accessToken = loginDAO.getAccessToken(userId);

            try {
                URL url = new URL(reqURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
                conn.setRequestProperty("Authorization", "Bearer " + accessToken);
                conn.setDoInput(true);
                conn.setDoOutput(true);

                int responseCode = conn.getResponseCode();

                if (responseCode != 200) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (loginDAO.getLoginType(userId).equals("apple")) {
            String refreshToken = loginDAO.getRefreshToken(userId);

            String clientId = "com.uniqueone.dangdiary";
            String reqURL = "https://appleid.apple.com/auth/revoke";

            String teamId = "R2M3DTM6K7";
            String keyId = "HD987X6833";
            String keyPath = "apple/AuthKey_HD987X6833.p8";
            String authURL = "https://appleid.apple.com";

            String clientSecret = createClientSecret(teamId, clientId, keyId, keyPath, authURL);

            try {
                URL url = new URL(reqURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                StringBuilder usb = new StringBuilder();
                usb.append("client_id").append("=").append(clientId).append("&");
                usb.append("client_secret").append("=").append(clientSecret).append("&");
                usb.append("token").append("=").append(refreshToken).append("&");
                usb.append("token_type_hint").append("=").append("refresh_token");

                PrintWriter pw = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
                pw.write(usb.toString());
                pw.flush();

                int responseCode = conn.getResponseCode();

                if (responseCode != 200) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Transactional
    public void deleteAccount(int userId) {
        String loginType = loginDAO.getLoginType(userId);
        String accessToken = loginDAO.getAccessToken(userId);
        String refreshToken = loginDAO.getRefreshToken(userId);

        loginDAO.deleteDiaryImages(userId);
        loginDAO.deleteTags(userId);
        loginDAO.deleteDiaryAdmin(userId);
        loginDAO.deleteDiaryCovers(userId);
        loginDAO.deleteDogs(userId);
        loginDAO.deleteFAQLikes(userId);
        loginDAO.deleteInquiries(userId);
        loginDAO.deleteLikes(userId);
        loginDAO.deleteReport(userId);
        loginDAO.deleteUserChallenges(userId);
        loginDAO.deleteUsers(userId);
        loginDAO.deleteDiaries(userId);

        if (loginType.equals("kakao")) {

            String apiKey = "c62730797df0ff3fcc1f7303775a846d";
            String reqURL = "https://kapi.kakao.com/v1/user/unlink";

            try {
                URL url = new URL(reqURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
                conn.setRequestProperty("Authorization", "Bearer " + accessToken);
                conn.setDoInput(true);
                conn.setDoOutput(true);

                int responseCode = conn.getResponseCode();

                if (responseCode != 200) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (loginType.equals("apple")) {

            String clientId = "com.uniqueone.dangdiary";
            String reqURL = "https://appleid.apple.com/auth/revoke";

            String teamId = "R2M3DTM6K7";
            String keyId = "HD987X6833";
            String keyPath = "apple/AuthKey_HD987X6833.p8";
            String authURL = "https://appleid.apple.com";

            String clientSecret = createClientSecret(teamId, clientId, keyId, keyPath, authURL);

            try {
                URL url = new URL(reqURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                StringBuilder usb = new StringBuilder();
                usb.append("client_id").append("=").append(clientId).append("&");
                usb.append("client_secret").append("=").append(clientSecret).append("&");
                usb.append("token").append("=").append(refreshToken).append("&");
                usb.append("token_type_hint").append("=").append("refresh_token");

                PrintWriter pw = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
                pw.write(usb.toString());
                pw.flush();

                int responseCode = conn.getResponseCode();

                if (responseCode != 200) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    boolean isExpire(String loginDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Calendar calendar = Calendar.getInstance();

            String now = format.format(calendar.getTime());

            Date nowDate = format.parse(now);
            Date birthDate = format.parse(loginDate);

            int date = (int)((nowDate.getTime() - birthDate.getTime()) / (24*60*60*1000) + 1);

            if (date <= 14) {
                return false;
            } else {
                return true;
            }
        } catch (ParseException e) {
            return true;
        }
    }

    LoginDTO getJSONFromString(String str) {
        LoginDTO loginDTO = new LoginDTO();
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode node = mapper.readTree(str);
            loginDTO.setId(node.get("id").asText());
            loginDTO.setName(node.get("properties").get("nickname").asText());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return loginDTO;
    }

    @Transactional
    public DogInfoDTO registerDogInfo(DogInfoDTO dogInfo) {
        loginDAO.registerDogInfo(dogInfo);
        loginDAO.registerNickname(dogInfo);
        DogInfoDTO dogInfoResponse = loginDAO.getDogInfo(dogInfo.getUserId());

        return dogInfoResponse;
    }
    
    public String createClientSecret(String teamId, String clientId, String keyId, String keyPath, String authUrl) {

        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.ES256).keyID(keyId).build();
        JWTClaimsSet claimsSet = new JWTClaimsSet();
        Date now = new Date();

        claimsSet.setIssuer(teamId);
        claimsSet.setIssueTime(now);
        claimsSet.setExpirationTime(new Date(now.getTime() + 3600000));
        claimsSet.setAudience(authUrl);
        claimsSet.setSubject(clientId);

        SignedJWT jwt = new SignedJWT(header, claimsSet);

        try {
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(readPrivateKey(keyPath));
            KeyFactory kf = KeyFactory.getInstance("EC");
            ECPrivateKey ecPrivateKey = (ECPrivateKey) kf.generatePrivate(spec);
            JWSSigner jwsSigner = new ECDSASigner(ecPrivateKey.getS());

            jwt.sign(jwsSigner);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jwt.serialize();
    }

    private byte[] readPrivateKey(String keyPath) {

        Resource resource = new ClassPathResource(keyPath);
        byte[] content = null;

        try (FileReader keyReader = new FileReader(resource.getFile());
             PemReader pemReader = new PemReader(keyReader)) {
            {
                PemObject pemObject = pemReader.readPemObject();
                content = pemObject.getContent();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    String getSocialIdApple(String payload) {
        String socialId = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
			Map<String, Object> returnMap = mapper.readValue(payload, Map.class);
            return (String) returnMap.get("sub");
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

        return socialId;
    }
}
