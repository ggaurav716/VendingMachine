package com.yash.TeaCoffeeVendingMachine;

public class MaterialContainer {

	private Integer tea;
	private Integer sugar;
	private Integer water;
	private Integer milk;
	private Integer coffee;

	public MaterialContainer() {

		this(0, 0, 0, 0, 0);
	}

	public MaterialContainer(Integer tea, Integer sugar, Integer water, Integer milk, Integer coffee) {
		this.tea = tea;
		this.sugar = sugar;
		this.water = water;
		this.milk = milk;
		this.coffee = coffee;
	}

	public Integer getTea() {
		return tea;
	}

	public void setTea(Integer tea) {
		if (tea <= ContainerCapacity.TEA.getValue())
			this.tea = tea;
		else
			System.out.println("Tea Container limit is Exceeding");

	}

	public Integer getSugar() {
		return sugar;
	}

	public void setSugar(Integer sugar) {
		if (sugar <= ContainerCapacity.SUGAR.getValue())
			this.sugar = sugar;
		else
			System.out.println("Sugar Container limit is Exceeding");

	}

	public Integer getWater() {
		return water;
	}

	public void setWater(Integer water) {
		if (water <= ContainerCapacity.WATER.getValue())
			this.water = water;
		else
			System.out.println("Water Container limit is Exceeding");

	}

	public Integer getMilk() {
		return milk;
	}

	public void setMilk(Integer milk) {
		if (milk <= ContainerCapacity.MILK.getValue())
			this.milk = milk;
		else
			System.out.println("Milk Container limit is Exceeding");

	}

	public Integer getCoffee() {
		return coffee;
	}

	public void setCoffee(Integer coffee) {
		if (coffee <= ContainerCapacity.COFFEE.getValue())
			this.coffee = coffee;
		else {
			System.out.println("Cofee Container limit is Exceeding");
		}
	}

	public void initializeContainer() {
		this.tea = ContainerCapacity.TEA.getValue();
		this.sugar = ContainerCapacity.SUGAR.getValue();
		this.water = ContainerCapacity.WATER.getValue();
		this.milk = ContainerCapacity.MILK.getValue();
		this.coffee = ContainerCapacity.COFFEE.getValue();
	}

}
