package pl.put.poznan.madness.rest.models;

import java.util.List;

import pl.put.poznan.madness.logic.SortingAlgorithm;

public class SortDto {
  public List<SortingAlgorithm> algorithms = List.of(SortingAlgorithm.Bubble);
  public List<Object> data = List.of();
  public String orderBy = "";
}
