package com.dangdiary.api.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangdiary.api.dao.LoginDAO;
import com.dangdiary.api.dto.login.KakaoLoginDTO;
import com.dangdiary.api.dto.login.LoginResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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

                KakaoLoginDTO kakaoLoginDTO = getJSONFromString(result);
                kakaoLoginDTO.setRefreshToken(refreshToken);

                long socialId = kakaoLoginDTO.getId();

                if (loginDAO.existId(socialId) == 1) {
                    int userId = loginDAO.getUserId(socialId);
                    kakaoLoginDTO.setUserId(userId);
                    loginDAO.updateUserWithKakao(kakaoLoginDTO);
                } else {
                    loginDAO.addUserWithKakao(kakaoLoginDTO);
                }

                loginResponse = loginDAO.getLoginResponseDTO(socialId);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loginResponse;
    }

    @Override
    public int autoLogin(int userId) {
        int responseCode = 401;
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
                        updatedRefreshToken = node.get("refresh_token").asText();
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
            //애플 로그인
        }

        return responseCode;
    }

    KakaoLoginDTO getJSONFromString(String str) {
        KakaoLoginDTO kakaoLoginDTO = new KakaoLoginDTO();
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode node = mapper.readTree(str);
            kakaoLoginDTO.setId(node.get("id").asLong());
            kakaoLoginDTO.setNickname(node.get("properties").get("nickname").asText());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return kakaoLoginDTO;
    }
}
