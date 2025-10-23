
/*
# tables
TODO 컬럼명 소문자 + 언더바
     그 외 대문자
 BOARD  */
    CREATE TABLE board (
        board_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
        title VARCHAR(255) NOT NULL,
        category INT,
        writer VARCHAR(100) NOT NULL,
        views INT,
        create_date DATE,
        update_date DATE,
        content TEXT
    );

### CATEGORY
    CREATE TABLE CATEGORY (
        CD INT NOT NULL PRIMARY KEY ,
        NAME VARCHAR(50) NOT NULL
    );

### COMMENT
    CREATE TABLE COMMENT (
        BOARD_ID INT NOT NULL ,
        COMMENT_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
        CONTENT VARCHAR(500) NOT NULL,
        WRITER VARCHAR(100) NOT NULL,
        CREATE_DATE DATE
    );

### USER
    CREATE TABLE USER (
        USER_ID VARCHAR(255) NOT NULL PRIMARY KEY,
        PASSWORD VARCHAR(500) NOT NULL
    );


### 비밀번호 암/복호화
-- 암호화
    PASSWORD = hex(aes_encrypt('password','p'));

-- 복호화
    select BOARD_ID, AES_DECRYPT(unhex(PASSWORD), 'p')
    FROM BOARD;