CREATE TABLE `practice`.`category` (

  `categoryId`       BIGINT(20)    NOT NULL AUTO_INCREMENT COMMENT '카테고리 번호',
  `categoryName`     VARCHAR(150)  NOT NULL                COMMENT '카테고리 이름',

  PRIMARY KEY (`categoryId`));
