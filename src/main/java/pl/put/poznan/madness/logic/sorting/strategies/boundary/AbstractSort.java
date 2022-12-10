package pl.put.poznan.madness.logic.sorting.strategies.boundary;

public abstract class AbstractSort {

  /**
   * Swap the contents of a[i] and a[j]
   */
  protected void swap(Object[] a, int i, int j) {
    Object tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }
}
