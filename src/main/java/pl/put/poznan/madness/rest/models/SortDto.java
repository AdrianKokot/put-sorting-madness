package pl.put.poznan.madness.rest.models;

import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingAlgorithm;

import java.util.List;

public class SortDto {

  public List<SortingAlgorithm> algorithms;
  public List<Object> data;
  public String orderBy;
}
