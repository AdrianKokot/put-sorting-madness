package pl.put.poznan.madness.rest.exceptions;

public class MissingPropertyKeyException extends InvalidSortInputException {
  public MissingPropertyKeyException() {
    super("Field 'property' is required when sorting complex objects.");
  }
}
