package dev.agentelf.variation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VariationIteratorTest {

    @Test
    void shouldCreateAllCombinations() {
        VariationIterator iterator = new VariationIterator(new Iterable[]{
                List.of("A", "B"),
                List.of(1, 2),
                List.of("X", "Y")
        });
        List<String> results = new java.util.ArrayList<>();
        while (iterator.hasNext()) {
            results.add(Arrays.toString(iterator.next()));
        }
        List<String> expected = List.of(
                "[A, 1, X]",
                "[A, 1, Y]",
                "[A, 2, X]",
                "[A, 2, Y]",
                "[B, 1, X]",
                "[B, 1, Y]",
                "[B, 2, X]",
                "[B, 2, Y]"
        );
        assertEquals(expected, results);
    }

    @Test
    void shouldReturnFalseForEmptyVariation() {
        VariationIterator iterator = new VariationIterator(new Iterable[]{
                List.of("A", "B"),
                List.of(),
                List.of("X", "Y")
        });
        assertFalse(iterator.hasNext());
    }

    @Test
    void shouldReturnSingleCombination() {
        VariationIterator iterator = new VariationIterator(new Iterable[]{
                List.of("A"),
                List.of(1),
                List.of("X")
        });
        assertTrue(iterator.hasNext());
        Object[] result = iterator.next();
        assertArrayEquals(new Object[]{"A", 1, "X"}, result);
        assertFalse(iterator.hasNext());
    }

    @Test
    void shouldThrowExceptionWhenNoMoreElements() {
        VariationIterator iterator = new VariationIterator(new Iterable[]{
                List.of("A")
        });
        iterator.next();
        assertThrows(
                java.util.NoSuchElementException.class,
                iterator::next
        );
    }

    @Test
    void shouldWorkWithTwoDimensions() {
        VariationIterator iterator = new VariationIterator(new Iterable[]{
                List.of("A", "B"),
                List.of(1, 2, 3)
        });
        List<String> results = new java.util.ArrayList<>();
        while (iterator.hasNext()) {
            results.add(Arrays.toString(iterator.next()));
        }
        List<String> expected = List.of(
                "[A, 1]",
                "[A, 2]",
                "[A, 3]",
                "[B, 1]",
                "[B, 2]",
                "[B, 3]"
        );
        assertEquals(expected, results);
    }
}
