# money
Basic money API written in Java

This is an example API to perform safe money and currency operations.

Supports:
1. Defining currencies
```
Currency USD = Currency.of("PLN",2);
```

2. Accurate operations (add, subtract, divide, multiply) on money, backed by BigDecimal.
```
Money usd1 = usd10.divide(new BigDecimal(10));
```

3. Defining exchange rates and conversion between currencies.
```
ExchangeRate eurUsdRate = ExchangeRate.of("1.22546", EURUSD);
eurUsdRate.convert(Money.of("2.50",USD));

```