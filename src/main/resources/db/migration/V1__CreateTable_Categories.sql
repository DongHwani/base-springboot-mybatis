CREATE TABLE `practice`.`categories` (

  `categoryId`       BIGINT(20)    NOT NULL AUTO_INCREMENT COMMENT '카테고리 번호',
  `categoryCode`     VARCHAR(150)  NOT NULL                COMMENT '카테고리 코드명',
  `categoryName`     VARCHAR(150)  NOT NULL                COMMENT '카테고리 이름',
  `isUse`            TINYINT(1)    NOT NULL  DEFAULT '1'    COMMENT '카테고리 사용여부',

  PRIMARY KEY (`categoryId`));


CREATE INDEX categoryId_idx ON categories ( categoryId, categoryCode );