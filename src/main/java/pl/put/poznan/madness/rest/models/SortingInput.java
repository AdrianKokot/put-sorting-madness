package pl.put.poznan.madness.rest.models;

import java.util.List;

import pl.put.poznan.madness.logic.SortingAlgorithm;

public class SortingInput<T> {
  public List<T> data;
  public SortingAlgorithm algorithm;
  public String property;
}
