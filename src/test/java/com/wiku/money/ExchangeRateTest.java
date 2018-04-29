package com.wiku.money;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ExchangeRateTest
{
    private static final Currency BTC = Currency.of("BTC", 8);
    private static final Currency USD = Currency.of("USD", 2);
    private static final Currency PLN = Currency.of("PLN", 2);

    Pair btcusd = Pair.of(BTC, USD);
    ExchangeRate exchangeRate = ExchangeRate.of("14000", btcusd);

    @Test
    public void canReturnProperFieldsWhenGettersCalled()
    {
        assertEquals(btcusd, exchangeRate.getPair());
        assertEquals(new BigDecimal("14000"), exchangeRate.getRate());
    }

    @Test
    public void canCalculateEqualsAndHashCode()
    {
        assertEquals(ExchangeRate.of("14000", btcusd), exchangeRate);
        assertEquals(ExchangeRate.of("14000", btcusd).hashCode(), exchangeRate.hashCode());
        assertNotEquals(ExchangeRate.of("14001", btcusd), exchangeRate);
        assertNotEquals(ExchangeRate.of("14000", Pair.of(USD,BTC)), exchangeRate);

    }

    @Test public void canConvertFromBaseToQuote()
    {
        Money usd = exchangeRate.convert(Money.of("0.07142857", BTC));
        assertEquals(Money.of("1000.0", USD), usd);
    }

    @Test public void canConvertFromBaseToQuoteWithRoundingMode()
    {
        Money usd = exchangeRate.convert(Money.of("0.07142857", BTC), RoundingMode.DOWN);
        assertEquals(Money.of("999.99", USD), usd);
    }

    @Test public void canConvertFromQuoteToBase()
    {
        Money btc = exchangeRate.convert(Money.of("1000.0", USD));
        assertEquals(Money.of("0.07142857", BTC), btc);
    }

    @Test public void canConvertFromQuoteToBaseWithRoundingMode()
    {
        Money btc = exchangeRate.convert(Money.of("1000.0", USD), RoundingMode.UP);
        assertEquals(Money.of("0.07142858", BTC), btc);
    }

    @Test public void canReturnToStringInReadableForm()
    {
        System.out.println(exchangeRate);
        assertEquals("14000(BTCUSD)", exchangeRate.toString());
    }

    @Test(expected = IllegalArgumentException.class) public void throwsExceptionWhenUnableToConvert()
    {
        exchangeRate.convert(Money.of("1000.0", PLN));
    }

}
