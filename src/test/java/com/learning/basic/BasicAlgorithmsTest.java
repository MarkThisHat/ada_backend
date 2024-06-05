package com.learning.basic;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BasicAlgorithmsTest {


    @BeforeAll
    static void beforeAllMethod() {
        System.out.println("Before ALL");
    }

    @BeforeEach
    void setup() {
        System.out.println("Before each");
    }

    @AfterEach
    void tearDown() {
        System.out.println("After each");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After All");
    }

    @Test
    @DisplayName("Dado um numero par, espero que retorne TRUE")
    void verificaNumeroParTeste() {
        //given --dado
        var sut = new BasicAlgorithms();
        var numero = 2;
        var expected = true;

        //when --quando
        var actual = sut.isEven(numero);

        //then  --entao
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Dado um numero ímpar, espero que retorne FALSE")
    void verificaNumeroImparTeste() {
        //given --dado
        var sut = new BasicAlgorithms();
        var numero = 3;
        var expected = false;

        //when --quando
        var actual = sut.isEven(numero);

        //then  --entao
        assertEquals(expected, actual);
    }


    @Test
    void dado_numero_par_isEven_retorna_TRUE() {
        //given --dado
        var sut = new BasicAlgorithms();
        var numero = 2;

        //when --quando
        var actual = sut.isEven(numero);

        //then  --entao
        assertTrue(actual);
    }


    public static Stream<Arguments> fatorialArgumentsSource() {
        return Stream.of(
                Arguments.of(0, 1),
                Arguments.of(5, 120),
                Arguments.of(12, 479001600)
                        );
    }

    @ParameterizedTest
    @MethodSource("fatorialArgumentsSource")
    void calcular_fatorial_ok(int numero, int expected) {
        //given
        // when
        var actual = BasicAlgorithms.calculaFatorial(numero);

        // then
        assertEquals(expected, actual);
    }


    public static Stream<Arguments> fatorialRuntimeExceptionArguments() {
        return Stream.of(
                Arguments.of(-1),
                Arguments.of(13)
                        );
    }


    @ParameterizedTest
    @DisplayName("Lancar runtime")
    @MethodSource("fatorialRuntimeExceptionArguments")
    void validar_fatorial_runtime(Integer numero) {
        //given
        final var expected = RuntimeException.class;

        //when then
        assertThrows(expected, () -> BasicAlgorithms.calculaFatorial(numero));
    }
}