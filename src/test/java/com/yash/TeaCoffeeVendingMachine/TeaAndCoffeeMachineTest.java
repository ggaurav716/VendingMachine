package com.yash.TeaCoffeeVendingMachine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Map;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TeaAndCoffeeMachineTest {

	@InjectMocks
	private TeaAndCoffeeMachineImpl teaAndCoffeeMachine;

	@Mock
	private UserInput inputChoice;

	@Mock
	Map<String, DrinksReport> drinkReportMap;

	@Mock
	private Appender appenderMock;

	@Before
	public void setupAppender() {
		appenderMock = mock(Appender.class);
		Logger.getRootLogger().addAppender(appenderMock);
	}

	@After
	public void removeAppender() {
		Logger.getRootLogger().removeAppender(appenderMock);
	}

	@Test
	public void shouldReturnfalseWhenContainerMaterialIsEmptyOrNotSufficient() {
		MaterialContainer container = new MaterialContainer();
		Boolean currentStatus = teaAndCoffeeMachine.makeDrink(MenuItems.COFFEE, container, new Integer(1));
		assertFalse(currentStatus);

	}

	@Test
	public void shouldReturnTrueWhenContainerHaveSufficientMaterial() {
		MaterialContainer container = new MaterialContainer();
		container.initializeContainer();
		when(drinkReportMap.containsKey(any(MenuItems.class))).thenReturn(true);
		Boolean currentStatus = teaAndCoffeeMachine.makeDrink(MenuItems.TEA, container, new Integer(3));
		assertTrue(currentStatus);

	}

	@Test
	public void shouldCheckTotalSale() {

		teaAndCoffeeMachine.checkTotalSale();
		verify(appenderMock, times(9)).doAppend((LoggingEvent) anyObject());

	}

	@Test
	public void shouldCheckTotalSaleWithPriorValues() {
		MaterialContainer container = new MaterialContainer();
		container.initializeContainer();
		teaAndCoffeeMachine.makeDrink(MenuItems.TEA, container, new Integer(1));
		teaAndCoffeeMachine.checkTotalSale();
		verify(appenderMock, times(10)).doAppend((LoggingEvent) anyObject());

	}

	@Test
	public void shouldCheckContainerMaterialStatus() {
		MaterialContainer container = new MaterialContainer();
		container.initializeContainer();
		teaAndCoffeeMachine.checkContainerMaterialstatus(container);
		verify(appenderMock, times(2)).doAppend((LoggingEvent) anyObject());
	}

	@Test
	public void shouldResetMaterialContainer() {
		MaterialContainer container = new MaterialContainer();
		container.initializeContainer();
		teaAndCoffeeMachine.resetContainer(container);
		verify(appenderMock).doAppend((LoggingEvent) anyObject());
	}

	@Test
	public void shouldRefillCoffeeContainer() {
		MaterialContainer container = new MaterialContainer();
		container.initializeContainer();
		teaAndCoffeeMachine.makeDrink(MenuItems.COFFEE, container, new Integer(1));
		when(inputChoice.userInputValue()).thenReturn(1, 10, 0);
		teaAndCoffeeMachine.refillContainer(container);
		verify(appenderMock, times(16)).doAppend((LoggingEvent) anyObject());
	}

	@Test
	public void shouldRefillTeaContainer() {
		MaterialContainer container = new MaterialContainer();
		container.initializeContainer();
		teaAndCoffeeMachine.makeDrink(MenuItems.COFFEE, container, new Integer(1));
		when(inputChoice.userInputValue()).thenReturn(2, 10, 0);
		teaAndCoffeeMachine.refillContainer(container);
		verify(appenderMock, times(16)).doAppend((LoggingEvent) anyObject());
	}

	@Test
	public void shouldRefillSugarContainer() {
		MaterialContainer container = new MaterialContainer();
		container.initializeContainer();
		teaAndCoffeeMachine.makeDrink(MenuItems.COFFEE, container, new Integer(1));
		when(inputChoice.userInputValue()).thenReturn(3, 10, 0);
		teaAndCoffeeMachine.refillContainer(container);
		verify(appenderMock, times(16)).doAppend((LoggingEvent) anyObject());
	}

	@Test
	public void shouldRefillWaterContainer() {
		MaterialContainer container = new MaterialContainer();
		container.initializeContainer();
		teaAndCoffeeMachine.makeDrink(MenuItems.COFFEE, container, new Integer(1));
		when(inputChoice.userInputValue()).thenReturn(4, 10, 0);
		teaAndCoffeeMachine.refillContainer(container);
		verify(appenderMock, times(16)).doAppend((LoggingEvent) anyObject());
	}

	@Test
	public void shouldRefillMilkContainer() {
		MaterialContainer container = new MaterialContainer();
		container.initializeContainer();
		teaAndCoffeeMachine.makeDrink(MenuItems.COFFEE, container, new Integer(1));
		when(inputChoice.userInputValue()).thenReturn(5, 10, 0);
		teaAndCoffeeMachine.refillContainer(container);
		verify(appenderMock, times(16)).doAppend((LoggingEvent) anyObject());
	}

	@Test
	public void shouldNotRefillContainersWhenChooseInvalidOption() {
		MaterialContainer container = new MaterialContainer();
		container.initializeContainer();
		teaAndCoffeeMachine.makeDrink(MenuItems.COFFEE, container, new Integer(1));
		when(inputChoice.userInputValue()).thenReturn(6, 0);
		teaAndCoffeeMachine.refillContainer(container);
		verify(appenderMock, times(16)).doAppend((LoggingEvent) anyObject());
	}

	@Test
	public void shouldNotRefillContainersWhenCoffeeContainerLimitExceed() {
		MaterialContainer container = new MaterialContainer();
		container.initializeContainer();
		when(inputChoice.userInputValue()).thenReturn(1, 100000, 0);
		teaAndCoffeeMachine.refillContainer(container);
		verify(appenderMock, times(15)).doAppend((LoggingEvent) anyObject());

	}

	@Test
	public void shouldNotRefillContainersWhenTeaContainerLimitExceed() {
		MaterialContainer container = new MaterialContainer();
		container.initializeContainer();
		when(inputChoice.userInputValue()).thenReturn(2, 100000, 0);
		teaAndCoffeeMachine.refillContainer(container);
		verify(appenderMock, times(15)).doAppend((LoggingEvent) anyObject());

	}

	@Test
	public void shouldNotRefillContainersWhenSugarContainerLimitExceed() {
		MaterialContainer container = new MaterialContainer();
		container.initializeContainer();
		when(inputChoice.userInputValue()).thenReturn(3, 100000, 0);
		teaAndCoffeeMachine.refillContainer(container);
		verify(appenderMock, times(15)).doAppend((LoggingEvent) anyObject());

	}

	@Test
	public void shouldNotRefillContainersWhenWaterContainerLimitExceed() {
		MaterialContainer container = new MaterialContainer();
		container.initializeContainer();
		when(inputChoice.userInputValue()).thenReturn(4, 100000, 0);
		teaAndCoffeeMachine.refillContainer(container);
		verify(appenderMock, times(15)).doAppend((LoggingEvent) anyObject());

	}

	@Test
	public void shouldNotRefillContainersWhenMilkContainerLimitExceed() {
		MaterialContainer container = new MaterialContainer();
		container.initializeContainer();
		when(inputChoice.userInputValue()).thenReturn(5, 100000, 0);
		teaAndCoffeeMachine.refillContainer(container);
		verify(appenderMock, times(15)).doAppend((LoggingEvent) anyObject());

	}

	@Test
	public void shouldMakeCoffeeWhenContainerHaveRequiredMaterial() {
		MaterialContainer container = new MaterialContainer();
		container.setCoffee(1000);
		container.setWater(1000);
		container.setMilk(1100);
		container.setSugar(1100);
		container.setCoffee(1100);
		container.setTea(0);
		teaAndCoffeeMachine.makeDrink(MenuItems.COFFEE, container, 2);

	}

	@Test
	public void shouldNotMakeCoffeeWhenSugarIsLessThanRequired() {
		MaterialContainer container = new MaterialContainer();
		container.setCoffee(1000);
		container.setWater(1000);
		container.setMilk(1100);
		container.setTea(0);
		teaAndCoffeeMachine.makeDrink(MenuItems.COFFEE, container, 2);

	}

	@Test
	public void shouldNotMakeCoffeeWhenCoffeeIsLessThanRequired() {
		MaterialContainer container = new MaterialContainer();
		container.setSugar(1100);
		container.setWater(1000);
		container.setMilk(1100);
		container.setTea(0);
		teaAndCoffeeMachine.makeDrink(MenuItems.COFFEE, container, 2);

	}

	@Test
	public void shouldNotMakeCoffeeWhenWaterIsLessThanRequired() {
		MaterialContainer container = new MaterialContainer();
		container.setCoffee(1000);
		container.setSugar(1100);
		container.setMilk(1100);
		container.setTea(0);
		teaAndCoffeeMachine.makeDrink(MenuItems.COFFEE, container, 2);

	}

	@Test
	public void shouldNotMakeCoffeeWhenMilkIsLessThanRequired() {
		MaterialContainer container = new MaterialContainer();
		container.setCoffee(1000);
		container.setWater(1000);
		container.setSugar(1100);
		container.setTea(0);
		teaAndCoffeeMachine.makeDrink(MenuItems.COFFEE, container, 2);

	}

	@Test
	public void shouldNotMakeTeaWhenTeaIsLessThanRequired() {
		MaterialContainer container = new MaterialContainer();
		container.setCoffee(1000);
		container.setWater(1000);
		container.setSugar(1100);
		container.setMilk(1100);
		teaAndCoffeeMachine.makeDrink(MenuItems.TEA, container, 2);

	}

}
