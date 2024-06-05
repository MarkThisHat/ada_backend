package com.learning.test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SecondLargestNumberTest {


    public static Stream<Arguments> secondLargestNumberArguments() {
        return Stream.of(
                Arguments.of(new int[]{3, 6, 8, 1, 9, 2}, 8),
                Arguments.of(new int[]{5, 5, 5, 5}, null),
                Arguments.of(new int[]{1, 2, 3}, 2)
                        );
    }

    @ParameterizedTest
    @MethodSource("secondLargestNumberArguments")
    void findSecondLargestNumberTest(int[] numbers, Integer expected) {
        // given
        // when
        var actual = SecondLargestNumber.findSecondLargest(numbers);

        // then
        assertEquals(expected, actual);
    }


}