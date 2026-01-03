package com.trf5.jus.br.sgc.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public  class MonetaryUtils {

    private static final int SCALE = 2;
    private MonetaryUtils() {}

    public static BigDecimal zero() {
        return BigDecimal.ZERO.setScale(SCALE, RoundingMode.HALF_UP);
    }

    public static BigDecimal ofNullable(BigDecimal value) {
        if (value == null) return zero();
        return value.setScale(SCALE, RoundingMode.HALF_UP);
    }

    public static BigDecimal add(BigDecimal first, BigDecimal second) {
        return ofNullable(first).subtract(ofNullable(second));
    }
}
