--------------------------------------------------------
--  파일이 생성됨 - 수요일-12월-20-2017   
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
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (158,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/16','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/16 01:19:34.494000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/16','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (156,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/16','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/16 01:19:34.494000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/16','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (157,513143,'윤성우의 열혈 Java 프로그래밍','저장',111,null,null,null,null,null,null,null);
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (175,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/18','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/18 00:02:18.840000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/18','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (176,513143,'윤성우의 열혈 Java 프로그래밍','윤성우',-1,null,null,null,null,null,null,null);
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (177,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/18','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/18 00:17:27.246000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/18','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (178,513145,'윤성우의 열혈 C++ 프로그래밍','수정된 저자',35000,'오렌지미디어',to_date('17/12/20','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/20 02:42:20.071000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/20','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (179,513145,'윤성우의 열혈 C++ 프로그래밍','윤성우',35000,'오렌지미디어',to_date('17/12/18','RR/MM/DD'),'문적원2','2_yoon_sung_woo_c++.jpg','전양훈',to_timestamp('17/12/18 00:17:42.875000000','RR/MM/DD HH24:MI:SSXFF'),to_date('17/12/18','RR/MM/DD'));
Insert into HBM.BOOK (NO,SYMBOL,NAME,AUTHOR,PRICE,PUB,PUBDAY,LOC,IMGFILE,BRWER,BRWDAY,PERIOD) values (180,513143,'윤성우의 열혈 Java 프로그래밍','윤성우',-1,null,null,null,null,null,null,null);
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

  ALTER TABLE "HBM"."BOOK" MODIFY ("NO" NOT NULL ENABLE);
  ALTER TABLE "HBM"."BOOK" MODIFY ("SYMBOL" NOT NULL ENABLE);
  ALTER TABLE "HBM"."BOOK" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "HBM"."BOOK" MODIFY ("AUTHOR" NOT NULL ENABLE);
  ALTER TABLE "HBM"."BOOK" MODIFY ("PRICE" NOT NULL ENABLE);
  ALTER TABLE "HBM"."BOOK" ADD CONSTRAINT "BOOK_PK" PRIMARY KEY ("NO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
