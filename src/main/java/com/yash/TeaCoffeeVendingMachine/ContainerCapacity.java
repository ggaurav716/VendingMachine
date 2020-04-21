package com.yash.TeaCoffeeVendingMachine;

public enum ContainerCapacity {
	TEA(2000), SUGAR(8000), WATER(15000), MILK(10000), COFFEE(2000);
	private final int materialCapacity;

	ContainerCapacity(final int materialCapacity) {
		this.materialCapacity = materialCapacity;
	}

	public int getValue() {
		return materialCapacity;
	}
}
