package com.albert;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Problem3Test {

    @Test
    void calculateFenceArea_returnsCorrectArea_whenMultipleAnimals() {
        String[] animals = {
            ".....",
            "..a..",
            "....a",
            ".a..."
        };
        assertThat(Problem3.calculateFenceArea(animals)).isEqualTo(6);
    }

    @Test
    void calculateFenceArea_returnsCorrectArea_whenSingleAnimal() {
        String[] animals = {
            ".....",
            "..a..",
            ".....",
            "....."
        };
        assertThat(Problem3.calculateFenceArea(animals)).isEqualTo(1);
    }

    @Test
    void calculateFenceArea_returnsCorrectArea_whenAnimalsInLine() {
        String[] animals = {
            ".....",
            "aaaaa",
            ".....",
            "....."
        };
        assertThat(Problem3.calculateFenceArea(animals)).isEqualTo(5);
    }

    @Test
    void calculateFenceArea_returnsCorrectArea_whenAnimalsInRectangle() {
        String[] animals = {
            ".....",
            ".aaa.",
            ".aaa.",
            "....."
        };
        assertThat(Problem3.calculateFenceArea(animals)).isEqualTo(6);
    }
}
