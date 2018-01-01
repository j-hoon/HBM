SELECT * FROM BOOK WHERE PRICE LIKE '' ORDER BY NO ASC;

SELECT * FROM BOOK WHERE BRWDAY LIKE '%17/12/20 22:49%' ORDER BY NO ASC;

----- Single Condition ----------------------------------------------------------------------------------
-- int, Integer (INT) ----------------------------------------------------------
SELECT * FROM BOOK WHERE PRICE = 35000 ORDER BY NO ASC;
SELECT * FROM BOOK WHERE PRICE = 111 ORDER BY NO ASC;
SELECT * FROM BOOK WHERE PRICE = -1 ORDER BY NO ASC;

SELECT * FROM BOOK WHERE PRICE > -1 ORDER BY NO ASC;
SELECT * FROM BOOK WHERE PRICE >= -1 ORDER BY NO ASC;

SELECT * FROM BOOK WHERE PRICE < 34999 ORDER BY NO ASC;
SELECT * FROM BOOK WHERE PRICE <= 35000 ORDER BY NO ASC;

SELECT * FROM BOOK WHERE 110 < PRICE AND PRICE < 35001 ORDER BY NO ASC;
SELECT * FROM BOOK WHERE -1 <= PRICE AND PRICE <= 34999 ORDER BY NO ASC;
--------------------------------------------------------------------------------

-- String (VARCHAR) ------------------------------------------------------------
SELECT * FROM BOOK WHERE NAME = '윤성우의 열혈 Java 프로그래밍' ORDER BY NO ASC;
SELECT * FROM BOOK WHERE NAME LIKE 'Java' ORDER BY NO ASC;
SELECT * FROM BOOK WHERE NAME LIKE '%Java%' ORDER BY NO ASC;
SELECT * FROM BOOK WHERE NAME LIKE '%윤성우%' ORDER BY NO ASC;
--------------------------------------------------------------------------------

-- LocalDate (Date) ------------------------------------------------------------
SELECT * FROM BOOK WHERE PERIOD = '2017-12-16' ORDER BY NO ASC;

SELECT LAST_DAY('2017-02-02') FROM DUAL;  -- 해당 월의 마지막 날짜
SELECT TO_CHAR(TO_DATE('2017-02-02'), 'YYYY') || '-12-31' AS LAST_DAY_OF_YEAR FROM DUAL;  -- 해당 년의 마지막 날짜

SELECT * FROM BOOK WHERE PERIOD BETWEEN '2017-12-01' AND LAST_DAY('2017-12-01') ORDER BY NO ASC;  -- 해당 월의 모든 데이터 (쿼리 기반으로 수정됨)
SELECT * FROM BOOK WHERE PERIOD BETWEEN '2017-01-01' AND TO_CHAR(TO_DATE('2017-01-01'), 'YYYY') || '-12-31' ORDER BY NO ASC;  -- 해당 년의 모든 데이터 (쿼리 기반으로 수정됨)


SELECT * FROM BOOK WHERE PERIOD BETWEEN '2017-12-01' AND '2017-12-31' ORDER BY NO ASC;  -- 해당 월의 모든 데이터
SELECT * FROM BOOK WHERE PERIOD BETWEEN '2017-12-01' AND LAST_DAY('2017-12-01') ORDER BY NO ASC;  -- 해당 월의 모든 데이터
SELECT * FROM BOOK WHERE PERIOD BETWEEN '2017-01-01' AND '2017-12-31' ORDER BY NO ASC;  -- 해당 년의 모든 데이터
SELECT * FROM BOOK WHERE PERIOD BETWEEN '2017-12-16' AND '2017-12-18' ORDER BY NO ASC;  -- 해당 기간의 모든 데이터

SELECT * FROM BOOK WHERE PERIOD <= '2017-12-18' ORDER BY NO ASC;  -- 해당 일 포함 이전 검색 (~까지)
SELECT * FROM BOOK WHERE PERIOD >= '2017-12-16' ORDER BY NO ASC;  -- 해당 일 포함 이후 검색 (~부터)
--------------------------------------------------------------------------------

-- LocalDateTime (TIMESTAMP) ---------------------------------------------------
SELECT * FROM BOOK WHERE BRWDAY = '17/12/16 01:19:34.494000000' ORDER BY NO ASC;  -- 해당 시각 검색
SELECT * FROM BOOK WHERE BRWDAY = '2017-12-20 19:47:18.848' ORDER BY NO ASC;  -- 해당 시각 검색

-- ? ? : value1('2017-12-20 19:47:18.000000000'), value1.plusNanos()
SELECT * FROM BOOK WHERE BRWDAY BETWEEN '2017-12-20 19:47:18.000000000' AND '2017-12-20 19:47:18.999999999' ORDER BY NO ASC;  -- 해당 초 검색

-- ? ? : value1('2017-12-20 19:48:00.000000000'), value1.plusSeconds().plusNanos()
SELECT * FROM BOOK WHERE BRWDAY BETWEEN '2017-12-20 19:48:00.000000000' AND '2017-12-20 19:48:59.999999999' ORDER BY NO ASC;  -- 해당 분 검색

-- ? ? : value1('2017-12-20 19:00:00.000000000'), value1.plusMinutes().plusSeconds().plusNanos()
SELECT * FROM BOOK WHERE BRWDAY BETWEEN '2017-12-20 19:00:00.000000000' AND '2017-12-20 19:59:59.999999999' ORDER BY NO ASC;  -- 해당 시 검색

-- ? ? : value1('2017-12-20 00:00:00.000000000').toLocalDate(), value1.plusDays().plusMinutes().plusSeconds().plusNanos()
SELECT * FROM BOOK WHERE BRWDAY BETWEEN '2017-12-20 00:00:00.000000000' AND '2017-12-20 23:59:59.999999999' ORDER BY NO ASC;  -- 해당 일 검색
SELECT * FROM BOOK WHERE BRWDAY BETWEEN '2017-12-20' AND TO_CHAR(TO_DATE('2017-12-20')) || ' 23:59:59.999999999' ORDER BY NO ASC;  -- 해당 일 검색 (쿼리 기반으로 수정됨)

-- ? ? : value1('2017-12-01 00:00:00.000000000').toLocalDate(), value1.toLocalDate()
SELECT * FROM BOOK WHERE BRWDAY BETWEEN '2017-12-01 00:00:00' AND '2017-12-31 23:59:59.999999999' ORDER BY NO ASC;  -- 해당 월 검색
SELECT * FROM BOOK WHERE BRWDAY BETWEEN '2017-11-01 00:00:00' AND '2017-11-30 23:59:59.999999999' ORDER BY NO ASC;  -- 해당 월 검색
SELECT * FROM BOOK WHERE BRWDAY BETWEEN '2017-12-01 00:00:00.000000000' AND TO_CHAR(LAST_DAY('2017-12-01')) || ' 23:59:59.999999999' ORDER BY NO ASC;  -- 해당 월 검색 (쿼리 기반으로 수정됨)
SELECT * FROM BOOK WHERE BRWDAY BETWEEN '2017-12-01' AND TO_CHAR(LAST_DAY('2017-12-01')) || ' 23:59:59.999999999' ORDER BY NO ASC;  -- 해당 월 검색 (쿼리 기반으로 수정됨)

-- ? ? : value1('2017-01-01 00:00:00.000000000').toLocalDate(), value1.toLocalDate()
SELECT * FROM BOOK WHERE BRWDAY BETWEEN '2017-01-01 00:00:00.000000000' AND '2017-12-31 23:59:59.999999999' ORDER BY NO ASC;  -- 해당 년 검색
SELECT * FROM BOOK WHERE BRWDAY BETWEEN '2017-01-01 00:00:00.000000000' AND TO_CHAR(TO_DATE('2017-01-01'), 'YYYY') || '-12-31 23:59:59.999999999' ORDER BY NO ASC;  -- 해당 년 검색 (쿼리 기반으로 수정됨)
SELECT * FROM BOOK WHERE BRWDAY BETWEEN '2017-01-01' AND TO_CHAR(TO_DATE('2017-01-01'), 'YYYY') || '-12-31 23:59:59.999999999' ORDER BY NO ASC;  -- 해당 년 검색 (쿼리 기반으로 수정됨)


SELECT * FROM BOOK WHERE BRWDAY <= '2017-12-20 19:47:18.848' ORDER BY NO ASC;  -- 해당 일 포함 이전 검색 (~까지)
SELECT * FROM BOOK WHERE BRWDAY >= '2017-12-20 19:47:18.848' ORDER BY NO ASC;  -- 해당 일 포함 이후 검색 (~부터)
--------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------


----- Multi Condition -----------------------------------------------------------------------------------
-- int, Integer (INT) ----------------------------------------------------------
SELECT * FROM BOOK WHERE 110 < PRICE AND PRICE < 35000 ORDER BY NO ASC;  -- BETWEEN_EX_EX
SELECT * FROM BOOK WHERE 111 <= PRICE AND PRICE < 35000 ORDER BY NO ASC;  -- BETWEEN_IN_EX
SELECT * FROM BOOK WHERE 110 < PRICE AND PRICE <= 35000 ORDER BY NO ASC;  -- BETWEEN_EX_IN
SELECT * FROM BOOK WHERE -1 <= PRICE AND PRICE <= 34999 ORDER BY NO ASC;  -- BETWEEN_IN_IN
--------------------------------------------------------------------------------

-- LocalDate (Date) ------------------------------------------------------------
SELECT * FROM BOOK WHERE PERIOD BETWEEN '2017-12-17' AND '2017-12-18' ORDER BY NO ASC;  -- 해당 기간의 모든 데이터
--------------------------------------------------------------------------------

-- LocalDateTime (TIMESTAMP) ---------------------------------------------------
SELECT * FROM BOOK WHERE BRWDAY BETWEEN '2017-12-18 00:17:42.875000000' AND '2017-12-20 19:48:14.000000000' ORDER BY NO DESC;  -- 해당 시각 검색
--------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------


