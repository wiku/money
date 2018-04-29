package com.wiku.money;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ExchangeRateTest
{
    private static final Currency BTC = new Currency("BTC", 8);
    private static final Currency USD = new Currency("USD", 2);
    private static final Currency PLN = new Currency("PLN", 2);


    Pair btcusd = Pair.of(BTC,USD);
    ExchangeRate exchangeRate = ExchangeRate.of("14000", btcusd);

    @Test
    public void canConvertFromBaseToQuote()
    {        
        Money usd = exchangeRate.convert(Money.of("2",BTC));
        assertEquals(Money.of("28000.00",USD), usd);
    }
    

    @Test
    public void canConvertFromQuoteToBase()
    {        
        Money btc = exchangeRate.convert(Money.of("1000.0",USD));
        assertEquals(Money.of("0.07142857",BTC), btc);
    }

    @Test
    public void canReturnToStringInReadableForm()
    {
        System.out.println(exchangeRate);
        assertEquals("14000(BTCUSD)", exchangeRate.toString());
    }

    @Test(expected =  IllegalArgumentException.class)
    public void throwsExceptionWhenUnableToConvert()
    {
        exchangeRate.convert(Money.of("1000.0",PLN));
    }

}
