package pl.put.poznan.madness.logic.sorting.strategies;

import java.util.Arrays;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingStrategy;

public class JavaSort implements SortingStrategy {

  @Override
  public <T extends Comparable<? super T>> void sort(T[] a) {
    Arrays.sort(a);
  }
}
