package pl.put.poznan.madness.logic.sorting.strategies;

import org.junit.jupiter.api.Test;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortDirection;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class QuickSortTest {

  @Test
  void shouldSortAscending() {
    QuickSort sorter = new QuickSort();
    Integer[] arr = new Integer[]{5, 3, 2, 1, 4};
    sorter.sort(arr, SortDirection.ASC);
    assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, arr);
  }

  @Test
  void shouldSortDescending() {
    QuickSort sorter = new QuickSort();
    Integer[] arr = new Integer[]{5, 3, 2, 1, 4};
    sorter.sort(arr, SortDirection.DESC);
    assertArrayEquals(new Integer[]{5, 4, 3, 2, 1}, arr);
  }

  @Test
  void shouldSortAscendingWithMaxIterations() {
    QuickSort sorter = new QuickSort();
    Integer[] arr = new Integer[]{5, 3, 2, 1, 4};
    sorter.sort(arr, SortDirection.ASC, 2);
    assertArrayEquals(new Integer[]{1, 2, 4, 3, 5}, arr);
  }

  @Test
  void shouldSortDescendingWithMaxIterations() {
    QuickSort sorter = new QuickSort();
    Integer[] arr = new Integer[]{5, 3, 2, 1, 4};
    sorter.sort(arr, SortDirection.DESC, 2);
    assertArrayEquals(new Integer[]{5, 4, 3, 2, 1}, arr);
  }

}
