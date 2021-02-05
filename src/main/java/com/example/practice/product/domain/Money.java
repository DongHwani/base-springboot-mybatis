package com.example.practice.product.domain;

import com.example.practice.product.domain.exception.InvalidMoneyPriceException;

import java.math.BigInteger;


public class Money {

	private static final int NEGATIVE_OR_ZERO = 1;
	private BigInteger price;

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
