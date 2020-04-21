package com.yash.TeaCoffeeVendingMachine;

public interface TeaAndCoffeeMachine {

	public Boolean makeDrink(MenuItems inputValue, MaterialContainer container, Integer numberOfCup);

	public void refillContainer(MaterialContainer container);

	public void checkTotalSale();

	public void resetContainer(MaterialContainer container);

	public void checkContainerMaterialstatus(MaterialContainer container);

}
