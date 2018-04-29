package com.wiku.money;

import java.util.Objects;

public class Currency
{
    private final String symbol;
    private final int fractionDigits;

    public static Currency of( String symbol, int fractionDigits )
    {
        return new Currency(symbol, fractionDigits);
    }

    public String getSymbol()
    {
        return symbol;
    }

    public int getFractionDigits()
    {
        return fractionDigits;
    }

    @Override public boolean equals( Object o )
    {
        if( this == o )
            return true;
        if( o == null || getClass() != o.getClass() )
            return false;
        Currency currency = (Currency)o;
        return fractionDigits == currency.fractionDigits && Objects.equals(symbol, currency.symbol);
    }

    @Override public int hashCode()
    {
        return Objects.hash(symbol, fractionDigits);
    }

    @Override public String toString()
    {
        return "Currency{" + "symbol='" + symbol + '\'' + ", fractionDigits=" + fractionDigits + '}';
    }

    private Currency( String symbol, int fractionDigits )
    {
        this.symbol = symbol;
        this.fractionDigits = fractionDigits;
    }

}
