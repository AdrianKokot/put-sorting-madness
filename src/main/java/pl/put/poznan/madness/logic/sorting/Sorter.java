package pl.put.poznan.madness.logic.sorting;

import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingStrategy;

/**
 * Utility class that provides a method for sorting an array using a given {@link SortingStrategy} implementation.
 *
 * This class allows sorting an array by simply providing the desired sorting strategy, without having to worry about
 * the details of the sorting algorithm itself.
 */
public class Sorter {

  private final SortingStrategy sortingStrategy;

  /**
   * Constructs a new {@link Sorter} object with the given {@link SortingStrategy} implementation.
   *
   * @param sortingStrategy the {@link SortingStrategy} implementation to be used by this {@link Sorter} object
   */
  public Sorter(SortingStrategy sortingStrategy) {
    this.sortingStrategy = sortingStrategy;
  }

  /**
   * Sorts the given array using the {@link SortingStrategy} implementation provided in the constructor.
   *
   * @param a the array to be sorted
   * @param <T> the type of the elements in the array, must implement {@link Comparable}
   */
  public <T extends Comparable<? super T>> void sort(T[] a) {
    sortingStrategy.sort(a);
  }

}
