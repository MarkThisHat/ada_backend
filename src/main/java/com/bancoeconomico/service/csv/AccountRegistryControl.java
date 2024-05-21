package com.bancoeconomico.service.csv;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AccountRegistryControl {
    private static final Set<Integer> usedNumbers = Collections.synchronizedSet(new HashSet<>());

    public static int generateUniqueNumber() {
        Random random = new Random();
        int number;
        do {
            number = random.nextInt(Integer.MAX_VALUE);
        } while (usedNumbers.contains(number));
        usedNumbers.add(number);
        return number;
    }
}
