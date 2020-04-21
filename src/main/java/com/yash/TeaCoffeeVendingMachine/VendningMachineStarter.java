package com.yash.TeaCoffeeVendingMachine;

import org.apache.log4j.Logger;

public class VendningMachineStarter {
	private final static Logger logger = Logger.getLogger(VendningMachineStarter.class);

	private UserInput inputChoice = new UserInput();
	TeaAndCoffeeMachineImpl makeTeaAndCoffeeImpl = new TeaAndCoffeeMachineImpl();

	private int menuList() {
		logger.info("======================Choose One Option======================");
		logger.info("0.EXIT TEA COFFEE VENDING MACHINE");
		logger.info("1.MAKE COFFEE");
		logger.info("2.MAKE TEA");
		logger.info("3.MAKE BLACKTEA");
		logger.info("4.MAKE BLACKCOFFEE");
		logger.info("5.REFILL CONTAINER");
		logger.info("6.CHECK TOTAL SALE");
		logger.info("7.CONTAINER STATUS");
		logger.info("8.RESET CONTAINER");

		return inputChoice.userInputValue();
	}

	@SuppressWarnings("incomplete-switch")
	public void startVendingMachine() {

		int choice = 0;
		Boolean status = false;

		MenuItems values[] = MenuItems.values();

		MaterialContainer container = new MaterialContainer();
		container.initializeContainer();

		while ((choice = menuList()) != 0) {
			if (choice > 8 || choice < 0) {
				logger.warn("Invalid Choice Choose Option 0-8 Only");

			} else {
				switch (values[choice]) {
				case COFFEE:
					logger.info("You Charge Rs.15/-Each Cup, Enter number of Cup");
					Integer numberOfCoffeeCup = inputChoice.userInputValue();
					status = makeTeaAndCoffeeImpl.makeDrink(values[choice], container, numberOfCoffeeCup);
					if (status == true)
						logger.info(values[choice] + " READY\n");
					else
						logger.error("Container Does Not Have Sufficient Material To Make " + numberOfCoffeeCup
								+ " CoffeeCup");
					break;
				case TEA:
					logger.info("You Charge Rs.10/-Each Cup, Enter number of Cup");
					Integer numberOfTeaCup = inputChoice.userInputValue();
					status = makeTeaAndCoffeeImpl.makeDrink(values[choice], container, numberOfTeaCup);
					if (status == true)
						logger.info(values[choice] + " READY\n");
					else
						logger.error(
								"Container Does Not Have Sufficient Material To Make " + numberOfTeaCup + " Tea Cup");
					break;
				case BLACKTEA:
					logger.info("You Charge Rs.5/-Each Cup, Enter number of Cup ");
					Integer numberofBlackTeaCup = inputChoice.userInputValue();
					status = makeTeaAndCoffeeImpl.makeDrink(values[choice], container, numberofBlackTeaCup);
					if (status == true)
						logger.info(values[choice] + " READY\n");
					else
						logger.error("Container Does Not Have Sufficient Material To Make " + numberofBlackTeaCup
								+ " Blacktea Cup");
					break;
				case BLACKCOFFEE:
					logger.info("You Charge Rs.10/-Each Cup, Enter number of Cup");
					Integer numberofBlackCoffeeCup = inputChoice.userInputValue();
					status = makeTeaAndCoffeeImpl.makeDrink(values[choice], container, numberofBlackCoffeeCup);
					if (status == true)
						logger.info(values[choice] + " READY\n");
					else
						logger.error("Container Does Not Have Sufficient Material To Make " + numberofBlackCoffeeCup
								+ " BlackCoffee Cup");
					break;
				case REFILL_CONTAINER:
					makeTeaAndCoffeeImpl.checkContainerMaterialstatus(container);
					makeTeaAndCoffeeImpl.refillContainer(container);
					break;
				case TOTAL_SALE_REPORT:
					makeTeaAndCoffeeImpl.checkTotalSale();
					break;
				case CONTAINER_STATUS:
					makeTeaAndCoffeeImpl.checkContainerMaterialstatus(container);
					break;
				case RESET_CONTAINER:
					makeTeaAndCoffeeImpl.resetContainer(container);
					break;

				}

			}
		}
		logger.info("Machine Stop Successfully,Thank You...");
	}

}
