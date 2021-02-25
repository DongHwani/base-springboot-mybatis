CREATE TABLE `practice`.`orders` (

  `orderId`          BIGINT(20)    NOT NULL AUTO_INCREMENT COMMENT '구매 번호',
  `memberId`         VARCHAR(150)  NOT NULL                COMMENT '구매자',
  `totalPrice`       BIGINT(20)    NOT NULL                COMMENT '구매 총 금액',

  PRIMARY KEY (`orderId`)
);

CREATE TABLE `practice`.`order_lines` (

  `orderLineId`      BIGINT(20)    NOT NULL AUTO_INCREMENT COMMENT '구매목록 번호',
  `orderId`          BIGINT(20)    NOT NULL                COMMENT '구매 번호',
  `productId`        BIGINT(20)     NOT NULL                COMMENT '구매자',

  PRIMARY KEY (`orderLineId`)
);


