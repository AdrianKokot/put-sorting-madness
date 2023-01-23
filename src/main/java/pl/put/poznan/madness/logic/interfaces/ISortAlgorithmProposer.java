package pl.put.poznan.madness.logic.interfaces;

import java.util.List;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingAlgorithm;

public interface ISortAlgorithmProposer {

  <T> SortingAlgorithm proposeAlgorithm(List<T> data);
}
