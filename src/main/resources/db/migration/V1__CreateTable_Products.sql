CREATE TABLE `practice`.`products` (

  `productId`       BIGINT(20)    NOT NULL AUTO_INCREMENT COMMENT '상품 번호',
  `productName`     VARCHAR(150)  NOT NULL                COMMENT '상품 이름',
  `sellerId`        VARCHAR(100)  NOT NULL                COMMENT '작성자',
  `image`           VARCHAR(200)  NOT NULL                COMMENT '상품 이미지',
  `price`           BIGINT        NOT NULL                COMMENT '상품가격',
  `description`     VARCHAR(500)  NOT NULL                COMMENT '상품설명',
  `registeredDate`  DATE          NOT NULL                COMMENT '등록일자',
  `categoryId`      BIGINT(20)    NOT NULL                COMMENT '카테고리 번호',

  PRIMARY KEY (`productId`));

CREATE INDEX productId_idx ON products ( productId );

