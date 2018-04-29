# money
Basic money API written in Java

This is an example API to perform safe money and currency operations.

Supports:
1. Defining currencies
```
Currency usd = Currency.of("USD", 2);
Currency eur = Currency.of("EUR", 2);
```
2. Money abstraction:
Money usd10 = Money.of("10.00", usd);

2. Arithmetic operations (add, subtract, divide, multiply) on money, backed by BigDecimal:
```
Money usd1 = usd10.divide(new BigDecimal(10));
```

3. Defining currency pairs and exchange rates for the purpose of conversion between currencies:
```
Pair eurUsd = Pair.of(eur, usd);
ExchangeRate eurUsdRate = ExchangeRate.of("1.22546", eurUsd);
Money eurMoney = eurUsdRate.convert(Money.of("2.50", usd));;
```