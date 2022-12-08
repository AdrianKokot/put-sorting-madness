package pl.put.poznan.madness.rest.models;

import java.util.List;

import pl.put.poznan.madness.logic.SortingAlgorithm;

public class SortingInput<T> {
  public List<T> data;
  public SortingAlgorithm algorithm = SortingAlgorithm.Bubble;
  public String property = "";

  @Override
  public String toString() {

    return String.format("SortingInput '%s' with '%s' algorithm %s", data.toString(), algorithm,
        property.isEmpty() ? "" : String.format("on '%s' property", property));
  }
}
