package pl.put.poznan.madness.logic;

import org.springframework.stereotype.Component;
import pl.put.poznan.madness.logic.interfaces.ISortAlgorithmProposer;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingAlgorithm;

import java.util.List;

@Component
public class SortAlgorithmProposerImpl implements ISortAlgorithmProposer {

  @Override
  public <T> SortingAlgorithm proposeAlgorithm(List<T> data) {
    int size = data.size();

    if (size <= 10) {
      return SortingAlgorithm.INSERTION_SORT;
    }
    else {
      return SortingAlgorithm.QUICK_SORT;
    }
  }
}
