package com.example.practice.product.domain;


import com.example.practice.product.domain.exception.InvalidMoneyPriceException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {

	@ParameterizedTest
	@ValueSource(ints = {1000, 2000, 3000})
	public void createInstanceTest(int price) {
		//Given
		Money money = new Money(BigInteger.valueOf(price));

		assertThat(money).isNotNull();
	}
	
	@ParameterizedTest
	@ValueSource(ints = {0, -2000, -3000})
	public void invalidCreateInstance(int price) {
		assertThatThrownBy(() ->
		   new Money(BigInteger.valueOf(price))
		).isInstanceOf(InvalidMoneyPriceException.class);
	}
}
