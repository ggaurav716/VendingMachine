package com.yash.TeaCoffeeVendingMachine;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
public class VendningMachineStarterTest {

	@InjectMocks
	private VendningMachineStarter vendningMachineStarter;

	@Mock
	private TeaAndCoffeeMachineImpl teaAndCoffeeMachine;

	@Mock
	private UserInput inputChoice;

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
	public void startVendingMachineWithInvalidChoice() {
		MaterialContainer container = new MaterialContainer();
		container.initializeContainer();
		when(inputChoice.userInputValue()).thenReturn(16, 0);
		vendningMachineStarter.startVendingMachine();
		verify(appenderMock, times(22)).doAppend((LoggingEvent) anyObject());

	}

	@Test
	public void startVendingMachineForMakingCoffeeWhenChoiceIsOne() {
		MaterialContainer container = new MaterialContainer();
		container.initializeContainer();
		when(inputChoice.userInputValue()).thenReturn(1, 0);
		when(teaAndCoffeeMachine.makeDrink(any(MenuItems.class), any(MaterialContainer.class), anyInt()))
				.thenReturn(true);
		vendningMachineStarter.startVendingMachine();
		verify(appenderMock, times(23)).doAppend(any(LoggingEvent.class));

	}
	
    @Test
	public void startVendingMachineForMakingTeaWhenChoiceIsTwo() {
		MaterialContainer container = new MaterialContainer();
		container.initializeContainer();
		when(inputChoice.userInputValue()).thenReturn(2, 0);
		when(teaAndCoffeeMachine.makeDrink(any(MenuItems.class), any(MaterialContainer.class), anyInt()))
				.thenReturn(true);
		vendningMachineStarter.startVendingMachine();
		verify(appenderMock, times(23)).doAppend(any(LoggingEvent.class));
	}

	@Test
	public void startVendingMachineForMakingBlackTeaWhenChoiceIsThree() {
		MaterialContainer container = new MaterialContainer();
		container.initializeContainer();
		when(inputChoice.userInputValue()).thenReturn(3, 0);
		when(teaAndCoffeeMachine.makeDrink(any(MenuItems.class), any(MaterialContainer.class), anyInt()))
				.thenReturn(true);
		vendningMachineStarter.startVendingMachine();
		verify(appenderMock, times(23)).doAppend(any(LoggingEvent.class));
	}

	@Test
	public void startVendingMachineForMakingBlackeCoffeeWhenChoiceIsFour() {
		MaterialContainer container = new MaterialContainer();
		container.initializeContainer();
		when(inputChoice.userInputValue()).thenReturn(4, 0);
		when(teaAndCoffeeMachine.makeDrink(any(MenuItems.class), any(MaterialContainer.class), anyInt()))
				.thenReturn(true);
		vendningMachineStarter.startVendingMachine();
		verify(appenderMock, times(23)).doAppend(any(LoggingEvent.class));
	}

	@Test
	public void startVendingMachineForRefillingWhenChoiceIsFive() {
		MaterialContainer container = new MaterialContainer();
		when(inputChoice.userInputValue()).thenReturn(5, 0);
		doNothing().when(teaAndCoffeeMachine).refillContainer(container);
		vendningMachineStarter.startVendingMachine();
		verify(appenderMock, times(21)).doAppend(any(LoggingEvent.class));
	}

	@Test
	public void startVendingMachineForTotalSaleWhenChoiceIsSix() {

		MaterialContainer container = new MaterialContainer();
		container.initializeContainer();
		when(inputChoice.userInputValue()).thenReturn(6, 0);
		vendningMachineStarter.startVendingMachine();
		verify(appenderMock, times(21)).doAppend(any(LoggingEvent.class));
	}

	@Test
	public void startVendingMachineForContainerStatusWhenChoiceIsSeven() {
		MaterialContainer container = new MaterialContainer();
		container.initializeContainer();
		when(inputChoice.userInputValue()).thenReturn(7, 0);
		vendningMachineStarter.startVendingMachine();
		verify(appenderMock, times(21)).doAppend(any(LoggingEvent.class));
	}

	@Test
	public void startVendingMachineForResetContainerWhenChoiceIsEight() {
		MaterialContainer container = new MaterialContainer();
		container.initializeContainer();
		when(inputChoice.userInputValue()).thenReturn(8, 0);
		when(teaAndCoffeeMachine.makeDrink(any(MenuItems.class), any(MaterialContainer.class), anyInt()))
				.thenReturn(true);
		vendningMachineStarter.startVendingMachine();
		verify(appenderMock, times(21)).doAppend(any(LoggingEvent.class));
	}

	@Test
	public void shouldGiveErrorMessageWhenContainerDoesNotHaveEnoughMaterialToMakeCoffee() {
		MaterialContainer container = new MaterialContainer();
		container.initializeContainer();
		when(inputChoice.userInputValue()).thenReturn(1, 0);
		when(teaAndCoffeeMachine.makeDrink(any(MenuItems.class), any(MaterialContainer.class), anyInt()))
				.thenReturn(false);
		vendningMachineStarter.startVendingMachine();
		verify(appenderMock, times(23)).doAppend(any(LoggingEvent.class));
	}

	@Test
	public void shouldGiveErrorMessageWhenContainerDoesNotHaveEnoughMaterialToMakeTea() {
		MaterialContainer container = new MaterialContainer();
		container.initializeContainer();
		when(inputChoice.userInputValue()).thenReturn(2, 0);
		vendningMachineStarter.startVendingMachine();
		verify(appenderMock, times(23)).doAppend(any(LoggingEvent.class));
	}

	@Test
	public void shouldGiveErrorMessageWhenContainerDoesNotHaveEnoughMaterialToMakeBlackTea() {
		MaterialContainer container = new MaterialContainer();

		container.initializeContainer();
		when(inputChoice.userInputValue()).thenReturn(3, 0);
		vendningMachineStarter.startVendingMachine();
		verify(appenderMock, times(23)).doAppend(any(LoggingEvent.class));
	}

	@Test
	public void shouldGiveErrorMessageWhenContainerDoesNotHaveEnoughMaterialToMakeBlackCoffee() {
		MaterialContainer container = new MaterialContainer();
		container.initializeContainer();
		when(inputChoice.userInputValue()).thenReturn(4, 0);
		vendningMachineStarter.startVendingMachine();
		verify(appenderMock, times(23)).doAppend(any(LoggingEvent.class));
	}
	
	
	
 
}
