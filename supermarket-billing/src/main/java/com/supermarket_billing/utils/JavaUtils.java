package com.supermarket_billing.utils;

import java.util.Arrays;

public class JavaUtils {
    public String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
}
