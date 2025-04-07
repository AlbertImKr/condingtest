package com.albert;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Problem2Test {

    @Test
    void minMaxDistance_returnsCorrectDistance_whenTwoLockers() {
        int[] houses = {1, 2, 3, 10};
        int k = 2;
        assertThat(Problem2.minMaxDistance(houses, k)).isEqualTo(1);
    }

    @Test
    void minMaxDistance_returnsCorrectDistance_whenThreeLockers() {
        int[] houses = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int k = 3;
        assertThat(Problem2.minMaxDistance(houses, k)).isEqualTo(2);
    }

    @Test
    void minMaxDistance_returnsCorrectDistance_whenOneLocker() {
        int[] houses = {2, 10, 100};
        int k = 1;
        assertThat(Problem2.minMaxDistance(houses, k)).isEqualTo(90);
    }

    @Test
    void minMaxDistance_returnsZero_whenLockersEqualHouses() {
        int[] houses = {1, 2, 3, 4, 5};
        int k = 5;
        assertThat(Problem2.minMaxDistance(houses, k)).isEqualTo(0);
    }

    @Test
    void minMaxDistance_returnsCorrectDistance_whenLockersLessThanHouses() {
        int[] houses = {1, 3, 6, 10, 15};
        int k = 2;
        assertThat(Problem2.minMaxDistance(houses, k)).isEqualTo(5);
    }
}
