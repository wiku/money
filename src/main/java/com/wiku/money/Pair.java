package com.wiku.money;

import java.util.Objects;

public class Pair
{
    private final Currency base;
    private final Currency quote;

    public static Pair of( Currency base, Currency quote )
    {
        return new Pair(base, quote);
    }

    private Pair( Currency base, Currency quote )
    {
        this.base = base;
        this.quote = quote;
    }

    public Currency getBase()
    {
        return base;
    }

    public Currency getQuote()
    {
        return quote;
    }

    @Override public boolean equals( Object o )
    {
        if( this == o )
            return true;
        if( o == null || getClass() != o.getClass() )
            return false;
        Pair pair = (Pair)o;
        return Objects.equals(base, pair.base) && Objects.equals(quote, pair.quote);
    }

    @Override public int hashCode()
    {
        return Objects.hash(base, quote);
    }

    @Override public String toString()
    {
        return base.getSymbol() + quote.getSymbol();
    }
}
