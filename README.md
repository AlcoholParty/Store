# Store
Store구현중

## 결제 API를 이용해서 Store 페이지 구현
### 아직 결제 완료 이후 데이터베이스에 저장 까지 구현은 하지않았음 -> 나중에 추가 예정
### URL 매핑도 나중에 좀더 깔끔하게 변경 예정
### 기존에 사용했던 결제 API를 이용해서 구현
### 결제 페이지는 팝업을 이용해서 구현함
### CSS까지 넣어서 작업 했지만 나중에 조금더 수정할 예정 -> (따로 CSS파일 분리예정)
### JavaScript 부분도 따로 파일로 분리해서 사용할 예정

## 사용된 데이터베이스
### MySQL - soju

	CREATE DATABASE soju;
	USE soju;

### 사용된 테이블 : Member, Pay, Store
	
	CREATE TABLE Member(
		emailId VARCHAR(50) PRIMARY KEY,
		pwd VARCHAR(255) NOT NULL,
		name VARCHAR(10) NOT NULL,
		nickName VARCHAR(20) NOT NULL,
		birthDay DATE NOT NULL,
		gender VARCHAR(1) NOT NULL,
		phoneNumber VARCHAR(15) NOT NULL,
		address VARCHAR(100) NOT NULL,
		roleName VARCHAR(100) NOT NULL
	);
	
	CREATE TABLE Pay(
		impUid VARCHAR(200) PRIMARY KEY,
		merchantUid VARCHAR(200),
		PGname VARCHAR(50), 
		payMethod VARCHAR(300),
		itemName VARCHAR(50),
		price INT,
		buyerEmail VARCHAR(50),
		buyerName VARCHAR(20),
		buyerTel VARCHAR(20),
		buyerAddress VARCHAR(50),
		buyerPostNum VARCHAR(50),
		itemCount INT
	);
	
	CREATE TABLE Store(
		storeIdx INT PRIMARY KEY,
		goods VARCHAR(50),
		category VARCHAR(50),
		price INT NOT NULL,
		introduce VARCHAR(500),
		stock INT NOT NULL,
		goodsLike INT,
		itemName VARCHAR(50) NOT NULL
	);
	
## 변경된 사항(02/22)
### pay 테이블에 환불결과를 저장할 항목 추가
	CREATE TABLE Pay(
		impUid VARCHAR(200) PRIMARY KEY,
		merchantUid VARCHAR(200),
		PGname VARCHAR(50), 
		payMethod VARCHAR(300),
		itemName VARCHAR(50),
		price INT,
		buyerEmail VARCHAR(50),
		buyerName VARCHAR(20),
		buyerTel VARCHAR(20),
		buyerAddress VARCHAR(50),
		buyerPostNum VARCHAR(50),
		itemCount INT,
		isPaid INT
	);

### 결제가 완료 되었는지 아임포트쪽 RestAPI를 사용해서 확인
### 환불 신청시 isPaid를 1로 변경 해서 환불버튼을 환불처리중 이라는 메세지로 변경
### 환불 완료시 isPaid를 2로 변경 해서 메세지를 환불처리완료 로 변경
