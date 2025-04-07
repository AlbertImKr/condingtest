package com.albert;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Problem1Test {

    @Test
    void minOperations_returnsZero_whenAEqualsB() {
        assertThat(Problem1.minOperations(5, 5, 2)).isZero();
    }

    @Test
    void minOperations_returnsCorrectSteps_whenAtoBWithMaxOperations() {
        assertThat(Problem1.minOperations(2, 32, 2)).isEqualTo(2);
    }

    @Test
    void minOperations_returnsCorrectSteps_whenAtoBWithLimitedOperations() {
        assertThat(Problem1.minOperations(2, 32, 1)).isEqualTo(3);
    }

    @Test
    void minOperations_returnsMinusOne_whenAtoBNotPossible() {
        assertThat(Problem1.minOperations(2, 33, 1)).isEqualTo(-1);
    }

    @Test
    void minOperations_returnsCorrectSteps_whenAtoBWithDifferentOperations() {
        assertThat(Problem1.minOperations(2, 64, 2)).isEqualTo(3);
    }
}
