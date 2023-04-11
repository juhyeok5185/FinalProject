-- 유저
CREATE TABLE users (
	username VARCHAR2(10 char) NOT NULL,
	name VARCHAR2(15 char) NOT NULL,
	tel VARCHAR2(20 char) NOT NULL,
	password VARCHAR2(50 char) NOT NULL,
	nation VARCHAR2(10 char) NOT NULL,
	email VARCHAR2(70 char) NOT NULL,
	birthDay DATE NOT NULL,
	personalId VARCHAR2(50 char) NOT NULL,
	loginFailCount number(1) DEFAULT '0',
	disabled number(1) DEFAULT '0',
	userLevel VARCHAR2(10 char) NOT NULL,
	role VARCHAR2(20 char) NOT NULL
);

-- 유저
ALTER TABLE users
	ADD CONSTRAINT PK_users -- 유저 기본키
	PRIMARY KEY (
	username -- 아이디
	);

-- 문의게시판
CREATE TABLE board (
	boardNo number(4) NOT NULL,
	username VARCHAR2(10 char) NOT NULL,
	replyNo number(1) NOT NULL,
	writeDay DATE NOT NULL,
	title VARCHAR2(20 char) NOT NULL,
	content VARCHAR2(500 char) NOT NULL
);

-- 문의게시판
ALTER TABLE board
	ADD CONSTRAINT PK_board -- 문의게시판 기본키
	PRIMARY KEY (
	boardNo -- 글번호
	);

-- 카드
CREATE TABLE card (
	cardNo VARCHAR2(20 char) NOT NULL,
	username VARCHAR2(10 char) NOT NULL,
	cardVendor VARCHAR2(10 char) NOT NULL,
	expiryDate DATE NOT NULL,
	cardPassword varchar2(10 char) NOT NULL,
	cardCVC VARCHAR2(3 char) NOT NULL
);

-- 카드
ALTER TABLE card
	ADD CONSTRAINT PK_card -- 카드 기본키
	PRIMARY KEY (
	cardNo -- 카드번호
	);

-- 예약
CREATE TABLE book (
	bookNo number(8) NOT NULL,
	username VARCHAR2(10 char) NOT NULL,
	totalCount number(1) NOT NULL
);

-- 예약
ALTER TABLE book
	ADD CONSTRAINT PK_book -- 예약 기본키
	PRIMARY KEY (
	bookNo,   -- 예약번호
	username  -- 아이디
	);

-- 방예약
CREATE TABLE roomBooking (
	bookNo number(8) NOT NULL,
	username VARCHAR2(10 char) NOT NULL,
	booker VARCHAR2(20 char) NOT NULL,
	bookDate DATE NOT NULL,
	checkIn DATE NOT NULL,
	checkOut DATE NOT NULL
);

-- 방예약
ALTER TABLE roomBooking
	ADD CONSTRAINT PK_roomBooking -- 방예약 기본키
	PRIMARY KEY (
	bookNo,   -- 예약번호
	username, -- 아이디
	booker    -- 예약자
	);

-- 식사예약
CREATE TABLE restaurant (
	resNo number(8) NOT NULL,
	username VARCHAR2(10 char) NOT NULL,
	resName VARCHAR2(20 char) NOT NULL,
	resDate DATE NULL
);

-- 식사예약
ALTER TABLE restaurant
	ADD CONSTRAINT PK_restaurant -- 식사예약 기본키
	PRIMARY KEY (
	resNo,    -- 예약번호
	username, -- 아이디
	resName   -- 예약자
	);

-- 방
CREATE TABLE room (
	booker VARCHAR2(20 char) NOT NULL,
	username VARCHAR2(10 char) NOT NULL,
	bookNo number(8) NULL,
	gradeName VARCHAR2(15 char) NOT NULL
);

-- 방
ALTER TABLE room
	ADD CONSTRAINT PK_room -- 방 기본키
	PRIMARY KEY (
	booker -- 예약자
	);

-- 방등급
CREATE TABLE roomGrade (
	gradeName VARCHAR2(15 char) NOT NULL,
	gradePrice number(8) NOT NULL
);

-- 방등급
ALTER TABLE roomGrade
	ADD CONSTRAINT PK_roomGrade -- 방등급 기본키
	PRIMARY KEY (
	gradeName -- 등급명
	);

-- 방상세
CREATE TABLE roomDetail (
	roomNo number(3) NOT NULL,
	roomStatus VARCHAR2(10 char) NOT NULL,
	booker VARCHAR2(20 char) NULL
);

-- 방상세
ALTER TABLE roomDetail
	ADD CONSTRAINT PK_roomDetail -- 방상세 기본키
	PRIMARY KEY (
	roomNo -- 방호수
	);

-- 조식
CREATE TABLE breakfast (
	resNo number(8) NULL,
	username VARCHAR2(10 char) NULL,
	breakfastTime DATE NULL,
	resName VARCHAR2(20 char) NULL
);

-- 석식
CREATE TABLE dinner (
	resNo number(8) NULL,
	username VARCHAR2(10 char) NULL,
	dinnerTime DATE NULL,
	resName VARCHAR2(20 char) NULL
);

-- 명품관
CREATE TABLE mall (
	mallName VARCHAR2(10 char) NOT NULL
);

-- 명품관
ALTER TABLE mall
	ADD CONSTRAINT PK_mall -- 명품관 기본키
	PRIMARY KEY (
	mallName -- 명품관이름
	);

-- 상품
CREATE TABLE item (
	itemNo number(8) NOT NULL,
	itemName VARCHAR2(20 char) NULL,
	itemPrice number(8) NULL,
	itemEA number(3) NULL,
	mallName VARCHAR2(10 char) NULL
);

-- 상품
ALTER TABLE item
	ADD CONSTRAINT PK_item -- 상품 기본키
	PRIMARY KEY (
	itemNo -- 상품코드
	);

-- 주문
CREATE TABLE orders (
	orderNo number(8) NOT NULL,
	username VARCHAR2(10 char) NULL,
	itemNo number(8) NULL
);

-- 주문
ALTER TABLE orders
	ADD CONSTRAINT PK_orders -- 주문 기본키
	PRIMARY KEY (
	orderNo -- 주문코드
	);

-- 관리자
CREATE TABLE admins (
	adminname VARCHAR(10) NULL,
	adminpassword VARCHAR(20) NULL
);

-- 주문상세
CREATE TABLE orderDetail (
	orderNo number(8) NULL,
	orderDate DATE NULL,
	contactDate DATE NULL,
	orderPrice number(8) NULL,
	orderEA number(3) NULL
);

-- 장바구니
CREATE TABLE cart (
	cartNo number(8) NOT NULL,
	itemNo number(8) NOT NULL,
	username VARCHAR2(10 char) NOT NULL
);

-- 장바구니
ALTER TABLE cart
	ADD CONSTRAINT PK_cart -- 장바구니 기본키
	PRIMARY KEY (
	cartNo,   -- 장바구니번호
	itemNo,   -- 상품코드
	username  -- 아이디
	);

-- 환불
CREATE TABLE refund (
	orderNo number(8) NOT NULL,
	refundNo number(8) NOT NULL,
	refundReason VARCHAR2(100 char) NULL
);

-- 환불
ALTER TABLE refund
	ADD CONSTRAINT PK_refund -- 환불 기본키
	PRIMARY KEY (
	orderNo,  -- 주문코드
	refundNo  -- 환불코드
	);

-- 예약환불
CREATE TABLE restaurantRefund (
	bookNo number(8) NOT NULL,
	username VARCHAR2(10 char) NOT NULL,
	refundno number(8) NOT NULL,
	refundreason VARCHAR2(100 char) NULL,
	refunddate DATE NULL
);

-- 예약환불
ALTER TABLE restaurantRefund
	ADD CONSTRAINT PK_restaurantRefund -- 예약환불 기본키
	PRIMARY KEY (
	bookNo,   -- 예약번호
	username, -- 아이디
	refundno  -- 환불코드
	);

-- 결제
CREATE TABLE payment (
	paycode number(8) NOT NULL,
	orderNo number(8) NOT NULL,
	payType VARCHAR2(10 char) NULL,
	payDate DATE NULL,
	payTotal number(8) NULL,
	deposit number(8) NULL,
	depositName VARCHAR(20 char) NULL,
	depositBank VARCHAR(20 char) NULL,
	bookNo number(8) NULL,
	username VARCHAR2(10 char) NULL
);

-- 결제
ALTER TABLE payment
	ADD CONSTRAINT PK_payment -- 결제 기본키
	PRIMARY KEY (
	paycode -- 결제코드
	);

-- 답글
CREATE TABLE reply (
	replyNo number(1) NOT NULL,
	replyContent VARCHAR2(50) NULL,
	replyWriteDay DATE NULL,
	replyWriter DATE NULL
);

-- 답글
ALTER TABLE reply
	ADD CONSTRAINT PK_reply -- 답글 기본키
	PRIMARY KEY (
	replyNo -- 답글번호
	);

-- 문의게시판
ALTER TABLE board
	ADD CONSTRAINT FK_users_TO_board -- 유저 -> 문의게시판
	FOREIGN KEY (
	username -- 아이디
	)
	REFERENCES users ( -- 유저
	username -- 아이디
	);

-- 문의게시판
ALTER TABLE board
	ADD CONSTRAINT FK_reply_TO_board -- 답글 -> 문의게시판
	FOREIGN KEY (
	replyNo -- 답글번호
	)
	REFERENCES reply ( -- 답글
	replyNo -- 답글번호
	);

-- 카드
ALTER TABLE card
	ADD CONSTRAINT FK_users_TO_card -- 유저 -> 카드
	FOREIGN KEY (
	username -- 아이디
	)
	REFERENCES users ( -- 유저
	username -- 아이디
	);

-- 예약
ALTER TABLE book
	ADD CONSTRAINT FK_users_TO_book -- 유저 -> 예약
	FOREIGN KEY (
	username -- 아이디
	)
	REFERENCES users ( -- 유저
	username -- 아이디
	);

-- 방예약
ALTER TABLE roomBooking
	ADD CONSTRAINT FK_book_TO_roomBooking -- 예약 -> 방예약
	FOREIGN KEY (
	bookNo,   -- 예약번호
	username  -- 아이디
	)
	REFERENCES book ( -- 예약
	bookNo,   -- 예약번호
	username  -- 아이디
	);

-- 식사예약
ALTER TABLE restaurant
	ADD CONSTRAINT FK_book_TO_restaurant -- 예약 -> 식사예약
	FOREIGN KEY (
	resNo,    -- 예약번호
	username  -- 아이디
	)
	REFERENCES book ( -- 예약
	bookNo,   -- 예약번호
	username  -- 아이디
	);

-- 방
ALTER TABLE room
	ADD CONSTRAINT FK_roomBooking_TO_room -- 방예약 -> 방
	FOREIGN KEY (
	bookNo,   -- 예약번호
	username, -- 아이디
	booker    -- 예약자
	)
	REFERENCES roomBooking ( -- 방예약
	bookNo,   -- 예약번호
	username, -- 아이디
	booker    -- 예약자
	);

-- 방
ALTER TABLE room
	ADD CONSTRAINT FK_roomGrade_TO_room -- 방등급 -> 방
	FOREIGN KEY (
	gradeName -- 등급명
	)
	REFERENCES roomGrade ( -- 방등급
	gradeName -- 등급명
	);

-- 방상세
ALTER TABLE roomDetail
	ADD CONSTRAINT FK_room_TO_roomDetail -- 방 -> 방상세
	FOREIGN KEY (
	booker -- 예약자
	)
	REFERENCES room ( -- 방
	booker -- 예약자
	);

-- 조식
ALTER TABLE breakfast
	ADD CONSTRAINT FK_restaurant_TO_breakfast -- 식사예약 -> 조식
	FOREIGN KEY (
	resNo,    -- 예약번호
	username, -- 아이디
	resName   -- 예약자
	)
	REFERENCES restaurant ( -- 식사예약
	resNo,    -- 예약번호
	username, -- 아이디
	resName   -- 예약자
	);

-- 석식
ALTER TABLE dinner
	ADD CONSTRAINT FK_restaurant_TO_dinner -- 식사예약 -> 석식
	FOREIGN KEY (
	resNo,    -- 예약번호
	username, -- 아이디
	resName   -- 예약자
	)
	REFERENCES restaurant ( -- 식사예약
	resNo,    -- 예약번호
	username, -- 아이디
	resName   -- 예약자
	);

-- 상품
ALTER TABLE item
	ADD CONSTRAINT FK_mall_TO_item -- 명품관 -> 상품
	FOREIGN KEY (
	mallName -- 명품관이름
	)
	REFERENCES mall ( -- 명품관
	mallName -- 명품관이름
	);

-- 주문
ALTER TABLE orders
	ADD CONSTRAINT FK_users_TO_orders -- 유저 -> 주문
	FOREIGN KEY (
	username -- 아이디
	)
	REFERENCES users ( -- 유저
	username -- 아이디
	);

-- 주문
ALTER TABLE orders
	ADD CONSTRAINT FK_item_TO_orders -- 상품 -> 주문
	FOREIGN KEY (
	itemNo -- 상품코드
	)
	REFERENCES item ( -- 상품
	itemNo -- 상품코드
	);

-- 주문상세
ALTER TABLE orderDetail
	ADD CONSTRAINT FK_orders_TO_orderDetail -- 주문 -> 주문상세
	FOREIGN KEY (
	orderNo -- 주문코드
	)
	REFERENCES orders ( -- 주문
	orderNo -- 주문코드
	);

-- 장바구니
ALTER TABLE cart
	ADD CONSTRAINT FK_item_TO_cart -- 상품 -> 장바구니
	FOREIGN KEY (
	itemNo -- 상품코드
	)
	REFERENCES item ( -- 상품
	itemNo -- 상품코드
	);

-- 장바구니
ALTER TABLE cart
	ADD CONSTRAINT FK_users_TO_cart -- 유저 -> 장바구니
	FOREIGN KEY (
	username -- 아이디
	)
	REFERENCES users ( -- 유저
	username -- 아이디
	);

-- 환불
ALTER TABLE refund
	ADD CONSTRAINT FK_orders_TO_refund -- 주문 -> 환불
	FOREIGN KEY (
	orderNo -- 주문코드
	)
	REFERENCES orders ( -- 주문
	orderNo -- 주문코드
	);

-- 예약환불
ALTER TABLE restaurantRefund
	ADD CONSTRAINT FK_book_TO_restaurantRefund -- 예약 -> 예약환불
	FOREIGN KEY (
	bookNo,   -- 예약번호
	username  -- 아이디
	)
	REFERENCES book ( -- 예약
	bookNo,   -- 예약번호
	username  -- 아이디
	);

-- 결제
ALTER TABLE payment
	ADD CONSTRAINT FK_orders_TO_payment -- 주문 -> 결제
	FOREIGN KEY (
	orderNo -- 주문코드
	)
	REFERENCES orders ( -- 주문
	orderNo -- 주문코드
	);

-- 결제
ALTER TABLE payment
	ADD CONSTRAINT FK_book_TO_payment -- 예약 -> 결제
	FOREIGN KEY (
	bookNo,   -- 예약번호
	username  -- 아이디
	)
	REFERENCES book ( -- 예약
	bookNo,   -- 예약번호
	username  -- 아이디
	);