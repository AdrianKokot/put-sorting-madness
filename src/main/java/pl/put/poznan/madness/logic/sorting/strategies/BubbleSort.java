package pl.put.poznan.madness.logic.sorting.strategies;

import pl.put.poznan.madness.logic.sorting.strategies.boundary.AbstractSort;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortDirection;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingStrategy;

public class BubbleSort extends AbstractSort implements SortingStrategy {

  @Override
  public <T extends Comparable<? super T>> void sort(T[] a, SortDirection sortDirection) {
    boolean swapped = true;
    int i = a.length - 1;
    while (swapped && i >= 0) {
      swapped = false;
      for (int j = 0; j < i; j++) {
        if ((SortDirection.ASC.equals(sortDirection) && a[j].compareTo(a[j + 1]) > 0) || (!SortDirection.ASC.equals(sortDirection) && a[j].compareTo(a[j + 1]) < 0)) {
          swap(a, j, j + 1);
          swapped = true;
        }
      }
      i--;
    }
  }

  @Override
  public <T extends Comparable<? super T>> void sort(T[] a, SortDirection sortDirection, int maxIterations) {
    boolean swapped = true;
    int i = a.length - 1;
    int iteration = 0;
    while (swapped && i >= 0 && iteration < maxIterations) {
      swapped = false;
      for (int j = 0; j < i; j++) {
        if ((SortDirection.ASC.equals(sortDirection) && a[j].compareTo(a[j + 1]) > 0) || (!SortDirection.ASC.equals(sortDirection) && a[j].compareTo(a[j + 1]) < 0)) {
          swap(a, j, j + 1);
          swapped = true;
        }
      }
      i--;
      iteration++;
    }
  }
}

