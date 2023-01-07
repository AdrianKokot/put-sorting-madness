package pl.put.poznan.madness.logic.sorting.strategies;

import java.util.Arrays;

import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortDirection;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingStrategy;

public class MergeSort implements SortingStrategy {

  @Override
  public <T extends Comparable<? super T>> void sort(T[] a, SortDirection sortDirection) {
    if (a.length > 1) {
      T[] left = Arrays.copyOfRange(a, 0, a.length / 2);
      T[] right = Arrays.copyOfRange(a, a.length / 2, a.length);

      sort(left, sortDirection);
      sort(right, sortDirection);

      merge(a, left, right, sortDirection);
    }
  }

  @Override
  public <T extends Comparable<? super T>> void sort(T[] a, SortDirection sortDirection, int maxIterations) {
    if (a.length > 1 && maxIterations > 0) {
      T[] left = Arrays.copyOfRange(a, 0, a.length / 2);
      T[] right = Arrays.copyOfRange(a, a.length / 2, a.length);

      sort(left, sortDirection, maxIterations - 1);
      sort(right, sortDirection, maxIterations - 1);

      merge(a, left, right, sortDirection);
    }
  }

  private static <T extends Comparable<? super T>> void merge(T[] result, T[] left, T[] right, SortDirection sortDirection) {
    int i = 0;
    int j = 0;
    int k = 0;

    if (SortDirection.ASC.equals(sortDirection)) {
      while (i < left.length && j < right.length) {
        if (left[i].compareTo(right[j]) <= 0) {
          result[k] = left[i];
          i++;
        } else {
          result[k] = right[j];
          j++;
        }
        k++;
      }
    } else {
      while (i < left.length && j < right.length) {
        if (left[i].compareTo(right[j]) >= 0) {
          result[k] = left[i];
          i++;
        } else {
          result[k] = right[j];
          j++;
        }
        k++;
      }
    }

    while (i < left.length) {
      result[k] = left[i];
      i++;
      k++;
    }

    while (j < right.length) {
      result[k] = right[j];
      j++;
      k++;
    }
  }
}
