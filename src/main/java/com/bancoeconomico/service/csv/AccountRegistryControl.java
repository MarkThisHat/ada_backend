package com.bancoeconomico.service.csv;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AccountRegistryControl {
    private static final Set<Long> usedNumbers = Collections.synchronizedSet(new HashSet<>());

    public static long generateUniqueNumber() {
        Random random = new Random();
        long number;
        do {
            number = random.nextLong();
        } while (usedNumbers.contains(number));
        usedNumbers.add(number);
        return number;
    }
}
