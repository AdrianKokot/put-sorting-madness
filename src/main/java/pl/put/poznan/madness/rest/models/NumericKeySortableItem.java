package pl.put.poznan.madness.rest.models;

public class NumericKeySortableItem implements ISortableItem {

  private final Double orderableValue;
  private final Object resultObject;

  public NumericKeySortableItem(Number orderableValue, Object resultObject) {
    this.orderableValue = orderableValue.doubleValue();
    this.resultObject = resultObject;
  }

  @Override
  public Double getOrderableValue() {
    return orderableValue;
  }

  @Override
  public Object getResultObject() {
    return resultObject;
  }

  @Override
  public int compareTo(ISortableItem o) {
    if (o instanceof NumericKeySortableItem) {
      return getOrderableValue().compareTo((Double) o.getOrderableValue());
    }
    return 0;
  }
}
