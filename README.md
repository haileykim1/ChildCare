# ChildCare
Cloud Web Service Final Project
어린이 보육관리 Android 어플리케이션

### 개발 환경
Microsoft Windows 10 home 64-bit

### 개발 언어
Kotlin

### 서비스 주요 기능
- 어린이 보육시설 검색
- 어린이 보호/놀이 시설 검색
- 어린이 예방접종 기관 검색
- 어린이 위치 등록 / 조회

### 사용한 공공 데이터
- 경기도 데이터드림 : 어린이집 현황(개방표준)
- 공공데이터포털 : 전국어린이보호구역표준데이터
- 공공데이터포털 : 전국어린이놀이시설정보서비스
- 경기도 데이터드림 : 어린이 국가예방접종 지정의료기관

### AWS Lambda
어린이 보육시설 검색
#### 함수 이름
 : ccFacilityApi
##### 런타임 언어
 : Node.js 12.x
##### Layers
 : require, xml-js 모듈 포함한 node_modules
##### 호출 예시
 : https://1qwds6aaii.execute-api.us-east-1.amazonaws.com/ccFacility?pCity=성남시
##### 반환 데이터 형태
 : JSON

어린이 보호/놀이 시설 검색
#### 함수 이름
 : ccProtectApi, ccPlayAPi
##### 런타임 언어
 : Node.js 12.x
##### Layers
 : require, xml-js 모듈 포함한 node_modules
##### 호출 예시
 : https://1qwds6aaii.execute-api.us-east-1.amazonaws.com/ccProtection?pName=큰빛어린이집 
 : https://1qwds6aaii.execute-api.us-east-1.amazonaws.com/ccPlay?pName=초록공원 놀이터
##### 반환 데이터 형태
 : JSON
 
어린이 예방접종 기관 검색
#### 함수 이름
 : ccVaccinationApi
##### 런타임 언어
 : Node.js 12.x
##### Layers
 : require, xml-js 모듈 포함한 node_modules
##### 호출 예시
 : https://1qwds6aaii.execute-api.us-east-1.amazonaws.com/ccVaccination?pCity=성남시
##### 반환 데이터 형태
 : JSON
 
 ### 어린이 위치 등록 / 조회 기능
 - 지도 뷰 출력 구현 완료
 - 마킹 기능 구현 완료
 - MongoDB통한 위치 등록 / 조회 기능 구현 예정
