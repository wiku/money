package com.wiku.money;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
        return convert(money, RoundingMode.HALF_DOWN);
    }

    public Money convert( Money money, RoundingMode roundingMode )
    {
        Currency currency = money.getCurrency();
        
        if( isBaseCurrency(currency) )
        {
            return Money.of(money.getAmount()
                    .multiply(rate)
                    .setScale(pair.getQuote().getFractionDigits(), roundingMode), pair.getQuote());
        }
        else if( isQuoteCurrency(currency) )
        {
            return Money.of(money.getAmount()
                    .setScale(pair.getBase().getFractionDigits(), roundingMode)
                    .divide(rate, roundingMode), pair.getBase());
        }
        else
        {
            throw new IllegalArgumentException("Cannot convert " + currency + " using rate for " + pair);
        }
    }

    private boolean isQuoteCurrency( Currency currency )
    {
        return currency.equals(pair.getQuote());
    }

    private boolean isBaseCurrency( Currency currency )
    {
        return currency.equals(pair.getBase());
    }

    public String toString()
    {
        return String.format("%s(%s)", rate.toPlainString(), pair);
    }
}
