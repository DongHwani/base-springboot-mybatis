CREATE TABLE `practice`.`purchase` (

  `purchaseId`       BIGINT(20)    NOT NULL AUTO_INCREMENT COMMENT '구매 번호',
  `productId`        BIGINT(20)    NOT NULL                COMMENT '구매한 상품 번호',
  `memberSequence`   BIGINT(20)    NOT NULL                COMMENT '구매인',
  `price`            BIGINT(20)    NOT NULL                COMMENT '구매금액',
  `address`          VARCHAR(20)   NOT NULL                COMMENT '주소',
  `detailAddress`    VARCHAR(20)   NOT NULL                COMMENT '상세주소',
  `zipcode`          VARCHAR(20)   NOT NULL                COMMENT '우편번호',

  PRIMARY KEY (`purchaseId`));
