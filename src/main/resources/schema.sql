DROP TABLE IF EXISTS login_info CASCADE;

CREATE TABLE login_info (
    email VARCHAR(100) PRIMARY KEY COMMENT '이메일'
) COMMENT '접속 가능한 계정';