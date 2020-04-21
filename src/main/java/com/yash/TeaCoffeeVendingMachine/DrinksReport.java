package com.yash.TeaCoffeeVendingMachine;

public class DrinksReport {

	private Integer drinkQuantity;
	private Integer drinkPrice;
	private MaterialContainer wasteMaterials;
	private Integer drinkTotalPrice;

	public DrinksReport() {

	}

	public Integer getDrinkQuantity() {
		return drinkQuantity;
	}

	public void setDrinkQuantity(Integer drinkQuantity) {
		this.drinkQuantity = drinkQuantity;
	}

	public Integer getDrinkPrice() {
		return drinkPrice;
	}

	public void setDrinkPrice(Integer drinkPrice) {
		this.drinkPrice = drinkPrice;
	}

	public MaterialContainer getWasteMaterials() {
		return wasteMaterials;
	}

	public void setWasteMaterials(MaterialContainer wasteMaterials) {
		this.wasteMaterials = wasteMaterials;
	}

	public Integer getDrinkTotalPrice() {
		return drinkTotalPrice;
	}

	public void setDrinkTotalPrice(Integer drinkTotalPrice) {
		this.drinkTotalPrice = drinkTotalPrice;
	}

}
