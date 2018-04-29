package com.wiku.money;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class MoneyTest
{
    
    private static Currency PLN = Currency.of("PLN",2);
    private static Currency USD = Currency.of("USD",2);

    private static Money testMoney = Money.of("10.10",PLN);

    @Test
    public void testNewInstanceCreatesProperBigDecimalAmount()
    {
        assertEquals(Money.of("10.10", PLN), testMoney);
    }
    

    @Test
    public void testNewInstancePerformsRoundingAccordingToCurrency()
    {
        Money money = Money.of("10.1",PLN);

        assertEquals(testMoney, money);
    }
    
    @Test
    public void canDivideByBigDecimal()
    {
        Money result = testMoney.divideBy(new BigDecimal(10));
        
        assertEquals(Money.of("1.01",PLN), result);
    }

    @Test
    public void canMultiplyByBigDecimal()
    {
        Money multiplied = testMoney.multiplyBy(BigDecimal.valueOf(5));

        assertEquals(Money.of("50.50", PLN), multiplied);
    }

    @Test
    public void canSubtractMoneyFromMoney()
    {
        Money subtracted = testMoney.minus(Money.of("5.2", PLN));

        assertEquals(Money.of("4.90", PLN), subtracted);
    }

    @Test
    public void returnsAmountAndSymbolWhenToStringMethodCalled()
    {
        assertEquals("10.10 PLN", testMoney.toString());
    }

    @Test
    public void canAddMoneyToMoney()
    {
        Money sum = testMoney.plus(Money.of("1", PLN));

        assertEquals(Money.of("11.10", PLN), sum);
    }

    @Test
    public void canAddNegativeAmountOfMoneyToMoney()
    {
        Money subtracted = testMoney.plus(Money.of("-5.2", PLN));

        assertEquals(Money.of("4.90", PLN), subtracted);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenTryingToAddMoneyOfDifferentCurrencies()
    {
        testMoney.plus(Money.of("10", USD));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenTryingToSubtractMoneyOfDifferentCurrencies()
    {
        testMoney.minus(Money.of("10", USD));
    }
}
