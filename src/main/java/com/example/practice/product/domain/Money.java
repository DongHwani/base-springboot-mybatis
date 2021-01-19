package com.example.practice.product.domain;

import com.example.practice.product.domain.exception.InvalidMoneyPriceException;


public class Money {

	private final int price;

	public Money(final int price) {
		if (price <= 0) {
			throw new InvalidMoneyPriceException();
		}
		this.price = price;
	}

    public int getPrice() {
		return this.price;
    }
}
