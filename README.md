# money
My own simple API to perform basic money and currency operations.

### Features:
1. Defining currencies:
```
Currency usd = Currency.of("USD", 2);
Currency eur = Currency.of("EUR", 2);
```

This allows you to easily define yor own custom currencies, eg. bitcoin:
```
Currency btc = Currency.of("BTC", 8);
```

2. Money abstraction:
```
Money usd10 = Money.of("10.00", usd);
```

2. Arithmetic operations on money (add, subtract, divide, multiply), backed by BigDecimal precision and currency rounding:
```
Money usd11 = usd10.plus(usd1);;
Money usd1 = usd10.divideBy(new BigDecimal(10));
Money multipliedByAndRundedUp = usd10.multiplyBy(new BigDecimal(3.1245), RoundingMode.UP);
```

3. Defining currency pairs and exchange rates for the purpose of conversion between currencies:
```
Pair eurUsd = Pair.of(eur, usd);
ExchangeRate eurUsdRate = ExchangeRate.of("1.22546", eurUsd);
Money eurMoney = eurUsdRate.convert(Money.of("2.50", usd));
```