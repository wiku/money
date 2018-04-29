package com.wiku.money;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.Data;

@Data public class Money
{
    private final BigDecimal amount;
    private final Currency currency;

    public static Money of( String amountAsString, Currency currency )
    {
        return new Money(new BigDecimal(amountAsString).setScale(currency.getFractionDigits(), RoundingMode.HALF_DOWN),
                currency);
    }

    public Money divide( BigDecimal factor )
    {
        return new Money(amount.divide(factor), currency);
    }

    public Money multiply( BigDecimal multiplier )
    {
        return new Money(amount.multiply(multiplier).setScale(currency.getFractionDigits(), RoundingMode.DOWN),
                currency);
    }

    public Money subtract( Money money )
    {
        if( money.currency.equals(currency) )
        {
            return new Money(amount.subtract(money.amount), currency);
        }
        else
        {
            throw new IllegalArgumentException(
                    "Subtraction of different currencies is not allowed. Attempted to subtract " + money + " from "
                            + this);
        }
    }

    @Override public String toString()
    {
        return amount.toPlainString() + " " + currency.getSymbol();
    }

    public Money add( Money moneyToAdd )
    {
        if( moneyToAdd.currency.equals(currency) )
        {
            return new Money(amount.add(moneyToAdd.amount), currency);
        }
        else
        {
            throw new IllegalArgumentException(
                    "Adding different currencies is not allowed. Attempted to add " + moneyToAdd + " to " + this);
        }
    }
}
