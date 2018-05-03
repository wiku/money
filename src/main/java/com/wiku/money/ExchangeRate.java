package com.wiku.money;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class ExchangeRate
{
    private final BigDecimal rate;
    private final Pair pair;

    public static ExchangeRate of( String rate, Pair pair )
    {
        return of(new BigDecimal(rate), pair);
    }

    public static ExchangeRate of( BigDecimal rate, Pair pair )
    {
        return new ExchangeRate(rate, pair);
    }

    public static ExchangeRate of( Money amount, Money worth, int decimalPrecision )
    {
        return ExchangeRate.of(worth.getAmount()
                .setScale(decimalPrecision)
                .divide(amount.getAmount(), RoundingMode.HALF_DOWN),
                Pair.of(amount.getCurrency(), worth.getCurrency()));
    }

    public BigDecimal getRate()
    {
        return rate;
    }

    public Pair getPair()
    {
        return pair;
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

    @Override public boolean equals( Object o )
    {
        if( this == o )
            return true;
        if( o == null || getClass() != o.getClass() )
            return false;
        ExchangeRate that = (ExchangeRate)o;
        return Objects.equals(rate, that.rate) && Objects.equals(pair, that.pair);
    }

    @Override public int hashCode()
    {

        return Objects.hash(rate, pair);
    }

    public String toString()
    {
        return String.format("%s(%s)", rate.toPlainString(), pair);
    }

    private ExchangeRate( BigDecimal rate, Pair pair )
    {
        this.rate = rate;
        this.pair = pair;
    }

    private boolean isQuoteCurrency( Currency currency )
    {
        return currency.equals(pair.getQuote());
    }

    private boolean isBaseCurrency( Currency currency )
    {
        return currency.equals(pair.getBase());
    }


}
