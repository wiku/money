package com.wiku.money;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.Data;

@Data public class Money
{
    public static final String SUBTRACT_CURRENCY_EXCEPTION_FORMAT = "Subtraction of different currencies is not allowed. Attempted to subtract %s from %s";
    public static final String ADD_CURRENCY_EXCEPTION_FORMAT = "Adding different currencies is not allowed. Attempted to add %s to %s";

    private final BigDecimal amount;
    private final Currency currency;

    public static Money of( String amountAsString, Currency currency )
    {
        BigDecimal amount = new BigDecimal(amountAsString);
        return Money.of(amount, currency);
    }

    public static Money of( BigDecimal amount, Currency currency )
    {
        assertAmountStringMatchesCurrencyPrecision(amount, currency);
        return new Money(amount.setScale(currency.getFractionDigits(), RoundingMode.HALF_DOWN), currency);
    }

    public Money plus( Money moneyToAdd )
    {
        assertSameCurrency(moneyToAdd, ADD_CURRENCY_EXCEPTION_FORMAT);
        return Money.of(amount.add(moneyToAdd.amount), currency);
    }

    public Money minus( Money moneyToSubtract )
    {
        assertSameCurrency(moneyToSubtract, SUBTRACT_CURRENCY_EXCEPTION_FORMAT);
        return Money.of(amount.subtract(moneyToSubtract.amount), currency);
    }

    public Money divideBy( BigDecimal factor )
    {
        return divideBy(factor, RoundingMode.HALF_DOWN);
    }

    public Money divideBy( BigDecimal factor, RoundingMode roundingMode )
    {
        return Money.of(amount.divide(factor, roundingMode), currency);
    }

    public Money multiplyBy( BigDecimal multiplier )
    {
        return multiplyBy(multiplier, RoundingMode.HALF_DOWN);
    }

    public Money multiplyBy( BigDecimal multiplier, RoundingMode roundingMode )
    {
        return Money.of(amount.multiply(multiplier).setScale(currency.getFractionDigits(), roundingMode), currency);
    }

    @Override public String toString()
    {
        return amount.toPlainString() + " " + currency.getSymbol();
    }

    private static void assertAmountStringMatchesCurrencyPrecision( BigDecimal amount, Currency currency )
    {
        if( amount.scale() > currency.getFractionDigits() )
        {
            throw new IllegalArgumentException(
                    "Money amount " + amount.toPlainString() + " does not match required currency precision: "
                            + currency.getFractionDigits());
        }
    }

    private void assertSameCurrency( Money money, String subtractExceptionMessageFormat )
    {
        if( !money.currency.equals(currency) )
        {
            throw new IllegalArgumentException(String.format(subtractExceptionMessageFormat, money, this));
        }
    }

}
