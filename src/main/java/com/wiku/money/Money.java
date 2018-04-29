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
        BigDecimal amount = new BigDecimal(amountAsString);
        assertAmountStringMatchesCurrencyPrecision(amountAsString, currency, amount);
        return new Money(amount.setScale(currency.getFractionDigits(), RoundingMode.HALF_DOWN), currency);
    }

    private static void assertAmountStringMatchesCurrencyPrecision( String amountAsString,
            Currency currency,
            BigDecimal amount )
    {
        if( amount.scale() > currency.getFractionDigits() )
        {
            throw new IllegalArgumentException(
                    "Money amount " + amountAsString + " does not match required currency precision: "
                            + currency.getFractionDigits());
        }
    }

    public Money divideBy( BigDecimal factor )
    {
        return divideBy(factor, RoundingMode.HALF_DOWN);
    }

    public Money divideBy( BigDecimal factor, RoundingMode roundingMode )
    {
        return new Money(amount.divide(factor, roundingMode), currency);
    }

    public Money multiplyBy( BigDecimal multiplier )
    {
        return multiplyBy(multiplier, RoundingMode.HALF_DOWN);
    }

    public Money multiplyBy( BigDecimal multiplier, RoundingMode roundingMode )
    {
        return new Money(amount.multiply(multiplier).setScale(currency.getFractionDigits(), roundingMode),
                currency);
    }

    public Money minus( Money money )
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

    public Money plus( Money moneyToAdd )
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
