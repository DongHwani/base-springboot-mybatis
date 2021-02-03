CREATE TABLE `practice`.`members` (

  `memberSequence`     BIGINT(20)    NOT NULL AUTO_INCREMENT COMMENT '유저 번호',
  `memberId`     VARCHAR(150)  NOT NULL                COMMENT '유저 ID',
  `password`     VARCHAR(150)  NOT NULL                COMMENT '유저 패스워드',
  `phoneNumber`  VARCHAR(150)                COMMENT '유저 전화번호',
  `address`     VARCHAR(150)  NOT NULL                COMMENT '유저 주소',
  `detailAddress`     VARCHAR(150)  NOT NULL                COMMENT '유저 주소',
  `zipCode`     VARCHAR(150)  NOT NULL                COMMENT '유저 주소',

  PRIMARY KEY (`memberSequence`));
