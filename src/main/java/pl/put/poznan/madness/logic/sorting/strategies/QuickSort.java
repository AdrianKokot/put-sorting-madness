package pl.put.poznan.madness.logic.sorting.strategies;

import pl.put.poznan.madness.logic.sorting.strategies.boundary.AbstractSort;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortDirection;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingStrategy;

public class QuickSort extends AbstractSort implements SortingStrategy {

  @Override
  public <T extends Comparable<? super T>> void sort(T[] a, SortDirection sortDirection) {
    sort(a, 0, a.length - 1, sortDirection);
  }

  private <T extends Comparable<? super T>> void sort(T[] a, int s_low, int s_high, SortDirection sortDirection) {
    int low = s_low;
    int high = s_high;
    if (low == high - 1) {
      if (SortDirection.ASC.equals(sortDirection)) {
        if (a[low].compareTo(a[high]) > 0) swap(a, low, high);
      } else {
        if (a[low].compareTo(a[high]) < 0) swap(a, low, high);
      }
    } else if (low < high) {
      T pivot = a[(low + high) / 2];
      a[(low + high) / 2] = a[high];
      a[high] = pivot;
      if (SortDirection.ASC.equals(sortDirection)) {
        while (low < high) {
          while ((a[low].compareTo(pivot) < 1) && (low < high)) low++;
          while ((pivot.compareTo(a[high]) < 1) && (low < high)) high--;
          if (low < high) swap(a, low, high);
        }
      } else {
        while (low < high) {
          while ((a[low].compareTo(pivot) > -1) && (low < high)) low++;
          while ((pivot.compareTo(a[high]) > -1) && (low < high)) high--;
          if (low < high) swap(a, low, high);
        }
      }
      a[s_high] = a[high];
      a[high] = pivot;
      sort(a, s_low, low - 1, sortDirection);
      sort(a, high + 1, s_high, sortDirection);
    }
  }
}
