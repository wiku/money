package com.wiku.money;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.Data;

@Data public class ExchangeRate
{
    private final BigDecimal rate;
    private final Pair pair;

    public static ExchangeRate of( String rate, Pair pair )
    {
        return new ExchangeRate(new BigDecimal(rate), pair);
    }

    public Money convert( Money money )
    {
        if( money.getCurrency().equals(pair.getBase()) )
        {
            return new Money(money.getAmount()
                    .multiply(rate)
                    .setScale(pair.getQuote().getFractionDigits(), RoundingMode.DOWN), pair.getQuote());
        }
        else if( money.getCurrency().equals(pair.getQuote()) )
        {
            return new Money(money.getAmount()
                    .setScale(pair.getBase().getFractionDigits(), RoundingMode.DOWN)
                    .divide(rate, RoundingMode.DOWN), pair.getBase());
        }
        else
        {
            throw new IllegalArgumentException("Cannot convert " + money.getCurrency() + " using rate for " + pair);
        }
    }

    public String toString()
    {
        return String.format("%s(%s)", rate.toPlainString(), pair);
    }
}
