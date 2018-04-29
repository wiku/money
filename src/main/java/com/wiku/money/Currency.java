package com.wiku.money;

import lombok.Data;

@Data public class Currency
{
    private final String symbol;
    private final int fractionDigits;

    public static Currency of( String symbol, int fractionDigits )
    {
        return new Currency(symbol, fractionDigits);
    }
}
