package com.wiku.money;

import org.junit.Test;

import java.math.BigDecimal;

public class ReadmeExamplesTest
{
    @Test public void testReadmeExample()
    {

        Currency usd = Currency.of("USD", 2);
        Currency eur = Currency.of("EUR", 2);

        Currency btc = Currency.of("BTC", 8);


        Money usd10 = Money.of("10.00", usd);

        Money usd1 = usd10.divideBy(new BigDecimal(10));
        Money usd11 = usd10.plus(usd1);

        Pair eurUsd = Pair.of(eur, usd);
        ExchangeRate eurUsdRate = ExchangeRate.of("1.22546", eurUsd);
        Money eurMoney = eurUsdRate.convert(Money.of("2.50", usd));
    }
}
