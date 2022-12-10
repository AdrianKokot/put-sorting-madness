package pl.put.poznan.madness.rest.utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import pl.put.poznan.madness.logic.SortingAlgorithm;
import pl.put.poznan.madness.rest.exceptions.InvalidSortInputException;
import pl.put.poznan.madness.rest.exceptions.MissingPropertyKeyException;
import pl.put.poznan.madness.rest.exceptions.NotAllCustomObjectHaveSortablePropertyException;
import pl.put.poznan.madness.rest.models.ISortableItem;
import pl.put.poznan.madness.rest.models.NumericKeySortableItem;
import pl.put.poznan.madness.rest.models.SortDto;
import pl.put.poznan.madness.rest.models.StringKeySortableItem;

public class SortDtoParser {
  private final SortDto sortDto;
  private String errorMessage = null;
  private List<ISortableItem> parsedData;

  public static SortDtoParser parse(SortDto dto) {
    return new SortDtoParser(dto);
  }

  private SortDtoParser(SortDto dto) {
    this.sortDto = dto;

    try {
      this.setup();
    } catch (Exception e) {
      this.errorMessage = e.getMessage();
    }
  }

  public List<ISortableItem> getParsedData() {
    return parsedData;
  }

  public String getMessage() {
    return errorMessage;
  }

  public boolean isValid() {
    return errorMessage == null;
  }

  private void setup() throws InvalidSortInputException {
    if (sortDto.data.size() == 0) {
      throw new InvalidSortInputException("Missing data to sort.");
    }

    List<Object> keys = sortDto.data;

    if (sortDto.data.stream().anyMatch(Map.class::isInstance)) {

      if (sortDto.orderBy.isEmpty()) {
        throw new MissingPropertyKeyException();
      }

      List<Object> itemsWithOrderableKey = sortDto.data.stream()
          .filter(x -> x instanceof Map && Map.class.cast(x).containsKey(sortDto.orderBy))
          .collect(Collectors.toList());

      if (itemsWithOrderableKey.size() != sortDto.data.size()) {
        throw new NotAllCustomObjectHaveSortablePropertyException(sortDto.orderBy);
      }

      keys = itemsWithOrderableKey.stream()
          .map(x -> Map.class.cast(x).get(sortDto.orderBy))
          .collect(Collectors.toList());
    }

    if (determineSortableImpl(keys) == "String") {
      final String[] stringKeys = keys.stream().map(String.class::cast).toArray(String[]::new);

      this.parsedData = IntStream.range(0, sortDto.data.size())
          .mapToObj(i -> (ISortableItem) new StringKeySortableItem(stringKeys[i], sortDto.data.get(i)))
          .collect(Collectors.toList());
    } else {
      final Number[] numericKeys = keys.stream().map(Number.class::cast).toArray(Number[]::new);
      this.parsedData = IntStream.range(0, sortDto.data.size())
          .mapToObj(i -> (ISortableItem) new NumericKeySortableItem(numericKeys[i], sortDto.data.get(i)))
          .collect(Collectors.toList());
    }
  }

  private String determineSortableImpl(List<Object> list) throws InvalidSortInputException {
    if (list.stream().allMatch(String.class::isInstance)) {
      return "String";
    }

    if (list.stream().allMatch(Number.class::isInstance)) {
      return "Number";
    }

    throw new InvalidSortInputException("Keys selected to sort items must be comparable.");
  }

  public List<SortingAlgorithm> getAlgorithms() {
    return sortDto.algorithms.stream().distinct().collect(Collectors.toList());
  }
}
