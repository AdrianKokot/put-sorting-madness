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
        if (SortDirection.ASC.equals(sortDirection)) {
          if (a[j].compareTo(a[j + 1]) > 0) {
            swap(a, j, j + 1);
            swapped = true;
          } else {
            if (a[j].compareTo(a[j + 1]) < 0) {
              swap(a, j, j + 1);
              swapped = true;
            }
          }
          i--;
        }
      }
    }
  }
}
