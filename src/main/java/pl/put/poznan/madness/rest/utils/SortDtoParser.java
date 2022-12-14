package pl.put.poznan.madness.rest.utils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortDirection;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingAlgorithm;
import pl.put.poznan.madness.rest.exceptions.InvalidSortInputException;
import pl.put.poznan.madness.rest.exceptions.MissingOrderByKeyException;
import pl.put.poznan.madness.rest.exceptions.NotAllCustomObjectHaveSortablePropertyException;
import pl.put.poznan.madness.rest.models.ISortableItem;
import pl.put.poznan.madness.rest.models.NumericKeySortableItem;
import pl.put.poznan.madness.rest.models.SortDto;
import pl.put.poznan.madness.rest.models.StringKeySortableItem;

/**
 * Utility class that parses a {@link SortDto} object into a list of {@link ISortableItem} objects
 * that can be used to sort data.
 *
 * <p>This class provides a static method for parsing a {@link SortDto} object and returning a
 * {@link SortDtoParser} object that contains the parsed data and any error messages that may have
 * occurred during parsing.
 *
 * <p>The parsed data can be retrieved using the {@link #getParsedData()} method, and the error
 * message (if any) can be retrieved using the {@link #getMessage()} method. The {@link #isValid()}
 * method can be used to check whether the parsing was successful or not.
 */
public class SortDtoParser {

  private static final String STRING_KEY = "String";
  private static final String NUMERIC_KEY = "Number";
  /** The {@link SortDto} object to be parsed. */
  private final SortDto sortDto;
  /**
   * A string containing an error message if an error occurred during parsing, or {@code null} if no
   * error occurred.
   */
  private String errorMessage = null;
  /** A list of {@link ISortableItem} objects representing the parsed data. */
  private List<ISortableItem> parsedData;

  /**
   * Constructs a new {@link SortDtoParser} object with the given {@link SortDto} object. If
   * validating fails the {@link SortDtoParser#errorMessage} will be populated with the
   * corresponding error
   *
   * @param dto the {@link SortDto} object to be parsed
   */
  private SortDtoParser(SortDto dto) {
    this.sortDto = dto;

    try {
      this.setup();
    } catch (Exception e) {
      this.errorMessage = e.getMessage();
    }
  }

  /**
   * Returns a new {@link SortDtoParser} object with the given {@link SortDto} object.
   *
   * @param dto the {@link SortDto} object to be parsed
   * @return {@link SortDtoParser} object with validation result of given {@link SortDto} object
   */
  public static SortDtoParser parse(SortDto dto) {
    return new SortDtoParser(dto);
  }

  public ISortableItem[] getParsedData() {
    return parsedData.toArray(ISortableItem[]::new);
  }

  public String getMessage() {
    return errorMessage;
  }

  public boolean isValid() {
    return errorMessage == null;
  }

  /**
   * Setups the {@link SortDtoParser} object by parsing object's {@link SortDtoParser#sortDto}.
   * Validates that the data is:
   *
   * <ul>
   *   <li>all of same sortable type,
   *   <li>either a Number, a String or an object with sortable property,
   *   <li>if the data is made up of objects then all of them need to have a desired key.
   * </ul>
   *
   * @throws InvalidSortInputException if is supplied with an empty array
   */
  private void setup() throws InvalidSortInputException {
    if (sortDto.data.isEmpty()) {
      throw new InvalidSortInputException("Missing data to sort.");
    }

    if (sortDto.algorithms.isEmpty()) {
      throw new InvalidSortInputException("Sorting algorithms not specified.");
    }

    List<Object> keys = sortDto.data;

    if (sortDto.data.stream().anyMatch(Map.class::isInstance)) {

      if (sortDto.orderBy.isEmpty()) {
        throw new MissingOrderByKeyException();
      }

      List<Object> itemsWithOrderableKey =
          sortDto.data.stream()
              .filter(x -> x instanceof Map && ((Map<?, ?>) x).containsKey(sortDto.orderBy))
              .collect(Collectors.toList());

      if (itemsWithOrderableKey.size() != sortDto.data.size()) {
        throw new NotAllCustomObjectHaveSortablePropertyException(sortDto.orderBy);
      }

      keys =
          itemsWithOrderableKey.stream()
              .map(x -> ((Map<?, ?>) x).get(sortDto.orderBy))
              .collect(Collectors.toList());
    }

    if (STRING_KEY.equals(determineSortableImpl(keys))) {
      final String[] stringKeys = keys.stream().map(String.class::cast).toArray(String[]::new);

      this.parsedData =
          IntStream.range(0, sortDto.data.size())
              .mapToObj(
                  i ->
                      (ISortableItem) new StringKeySortableItem(stringKeys[i], sortDto.data.get(i)))
              .collect(Collectors.toList());
    } else {
      final Number[] numericKeys = keys.stream().map(Number.class::cast).toArray(Number[]::new);
      this.parsedData =
          IntStream.range(0, sortDto.data.size())
              .mapToObj(
                  i ->
                      (ISortableItem)
                          new NumericKeySortableItem(numericKeys[i], sortDto.data.get(i)))
              .collect(Collectors.toList());
    }
  }

  /**
   * Determines the type of given keys.
   *
   * @param list list of keys
   * @return type of keys
   * @throws InvalidSortInputException if given keys aren't all comparable with each other
   */
  private String determineSortableImpl(List<Object> list) throws InvalidSortInputException {
    if (list.stream().allMatch(String.class::isInstance)) {
      return STRING_KEY;
    }

    if (list.stream().allMatch(Number.class::isInstance)) {
      return NUMERIC_KEY;
    }

    throw new InvalidSortInputException("Keys selected to sort items must be comparable.");
  }

  public List<SortingAlgorithm> getAlgorithms() {
    return sortDto.algorithms.stream().distinct().collect(Collectors.toList());
  }

  public SortDirection getDirection() {
    return sortDto.direction;
  }

  public Optional<Integer> getIterationsCount() {
    return Optional.ofNullable(sortDto.iterationsCount);
  }
}
