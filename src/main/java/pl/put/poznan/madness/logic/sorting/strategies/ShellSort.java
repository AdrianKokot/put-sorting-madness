package pl.put.poznan.madness.logic.sorting.strategies;

import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortDirection;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingStrategy;

public class ShellSort implements SortingStrategy {

  @Override
  public <T extends Comparable<? super T>> void sort(T[] a, SortDirection sortDirection) {
    int h = 1;
    while ((h * 3 + 1) < a.length) h = 3 * h + 1;
    while (h > 0) {
      for (int i = h - 1; i < a.length; i++) {
        T s = a[i];
        int j = i;
        if (SortDirection.ASC.equals(sortDirection)) {
          for (j = i; (j >= h) && (a[j - h].compareTo(s) > 0); j -= h) a[j] = a[j - h];
        } else {
          for (j = i; (j >= h) && (a[j - h].compareTo(s) < 0); j -= h) a[j] = a[j - h];
        }
        a[j] = s;
      }
      h /= 3;
    }
  }

  @Override
  public <T extends Comparable<? super T>> void sort(T[] a, SortDirection sortDirection, int iterationsCount) {
    int h = 1;
    while ((h * 3 + 1) < a.length) h = 3 * h + 1;
    while (h > 0) {
      for (int i = h - 1; i < a.length; i++) {
        T s = a[i];
        int j = i;
        int counter = 0;
        if (SortDirection.ASC.equals(sortDirection)) {
          for (j = i; (j >= h) && (a[j - h].compareTo(s) > 0) && counter < iterationsCount; j -= h) {
            a[j] = a[j - h];
            counter++;
          }
        } else {
          for (j = i; (j >= h) && (a[j - h].compareTo(s) < 0) && counter < iterationsCount; j -= h) {
            a[j] = a[j - h];
            counter++;
          }
        }
        a[j] = s;
      }
      h /= 3;
    }
  }
}
