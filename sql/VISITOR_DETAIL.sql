--------------------------------------------------------
--  DDL for Table VISITOR_DETAIL
--------------------------------------------------------

  CREATE TABLE "HBM"."VISITOR_DETAIL" 
   (	"VNO" NUMBER, 
	"IMGFILENAME" VARCHAR2(60 BYTE), 
	"IMGFILECONT" BLOB, 
	"HPHONE" VARCHAR2(10 BYTE), 
	"ADDR" VARCHAR2(100 BYTE), 
	"COMP" VARCHAR2(60 BYTE), 
	"POS" VARCHAR2(40 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" 
 LOB ("IMGFILECONT") STORE AS BASICFILE (
  TABLESPACE "USERS" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;
