package pl.put.poznan.madness.logic.sorting.strategies;

import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingStrategy;

import java.util.Arrays;

public class JavaSort implements SortingStrategy {

  @Override
  public <T extends Comparable<? super T>> void sort(T[] a) {
    Arrays.sort(a);
  }
}
