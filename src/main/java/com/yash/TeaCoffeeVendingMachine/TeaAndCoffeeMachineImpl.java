package com.yash.TeaCoffeeVendingMachine;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class TeaAndCoffeeMachineImpl implements TeaAndCoffeeMachine {

	UserInput scanner = new UserInput();
	private final static Logger logger = Logger.getLogger(VendningMachineStarter.class);
	static Map<String, DrinksReport> drinkReportMap = new HashMap<>();
	static int refillCount = 0;

	private Integer drinkPrice(String inputKey) {
		Map<String, Integer> drinkPrice = new HashMap<>();
		drinkPrice.put("COFFEE", 15);
		drinkPrice.put("BLACKTEA", 5);
		drinkPrice.put("TEA", 10);
		drinkPrice.put("BLACKCOFFEE", 10);
		return drinkPrice.get(inputKey).intValue();
	}

	private MaterialContainer perCupWasteMaterial(MenuItems currentrecipe) {

		Map<MenuItems, MaterialContainer> credentialsToMakeDrink = new HashMap<>();
		credentialsToMakeDrink.put(MenuItems.COFFEE, new MaterialContainer(0, 2, 3, 8, 1));
		credentialsToMakeDrink.put(MenuItems.BLACKTEA, new MaterialContainer(0, 2, 12, 0, 0));
		credentialsToMakeDrink.put(MenuItems.BLACKCOFFEE, new MaterialContainer(0, 2, 12, 0, 0));
		credentialsToMakeDrink.put(MenuItems.TEA, new MaterialContainer(1, 2, 5, 4, 0));
		return credentialsToMakeDrink.get(currentrecipe);
	}

	private MaterialContainer drinkRecipe(MenuItems currentrecipe) {

		Map<MenuItems, MaterialContainer> credentialsToMakeDrink = new HashMap<>();
		credentialsToMakeDrink.put(MenuItems.COFFEE, new MaterialContainer(0, 17, 23, 88, 5));
		credentialsToMakeDrink.put(MenuItems.BLACKTEA, new MaterialContainer(3, 17, 112, 0, 0));
		credentialsToMakeDrink.put(MenuItems.BLACKCOFFEE, new MaterialContainer(0, 17, 112, 0, 3));
		credentialsToMakeDrink.put(MenuItems.TEA, new MaterialContainer(6, 17, 65, 44, 0));
		return credentialsToMakeDrink.get(currentrecipe);
	}

	private MaterialContainer addWastegeMaterialObject(MaterialContainer m1, MaterialContainer m2) {

		m1.setCoffee(m1.getCoffee() + m2.getCoffee());
		m1.setMilk(m1.getMilk() + m2.getMilk());
		m1.setSugar(m1.getSugar() + m2.getSugar());
		m1.setTea(m1.getTea() + m2.getTea());
		m1.setWater(m1.getWater() + m2.getWater());
		return m1;
	}

	private MaterialContainer multipleCupWasteMaterialCalculatoin(MaterialContainer currentrecipe, Integer numOfCups) {

		currentrecipe.setCoffee(currentrecipe.getCoffee() * numOfCups);
		currentrecipe.setMilk(currentrecipe.getMilk() * numOfCups);
		currentrecipe.setSugar(currentrecipe.getSugar() * numOfCups);
		currentrecipe.setTea(currentrecipe.getTea() * numOfCups);
		currentrecipe.setWater(currentrecipe.getWater() * numOfCups);
		return currentrecipe;
	}

	public Boolean makeDrink(MenuItems inputValue, MaterialContainer container, Integer numberOfCup) {

		Boolean status = false;
		MaterialContainer currentrecipe = drinkRecipe(inputValue);

		if (container.getSugar() >= currentrecipe.getSugar() * numberOfCup
				&& container.getWater() >= currentrecipe.getWater() * numberOfCup
				&& container.getMilk() >= currentrecipe.getMilk() * numberOfCup
				&& container.getTea() >= currentrecipe.getTea() * numberOfCup
				&& container.getCoffee() >= currentrecipe.getCoffee() * numberOfCup) {

			container.setWater(container.getWater() - currentrecipe.getWater() * numberOfCup);
			container.setMilk(container.getMilk() - currentrecipe.getMilk() * numberOfCup);
			container.setCoffee(container.getCoffee() - currentrecipe.getCoffee() * numberOfCup);
			container.setSugar(container.getSugar() - currentrecipe.getSugar() * numberOfCup);
			container.setCoffee(container.getCoffee() - currentrecipe.getCoffee() * numberOfCup);
			container.setTea(container.getTea() - currentrecipe.getTea() * numberOfCup);
			status = true;

			DrinksReport report = new DrinksReport();
			report.setDrinkPrice(drinkPrice(inputValue.toString()));
			report.setDrinkQuantity(numberOfCup);
			report.setDrinkTotalPrice(numberOfCup * drinkPrice(inputValue.toString()));

			report.setWasteMaterials(multipleCupWasteMaterialCalculatoin(perCupWasteMaterial(inputValue), numberOfCup));

			if (drinkReportMap.containsKey(inputValue.toString())) {

				report.setDrinkQuantity(drinkReportMap.get(inputValue.toString()).getDrinkQuantity() + numberOfCup);
				report.setDrinkTotalPrice(drinkReportMap.get(inputValue.toString()).getDrinkTotalPrice()
						+ numberOfCup * drinkPrice(inputValue.toString()));

				report.setWasteMaterials(
						addWastegeMaterialObject(drinkReportMap.get(inputValue.toString()).getWasteMaterials(),
								multipleCupWasteMaterialCalculatoin(perCupWasteMaterial(inputValue), numberOfCup)));

				drinkReportMap.put(inputValue.toString(), report);

			} else
				drinkReportMap.put(inputValue.toString(), report);

			logger.info("Total Amount For This Order Rs." + numberOfCup * drinkPrice(inputValue.toString()) + "-/");
		}

		return status;

	}

	private int refillMenu() {
		logger.info("Select Container Which You Want To Fill");
		logger.info("1.Fill Coffee");
		logger.info("2.Fill Tea");
		logger.info("3.Fill Sugar");
		logger.info("4.Fill Water");
		logger.info("5.Fill Milk");
		logger.info("0.For Main Menu");
		return scanner.userInputValue();

	}

	public void refillContainer(MaterialContainer container) {
		Integer choice = 0;

		while ((choice = refillMenu()) != 0) {
			switch (choice) {
			case 1:
				logger.info("Enter Coffee Amount");
				container.setCoffee(container.getCoffee() + scanner.userInputValue());
				break;
			case 2:
				logger.info("Enter Tea Amount");
				container.setTea(container.getTea() + scanner.userInputValue());
				break;
			case 3:
				logger.info("Enter Sugar Amount");
				container.setSugar(container.getSugar() + scanner.userInputValue());
				break;
			case 4:
				logger.info("Enter Water Amount");
				container.setWater(container.getWater() + scanner.userInputValue());
				break;
			case 5:
				logger.info("Enter Milk Amount");
				container.setMilk(container.getMilk() + scanner.userInputValue());
				break;
			default:
				logger.warn("Invalid Option");
				break;
			}
		}
		refillCount++;

	}

	public void checkTotalSale() {
		Integer totalCup = 0, totalCupPrice = 0;

		logger.info("=======================TOTAL SALE DRINK WISE==================\n");
        
		String result=String.format("%15s%10s%8s%12s%20s%10s%10s%10s%10s", "DRINK NAME", "QUANTITY", "PRICE", " TOTAL_PRICE",
				"  WASTE_MATERIAl-COFFEE", "SUGAR", "MILK", "TEA", "WATER");
		logger.info(result);
	   drinkReportMap.forEach((k, v) -> logger.info((String.format("%15s%10s%8s%12s%20s%10s%10s%10s%10s", k,
				v.getDrinkQuantity(), v.getDrinkPrice(), v.getDrinkTotalPrice(), v.getWasteMaterials().getCoffee(),
				v.getWasteMaterials().getSugar(), v.getWasteMaterials().getMilk(), v.getWasteMaterials().getTea(),
				v.getWasteMaterials().getWater()))));

		logger.info("=======================TOTAL SALE CUP WISE===================\n");

		MaterialContainer totalWastage = new MaterialContainer();

		for (Map.Entry<String, DrinksReport> drinkReportEntrySet : drinkReportMap.entrySet()) {
			totalCup += drinkReportEntrySet.getValue().getDrinkQuantity();
			totalCupPrice += drinkReportEntrySet.getValue().getDrinkTotalPrice();
			totalWastage = addWastegeMaterialObject(totalWastage, drinkReportEntrySet.getValue().getWasteMaterials());

		}

		logger.info("Total Cup :" + totalCup + " TotalCupPrice: " + totalCupPrice);
		logger.info("Refill Count :" + refillCount);
		logger.info("=======================TOTAL WASTAGE===========================\n");
		logger.info("Water:" + " " + totalWastage.getWater() + "Ml Tea:" + " " + totalWastage.getTea() + "Mg Sugar:"
				+ " " + totalWastage.getSugar() + "Mg Coffee:" + "" + totalWastage.getCoffee() + "Mg Milk:" + ""
				+ totalWastage.getMilk() + "Ml \n");
	}

	public void checkContainerMaterialstatus(MaterialContainer container) {
		logger.info("=======================Container Remaining Materials=============\n");
		logger.info("Water:" + " " + container.getWater() + "Ml Tea:" + " " + container.getTea() + "Mg Sugar:" + " "
				+ container.getSugar() + "Mg Coffee:" + "" + container.getCoffee() + "Mg Milk:" + ""
				+ container.getMilk() + "Ml \n");

	}

	public void resetContainer(MaterialContainer container) {
		container.initializeContainer();
		logger.info("Container Reset Successfully");

	}

}
