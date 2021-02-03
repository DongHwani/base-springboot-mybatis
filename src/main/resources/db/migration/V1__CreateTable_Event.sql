CREATE TABLE `practice`.`event` (

  `eventId`       BIGINT(20)      NOT NULL AUTO_INCREMENT COMMENT '이벤트 번호',
  `productId`       BIGINT(20)    NOT NULL                COMMENT '상품 번호',
  `productName`   VARCHAR(200)    NOT NULL                COMMENT '이벤트 상품이름',
  `price`         BIGINT          NOT NULL                COMMENT '이벤트 상품가격',
  `description`   VARCHAR(200)    NOT NULL                COMMENT '이벤트 상품설명',
  `image`   VARCHAR(200)    NOT NULL                COMMENT '이벤트 상품사진',

  PRIMARY KEY (`eventId`));
