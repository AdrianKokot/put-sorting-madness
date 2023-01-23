package pl.put.poznan.madness.logic;

import java.util.List;
import org.springframework.stereotype.Component;
import pl.put.poznan.madness.logic.interfaces.ISortAlgorithmProposer;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingAlgorithm;

/**
 * SortAlgorithmProposerImpl is a class that implements the ISortAlgorithmProposer interface. It
 * proposes a sorting algorithm for a given list of data based on the data.
 *
 * @see pl.put.poznan.madness.logic.interfaces.ISortAlgorithmProposer
 * @see pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingAlgorithm
 */
@Component
public class SortAlgorithmProposerImpl implements ISortAlgorithmProposer {
  /**
   * Proposes a sorting algorithm for a given list of data based on its size.
   *
   * @param data the list of data to sort
   * @return the proposed sorting algorithm
   */
  @Override
  public <T> SortingAlgorithm proposeAlgorithm(List<T> data) {
    int size = data.size();

    if (size <= 10) {
      return SortingAlgorithm.INSERTION_SORT;
    } else {
      return SortingAlgorithm.QUICK_SORT;
    }
  }
}
