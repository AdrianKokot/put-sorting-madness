package pl.put.poznan.madness.rest.models;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import pl.put.poznan.madness.logic.SortingAlgorithm;
import pl.put.poznan.madness.rest.exceptions.InvalidSortInputException;
import pl.put.poznan.madness.rest.exceptions.MissingPropertyKeyException;
import pl.put.poznan.madness.rest.exceptions.NotAllCustomObjectHaveSortablePropertyException;

public class SortingInput {
  public List<Object> data;
  public List<SortingAlgorithm> algorithms = List.of(SortingAlgorithm.Bubble);
  public String property = "";

  private boolean isPrimitiveDataInput() throws InvalidSortInputException {
    if (data.stream().anyMatch(Map.class::isInstance)) {

      if (property.isEmpty()) {
        throw new MissingPropertyKeyException();
      }

      long numberOfCorrectObjectsInInput = this.data.stream()
          .filter(x -> x instanceof Map && ((Map) x).containsKey(property))
          .count();

      if (numberOfCorrectObjectsInInput != data.size()) {
        throw new NotAllCustomObjectHaveSortablePropertyException(property);
      }

      return false;
    }

    return true;
  }

  public List<SortableItem> getSortableItems() throws InvalidSortInputException {
    Stream<SortableItem> stream;

    if (isPrimitiveDataInput()) {
      stream = data.stream()
          .map(x -> new SortableItem(x, x.toString()));
    } else {
      stream = data.stream()
          .map(x -> (Map) x)
          .map(x -> new SortableItem(x, x.get(property).toString()));
    }

    return stream
        .collect(Collectors.toList());
  }
}
