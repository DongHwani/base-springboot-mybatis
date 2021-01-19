package com.example.practice.product.domain;


import com.example.practice.product.domain.Money;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.practice.product.domain.exception.*;

public class MoneyTest {

	@ParameterizedTest
	@ValueSource(ints = {1000, 2000, 3000})
	public void createInstanceTest(int price) {
		//Given
		Money money = new Money(price);

		assertThat(money).isNotNull();
	}
	
	@ParameterizedTest
	@ValueSource(ints = {0, -2000, -3000})
	public void invalidCreateInstace(int price) {
		assertThatThrownBy(() ->
		   new Money(price)
		).isInstanceOf(InvalidMoneyPriceException.class);
	}
}
