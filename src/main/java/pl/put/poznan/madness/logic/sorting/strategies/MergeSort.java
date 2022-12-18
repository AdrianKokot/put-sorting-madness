package pl.put.poznan.madness.logic.sorting.strategies;

import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingStrategy;

import java.util.Arrays;

public class MergeSort implements SortingStrategy {

  @Override
  public <T extends Comparable<? super T>> void sort(T[] a) {
    if (a.length > 1) {
      T[] left = Arrays.copyOfRange(a, 0, a.length / 2);
      T[] right = Arrays.copyOfRange(a, a.length / 2, a.length);

      sort(left);
      sort(right);

      merge(a, left, right);
    }
  }

  private static <T extends Comparable<? super T>> void merge(T[] result, T[] left, T[] right) {
    int i = 0;
    int j = 0;
    int k = 0;

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
