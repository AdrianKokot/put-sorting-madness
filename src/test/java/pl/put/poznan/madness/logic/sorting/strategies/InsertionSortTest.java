package pl.put.poznan.madness.logic.sorting.strategies;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortDirection;

class InsertionSortTest {

  @Test
  void shouldSortAscending() {
    InsertionSort sorter = new InsertionSort();
    Integer[] arr = new Integer[] {5, 3, 2, 1, 4};
    sorter.sort(arr, SortDirection.ASC);
    assertArrayEquals(new Integer[] {1, 2, 3, 4, 5}, arr);
  }

  @Test
  void shouldSortDescending() {
    InsertionSort sorter = new InsertionSort();
    Integer[] arr = new Integer[] {5, 3, 2, 1, 4};
    sorter.sort(arr, SortDirection.DESC);
    assertArrayEquals(new Integer[] {5, 4, 3, 2, 1}, arr);
  }

  @Test
  void shouldSortAscendingWithMaxIterations() {
    InsertionSort sorter = new InsertionSort();
    Integer[] arr = new Integer[] {5, 3, 2, 1, 4};
    sorter.sort(arr, SortDirection.ASC, 2);
    assertArrayEquals(new Integer[] {2, 3, 5, 1, 4}, arr);
  }

  @Test
  void shouldSortDescendingWithMaxIterations() {
    InsertionSort sorter = new InsertionSort();
    Integer[] arr = new Integer[] {5, 3, 2, 1, 4};
    sorter.sort(arr, SortDirection.DESC, 2);
    assertArrayEquals(new Integer[] {5, 3, 2, 1, 4}, arr);
  }
}
