package pl.put.poznan.madness.rest.models;

import java.util.List;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingAlgorithm;

public class SortDto {

  public List<SortingAlgorithm> algorithms = List.of();
  public List<Object> data = List.of();
  public String orderBy = "";
}
