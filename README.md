# 댕댕일기

## ✅ 프로젝트 소개

- 프로젝트명: 댕댕일기

- 프로젝트 기간: 2022.04.01 ~ 2024.02.01

- 개발 인원: 3명

- 기여도: 80%

## ✅ 서비스 소개

- 보호자-강아지 놀이 추천 서비스

- 주요 성과: DB 정규화를 통해 쿼리 속도 약 3.8배(74%) 개선, 챌린지 등록을 자동화하여 관리 효율 극대화

## ✅ 주요 기능

### 로그인 기능 ( 1 / 5 )

<img width="375" alt="Untitled-2" src="https://github.com/user-attachments/assets/132b2a80-0cbd-42c7-97e9-ec4a83bfdf95" />


- OAuth2 프로토콜을 활용한 소셜 로그인 구현
- Apple, Kakao 로그인

---

### 반려견 등록 기능 ( 2 / 5 )

<img width="375" alt="Untitled-5" src="https://github.com/user-attachments/assets/207db8a7-4747-4b67-b1b9-6afcab186a5c" /> <img width="375" alt="Untitled-4" src="https://github.com/user-attachments/assets/82d042f4-12dd-442b-8c23-27324aae0ffc" /> <img width="375" alt="Untitled-3" src="https://github.com/user-attachments/assets/feee1155-f5ca-4ab3-b651-10f221a8eca4" />

견종 검색 기능을 통해 쉽게 견종을 찾고 반려견 정보를 등록할 수 있습니다.

---

### 챌린지 추천 및 일기 작성 ( 3 / 5 )

<img width="375" alt="Untitled-7" src="https://github.com/user-attachments/assets/864c3878-b4c0-4b61-b2b4-206555896a9e" /> <img width="375" alt="Untitled-6" src="https://github.com/user-attachments/assets/f7ceea25-b511-493c-a3c1-0bdf1c6577e4" />

매일 오전 10시 챌린지 추천 받고 일기를 작성할 수 있습니다.

---

### 내가 쓴 일기 확인 ( 4 / 5 )

<img width="375" alt="Untitled-8" src="https://github.com/user-attachments/assets/fb4943a1-7f4d-4435-a85c-4ab817533e82" /> <img width="375" alt="Untitled-9" src="https://github.com/user-attachments/assets/559fa1f0-4596-4f08-b550-c77bd2d30321" />

내가 쓴 일기를 확인하고 표지 및 일기를 수정할 수 있습니다.

---

### 다른 일기 둘러보기 ( 5 / 5 )

<img width="375" alt="Untitled-10" src="https://github.com/user-attachments/assets/e74d9b4a-5dd6-4519-9647-426d6819c971" /> <img width="375" alt="Untitled-11" src="https://github.com/user-attachments/assets/ba14e9a9-a340-446f-bf09-1177ed2baa43" />


다른 보호자가 쓴 일기를 둘러보고 검색할 수 있습니다.

검색은 해시태그, 계정, 견종을 포함하여 통합 검색이 가능합니다.

### APP

![Static Badge](https://img.shields.io/badge/flutter-%2302569B?style=flat-square&logo=flutter&logoColor=%23FFFFFF&color=%2302569B) ![Static Badge](https://img.shields.io/badge/getx-%238A2BE2?style=flat-square&logo=getx&logoColor=%23FFFFFF&color=%238A2BE2)

### BE

![Static Badge](https://img.shields.io/badge/springboot-%236DB33F?style=flat-square&logo=springboot&logoColor=%23FFFFFF&color=%236DB33F) ![Static Badge](https://img.shields.io/badge/java-%23139BB4?style=flat-square&color=%23139BB4)

### DB
![Static Badge](https://img.shields.io/badge/MySQL-%234479A1?style=flat-square&logo=mysql&logoColor=%23FFFFFF&color=%234479A1)

### Dev-Ops
![Static Badge](https://img.shields.io/badge/amazonec2-%23FF9900?style=flat-square&logo=amazonec2&logoColor=%23FFFFFF&color=%23FF9900) ![Static Badge](https://img.shields.io/badge/amazons3-%23569A31?style=flat-square&logo=amazons3&logoColor=%23FFFFFF&color=%23569A31) ![Static Badge](https://img.shields.io/badge/amazonrds-%23527FFF?style=flat-square&logo=amazonrds&logoColor=%23FFFFFF&color=%23527FFF)

## ✅ 시스템 아키텍처

![Dang Diary](https://github.com/user-attachments/assets/787c3b75-c3d7-4bd4-92ab-9c6d6edc8a33)

## ✅ ERD

![ERD_sample drawio](https://github.com/user-attachments/assets/c0f8bcdd-0dbb-48cc-8123-9d9c75c8fb18)

## ✅ 프로젝트 회고

1. DB 정규화 및 성능 개선

비정규화된 데이터 구조로 인해 중복 데이터 증가와 성능 저하 문제가 발생했습니다. 이를 해결하기 위해 3차 정규화를 적용하고, 자주 사용하는 검색 조건에 인덱스를 추가했으며, 복잡한 쿼리는 View로 대체하였습니다. 그 결과, 쿼리 실행 속도가 약 74% 향상되고 데이터 일관성과 저장 효율성도 개선되었습니다. 이를 통해 데이터베이스 구조가 시스템 성능과 유지보수에 미치는 영향을 깊이 깨닫게 되었습니다.

2. 데이터 입력 자동화

팀원들이 데이터를 수작업으로 입력하며 많은 시간이 소요되고 오류가 발생하는 문제가 있었습니다. 이를 해결하기 위해 구글 시트 API를 활용하여 자동으로 데이터베이스와 연동하는 시스템을 구축하였고, Spring의 @Scheduled 어노테이션을 적용해 일정 주기마다 데이터가 동기화되도록 구현하였습니다. 그 결과, 데이터 입력 시간이 90% 단축되었으며, 오류 감소로 신뢰성이 향상되었습니다. 이 경험을 통해 반복 작업을 최소화하고 업무 효율성을 높이는 것이 중요함을 실감하였습니다.
