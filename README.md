# money
My own minimalistic API to perform basic money and currency operations.

### Features / usage:
1. Defining currencies.
No pre-existing list of currencies exists, but it's easy to create them if needed, providing a symbol and number of fraction digits (precision, eg. 2 for USD, EUR, PLN and most currencies):
```
Currency usd = Currency.of("USD", 2);
Currency eur = Currency.of("EUR", 2);
```
This also allows you to easily define yor own custom currencies, like bitcoin or other cryptocurrencies:
```
Currency btc = Currency.of("BTC", 8);
```

2. Money abstraction.
To represent 10 U.S. dollars:
```
Money usd10 = Money.of("10.00", usd);
```

2. Arithmetic operations on money.
You can add, subtract, divide, multiply money, which is backed by BigDecimal precision and currency rounding (default rounding is HALF_DOWN):
```
Money usd11 = usd10.plus(usd1);;
Money usd1 = usd10.divideBy(new BigDecimal(10));
Money multipliedByAndRundedUp = usd10.multiplyBy(new BigDecimal(3.1245), RoundingMode.UP);
```

3. Defining currency pairs and exchange rates for the purpose of conversion between currencies.
Create a pair of two currencies, then define a new ExhangeRate to do the conversion:
```
Pair eurUsd = Pair.of(eur, usd);
ExchangeRate eurUsdRate = ExchangeRate.of("1.22546", eurUsd);
Money eurMoney = eurUsdRate.convert(Money.of("2.50", usd));
```
