package pl.put.poznan.madness.rest.exceptions;

public class MissingOrderByKeyException extends InvalidSortInputException {
  public MissingOrderByKeyException() {
    super("Field 'orderBy' is required when sorting complex objects.");
  }
}
