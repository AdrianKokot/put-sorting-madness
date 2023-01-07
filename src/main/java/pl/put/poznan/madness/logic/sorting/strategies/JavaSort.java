package pl.put.poznan.madness.logic.sorting.strategies;

import java.util.Arrays;
import java.util.Collections;

import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortDirection;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingStrategy;

public class JavaSort implements SortingStrategy {

  @Override
  public <T extends Comparable<? super T>> void sort(T[] a, SortDirection sortDirection) {
    if (SortDirection.ASC.equals(sortDirection)) {
      Arrays.sort(a);
    } else {
      Arrays.sort(a, Collections.reverseOrder());
    }
  }

  @Override
  public <T extends Comparable<? super T>> void sort(T[] a, SortDirection sortDirection, int iterationsCount) {
    if (SortDirection.ASC.equals(sortDirection)) {
      Arrays.sort(a);
    } else {
      Arrays.sort(a, Collections.reverseOrder());
    }
  }
}
