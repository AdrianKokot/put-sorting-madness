package pl.put.poznan.madness.logic.sorting.strategies.boundary;

public interface SortingStrategy {

  <T extends Comparable<? super T>> void sort(T[] a, SortDirection sortDirection);
}
