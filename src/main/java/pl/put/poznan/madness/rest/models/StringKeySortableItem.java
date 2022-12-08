package pl.put.poznan.madness.rest.models;

public class StringKeySortableItem implements ISortableItem {
  private String orderableValue;
  private Object resultObject;

  @Override
  public String getOrderableValue() {
    return orderableValue;
  }

  @Override
  public Object getResultObject() {
    return resultObject;
  }

  public StringKeySortableItem(String orderableValue, Object resultObject) {
    this.orderableValue = orderableValue;
    this.resultObject = resultObject;
  }

  @Override
  public int compareTo(ISortableItem o) {
    if (o instanceof StringKeySortableItem) {
      return getOrderableValue().compareTo((String) o.getOrderableValue());
    }
    return 0;
  }
}
