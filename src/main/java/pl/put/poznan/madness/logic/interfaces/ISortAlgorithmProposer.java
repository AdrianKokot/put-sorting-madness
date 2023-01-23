package pl.put.poznan.madness.logic.interfaces;

import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingAlgorithm;

import java.util.List;

public interface ISortAlgorithmProposer {
  <T> SortingAlgorithm proposeAlgorithm(List<T> data);
}
