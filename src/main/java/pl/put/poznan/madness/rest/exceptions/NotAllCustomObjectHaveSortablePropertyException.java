package pl.put.poznan.madness.rest.exceptions;

public class NotAllCustomObjectHaveSortablePropertyException extends InvalidSortInputException {
  public NotAllCustomObjectHaveSortablePropertyException(String propertyName) {
    super(String.format("Not all provided objects have sortable '%s' property.", propertyName));
  }
}
