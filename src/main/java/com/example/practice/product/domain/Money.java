package com.example.practice.product.domain;

import com.example.practice.product.domain.exception.InvalidMoneyPriceException;

import java.math.BigInteger;


public class Money {

	private final BigInteger price;

	public Money(final BigInteger price) {
		if (price.signum() != 1) {
			throw new InvalidMoneyPriceException();
		}
		this.price = price;
	}

    public BigInteger getPrice() {
		return price;
    }
}
