package com.wiku.money;

import lombok.Data;

@Data public class Pair
{
    private final Currency base;
    private final Currency quote;

    public static Pair of( Currency base, Currency quote )
    {
        return new Pair(base, quote);
    }

    @Override public String toString()
    {
        return base.getSymbol() + quote.getSymbol();
    }
}
