package com.wiku.money;
import org.junit.Test;

import static org.junit.Assert.*;

public class PairTest
{
    private static Pair USDPLN = Pair.of(Currency.of("USD", 2), Currency.of("PLN", 2));

    @Test
    public void canGetSymbolOfPair()
    {
        assertEquals("USDPLN", USDPLN.toString());
    }


}
