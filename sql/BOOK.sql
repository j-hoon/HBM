--------------------------------------------------------
--  파일이 생성됨 - 화요일-12월-26-2017   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table BOOK
--------------------------------------------------------

  CREATE TABLE "HBM"."BOOK" 
   (	"NO" NUMBER, 
	"SYMBOL" NUMBER, 
	"NAME" VARCHAR2(100 BYTE), 
	"AUTHOR" VARCHAR2(40 BYTE), 
	"PRICE" NUMBER, 
	"PUB" VARCHAR2(40 BYTE), 
	"PUBDAY" DATE, 
	"LOC" VARCHAR2(40 BYTE), 
	"IMGFILE" VARCHAR2(40 BYTE), 
	"BRWER" VARCHAR2(20 BYTE), 
	"BRWDAY" TIMESTAMP (6), 
	"PERIOD" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into HBM.BOOK
SET DEFINE OFF;
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (195,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 19:17:35.066000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (196,513143,'윤성우의 열혈 Java 프로그래밍','윤성우',-1,null,null,null,null,null,null,null);
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (203,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 19:50:11.575000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (204,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 19:51:12.601000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (199,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 19:48:39.677000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (154,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/16','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('16/12/31 23:59:59.999000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/16','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (157,513143,'윤성우의 열혈 Java 프로그래밍','저장',111,null,null,null,null,null,null,null);
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (175,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/18','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/01 00:00:00.000000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/18','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (176,513143,'윤성우의 열혈 Java 프로그래밍','윤성우',-1,null,null,null,null,null,null,null);
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (197,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 19:47:18.848000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (223,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 22:51:37.119000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (179,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/18','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/18 00:17:42.875000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/18','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (180,513143,'윤성우의 열혈 Java 프로그래밍','윤성우',-1,null,null,null,null,null,null,null);
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (198,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 19:48:14.000000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (200,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 19:48:48.326000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (201,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 19:49:12.742000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (202,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 19:49:26.455000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (205,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 19:52:07.509000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (206,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 19:54:21.285000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (207,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 19:59:00.620000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (208,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 22:37:52.700000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (209,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 22:39:31.121000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (210,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 22:42:13.401000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (211,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 22:43:09.905000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (212,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 22:44:11.622000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (213,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 22:44:50.008000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (214,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 22:45:46.027000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (215,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 22:46:22.809000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (216,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 22:47:25.998000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (217,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 22:47:53.799000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (218,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 22:48:20.820000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (219,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 22:49:18.781000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (220,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 22:49:33.188000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (221,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 22:49:39.585000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (222,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 22:51:09.215000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (224,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 22:52:02.454000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (225,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 22:53:31.562000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (226,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 22:53:56.888000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (227,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/31 23:59:59.999000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (228,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('18/01/01 00:00:00.000000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (156,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/16','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/11/30 23:59:59.999000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/16','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (155,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/16','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/01/01 00:00:00.000000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/16','RR/MM/DD'));
--------------------------------------------------------
--  DDL for Index BOOK_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "HBM"."BOOK_PK" ON "HBM"."BOOK" ("NO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table BOOK
--------------------------------------------------------

  ALTER TABLE "HBM"."BOOK" ADD CONSTRAINT "BOOK_PK" PRIMARY KEY ("NO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "HBM"."BOOK" MODIFY ("PRICE" NOT NULL ENABLE);
  ALTER TABLE "HBM"."BOOK" MODIFY ("AUTHOR" NOT NULL ENABLE);
  ALTER TABLE "HBM"."BOOK" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "HBM"."BOOK" MODIFY ("SYMBOL" NOT NULL ENABLE);
  ALTER TABLE "HBM"."BOOK" MODIFY ("NO" NOT NULL ENABLE);
