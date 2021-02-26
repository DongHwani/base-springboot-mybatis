### SpringBoot - Mybatis 설정 및 연관관계 맵핑 (작성중)
SpringBoot-Mybatis 프로젝트 환경에서 제공해주는 기본옵션들과 
Mybatis에서 제공하는 연관관계 맵핑과 관련하여 정리하였다. 

##### 1. 프로젝트 의존성 설정 

~~~gradle
dependencies {
 implementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter:2.1.4'
}
~~~
이렇게 의존성을 추가하면 스프링부트에서는 오토 스캐닝을 지원하여 `Mapper 인터페이스`(어노테이션)를 찾아
Bean으로 등록해준다. 
 
##### 2. Mapper 설정

Mapper 설정에는 총 2가지 파일 생성이 필요하다. 첫번째는 xml기반의 쿼리문, 객체 맵핑등을 기술한 xml파일과 이 xml파일의 기능들을
제공하기 위한 인터페이스 생성이다. 

2-1) 스프링 컨테이너에 등록할 Repository 생성
~~~java
@Mapper
public interface CategoryRepository {

    void save(Category category);
}
~~~
@Mapper 어노테이션이 붙어있으면 해당 인터페이스는 스프링 컨테이너에 bean으로 등록된다.

2-2) mapper.xml 생성   
![프로젝트구조](./img/프로젝트구조.png)
~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.example.practice.category.domain.CategoryRepository">

    <insert id="save" parameterType="category"
            useGeneratedKeys="true"  keyProperty="categoryId" >
      INSERT INTO categories
        (categoryCode, categoryName)
      VALUES
        (#{categoryCode}, #{categoryName})
    </insert>
</mapper>
~~~
두 개의 파일을 프로젝트에 생성하였다면, 두 개의 파일을 이어줄 수 있는 `경로설정`이 필요하다. 왜냐하면
인터페이스를 통해 해당 xml의 쿼리문을 수행해야하는데 이 mapper 인터페이스가 xml 파일위치가 어디에 있는지 알 수 없기 
때문이다.   

2-3)properties 파일에 mapper 경로설정
~~~properties
mybatis.mapper-locations=mybatis/mapper/*.xml
~~~
이렇게 경로에 대한 설정을 해주면, mapper 인터페이스가 해당 xml을 찾아서 요청하여 mybatis를 사용하게 된다.
기본적으로 mapper-locations는 루트구조가 resources폴더로 되어있기에 resources폴더 하위에 mapper 폴더 경로를 적어주면된다.

이렇게 mapper xml 경로를 설정을 완료하였다면 mapper 인터페이스에서 xml를 사용할 수 있게 된다. 여기서 mapper 인터페이스에
기술된 메서드명과 xml에 쿼리문에 기술된 id값이 일치해야 해당 쿼리문을 수행 할 수 있다.


##### 3. 연관관계 맵핑

3.1) has one 관계   
has one 관계인 객체로 맵핑할 경우 `<association>` 태그로 맵핑할 수 있으며, 두 가지 맵핑 전략이 존재한다.  

- Nested Result : 하나의 JOIN 쿼리로 결과를 맵핑하는 방법
- Nested Select : 다른 맵핑된 SQL 구문을 실행하여 맵핑하는 방법

3.1.1) Nested Result  
~~~java
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(exclude = {"seller", "productName", "image", "description", "registeredDate", "price", "category"})
public class Product {
	
	private Long productId;
	private String productName;
	private Member seller;
	private String image;
	private Money price;
	private String description;
	private LocalDateTime registeredDate;
	private Category category;
    
    //생략.. 
}
@Getter
@Builder @ToString
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(exclude ={ "password", "phoneNumber", "address", "detailAddress", "zipCode"})
public class Member {

    private Long memberSequence;
    private String memberId;
    private String password;
    private String phoneNumber;

}
~~~ 
~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.example.practice.product.domain.ProductRepository">

    <resultMap id="Product" type="com.example.practice.product.domain.Product">
        <id property="productId"       column="productId"/>
        <result property="productName" column="productName"/>
        <result property="image" column="image"/>
        <result property="description" column="description"/>
        <result property="registeredDate" column="registeredDate"/>
        <association property="seller"         javaType="com.example.practice.member.domain.Member">
            <id property="memberSequence"       column="memberSequence"/>
            <result property="memberId"         column="memberId"/>
            <result property="phoneNumber"      column="phoneNumber"/>
            <result property="description"      column="address"/>
        </association>
    </resultMap>

    <select id="findById" parameterType="long" resultMap="Product">
        SELECT
            p.productId,
            p.productName,
            p.image,
            p.description,
            p.categoryId,
            m.memberSequence,
            m.memberId
        FROM products p INNER JOIN members m
        ON p.sellerId = m.memberId
        WHERE p.productId = #{productId}
    </select>

</mapper>
~~~
Product 도메인이 Member타입의 seller라는 객체를 값으로 가지고 있었고, 이를 association 태그와 JOIN쿼리문을 사용하여
resultMap에 맵핑시킬 수 있다.  

<테스트결과>
![association](./img/Nested_Result_hasone.png)

3.1.2) Nested Select

3-2) has many 관계(one to many)
 one to many의 관계에서는 insert와 select 두 가지 과정에 대해 살펴보겠다.  
      
 - **INSERT 과정**   
~~~java
@Builder @Getter @EqualsAndHashCode(exclude = { "buyer", "orderLines" })
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    private Long orderId;
    private Member buyer;
    private Money totalPrice;
    private List<Product> orderLines;
    
    //생략...
}
~~~
~~~sql
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
~~~
주문을 의미하는 order 도메인은 여러개의 상품을 가지고 있는 One to Many 관계이며, 테이블은 orders와 order_lines가 1:N관계를 갖고 있다.
여기서 새로운 order를 insert 할 경우, 두 개의 테이블에 orders와 oder_lines을를 insert를 해야한다. 
~~~java
@Mapper
@Repository
public interface OrderRepository  {
    Long saveOrder(Order order);
    void saveOrderLines(Order order);
}
~~~
~~~xml
    <insert id="saveOrder" parameterType="com.example.practice.order.domain.Order"
            useGeneratedKeys="true"  keyProperty="orderId" >
      INSERT INTO orders
        (orderId, memberId, totalPrice)
      VALUES
        (#{orderId}, #{buyer.memberId}, #{totalPrice.price})
    </insert>

    <insert id="saveOrderLines" parameterType="order">
        INSERT INTO order_lines
            (orderId, productId)
        VALUES
            <foreach collection="orderLines" item="orderLine" open="(" separator="),(" close=")">
                #{orderId}, #{orderLine.productId}
            </foreach>
    </insert>
~~~
이렇게 OrderRepository에 order를 insert하는 메서드와 주문목록인 orderLines를 insert를 하는 메서드 두 개를 만들어 테이블에 저장할 수 있다.
하지만, 이런 일반적인 방법은 객체의 연관관계가 아닌 테이블의 연관관계에 따라 Repository를 사용하는 쪽에서 좀 더 구체적인 테이블 정보를 알아야하는 불편함이 있다.
~~~java
 public class OrderRepositoryTest  {
    @Autowired
    private OrderRepository orderRepository;

    public void save() {
        //OrderRepository를 사용하는 쪽에서 두 개의 메서드를 사용해야 정상적으로 두 테이블에 데이터가 저장된다.
        //순서 또한 지켜줘야한다는 불편함이 있다.
        orderRepository.saveOrder(order);
        orderRepository.saveOrderLines(order);
    }
}
~~~

JPA처럼 루트 도메인격인 Order 객체를 insert하면 연관관계에 있는 하위 도메인도 insert를 하여 좀 더 추상화 될 수 있는 방법이 없을까 고민하다가
스택오버플로우에서 default 메서드를 사용하여 제공해주는 방법을 찾았다. 
~~~java 
@Mapper
@Repository
public interface OrderRepository extends OrderBaseSave {

    Long saveOrder(Order order);
    void saveOrderLines(Order order);
    
    default void save(Order order) {
        saveOrder(order);
        saveOrderLines(order);
    }
}
~~~
(출처 : https://stackoverflow.com/questions/33028923/mybatis-inserts-one-to-many-relationship)

이렇게 하면 사용하는 쪽에서 save 메서드만 사용해도 두 테이블에 저장할 수 있게 된다. 
~~~java
 public class OrderRepositoryTest  {
    @Autowired
    private OrderRepository orderRepository;

    public void save() {
        //이런 방법도 있었구나 
        orderRepository.save(order);
    }
}
~~~
하지만 saveOrder, saveOrderLines 두 개 메서드가 여전히 공개된 상태이기 때문에 사용하는 쪽에서 해당 메서드를 사용 할 문제가 있다. 
![dfeault메서드를사용한방식](./img/oneToMany-insert.png)

이 두 개의 메서드를 save라는 하나의 메서드로 사용하는 쪽에 제공하려면, 상속 구조를 사용하면 된다. 
~~~java 
public interface OrderBaseSave {

    Long saveOrder(Order order);
    void saveOrderLines(Order orderLines);

}

@Mapper
@Repository
public interface OrderRepository extends OrderBaseSave {

    default void save(Order order) {
        saveOrder(order);
        saveOrderLines(order);
    }
}
~~~
이 방법으로 Repository를 사용하는 쪽에서는 테이블의 연관관계에 상관없이 save 메서드만 사용해서 객체와 연관된 객체들도 저장할 수 있게 된다.   

 - **SELECT 과정**  
 
 
 
 
 
 

3-3) 생성자를 통한 객체 맵핑  
Product 객체 내부에는 Money라는 객체가 있다. 
이 Money 객체는 양수의 값만 받을 수 있도록 생성자에 제약사항을 걸어놨다. 
(Java bean 규약에 따르는 기본생성자가 있어야 Mapping이 가능하여 private 생성자를 추가하였다 )
~~~java
public class Product {
	
	private Long productId;
	private String productName;
	private Money price;
    // 생략..
}

public class Money {

	private static final int NEGATIVE_OR_ZERO = 1;
	private BigInteger price;

	private Money(){}

	public Money(final BigInteger price) {
		if (price.signum() != NEGATIVE_OR_ZERO) {
			throw new InvalidMoneyPriceException();
		}
		this.price = price;
	}

	public BigInteger getPrice() {
		return price;
    }
}
~~~
DB에서 조회 후 해당 값을 Money 객체의 생성자 파라미터로 넘겨주려면 `<constructor>`태그를 사용하여
인스턴스 생성시에 조회 된 결과값을 파라미터로 넘겨줄 수 있다. 
~~~xml
    <resultMap id="Product" type="com.example.practice.product.domain.Product">
        <id property="productId"       column="productId"/>
        <result property="productName" column="productName"/>
        <association property="price"  javaType="com.example.practice.product.domain.Money">
           <constructor>
              <arg  column="price" javaType="java.math.BigInteger" ></arg>
           </constructor>
        </association>
        <!-- 생략 -->
    </resultMap>
~~~
`<arg>`태그를 사용하여 Mapping된 결과값을 생성자 파라미터로 넘겨주면, Money 객체 생성자에 있는 음수값에 대한 유효성검사
를 거치게된다. 

만약 데이터베이스에 강제로 실수로 음수값을 insert했다고 가정해보겠다. 

![음수값](./img/음수값insert.png)
가격이 -1인 상품은 도메인 규칙에 어긋난다. 위의 생성자 태그를 사용하여 가격이 -1인 상품을 가져 올 경우 리플렉션 과정 중 
도메인 예외가 발생하여 사전에 불필요한 값을 가져오지 못하도록 미연의 방지를 할 수 있게 된다. 
~~~java
public class ProductRepositoryTest extends ProductDomainBuilder {
    @Test
    public void findByIdThenFail(){
        assertThatThrownBy(() ->
                productRepository.findById(46L).get()
        ).isInstanceOf(MyBatisSystemException.class);
    }
}
~~~


[Refference]
- https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure
- https://blog.mybatis.org/2019/01/mybatis-350-released.html
- https://stackoverflow.com/questions/33028923/mybatis-inserts-one-to-many-relationship