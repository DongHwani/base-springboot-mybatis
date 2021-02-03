CREATE TABLE `practice`.`cart` (

  `cartId`           BIGINT(20)    NOT NULL AUTO_INCREMENT COMMENT '카드 번호',
  `productId`        BIGINT(20)  NOT NULL                COMMENT '카트 상품번호',
  `memberId`         VARCHAR(150) NOT NULL                COMMENT '카트 유저아이디',

PRIMARY KEY (`cartId`));
