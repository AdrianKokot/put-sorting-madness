package pl.put.poznan.madness.logic.interfaces;

import java.util.List;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingAlgorithm;

/**
 * ISortAlgorithmProposer is an interface for a class that proposes a sorting algorithm for a given
 * list of data.
 *
 * @see pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingAlgorithm
 */
public interface ISortAlgorithmProposer {
  /**
   * Proposes a sorting algorithm for a given list of data.
   *
   * @param data the list of data to sort
   * @return the proposed sorting algorithm
   */
  <T> SortingAlgorithm proposeAlgorithm(List<T> data);
}
