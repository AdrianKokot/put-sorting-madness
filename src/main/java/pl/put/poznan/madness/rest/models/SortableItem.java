package pl.put.poznan.madness.rest.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pl.put.poznan.madness.rest.utils.SortableItemSerializer;

@JsonSerialize(using = SortableItemSerializer.class)
public class SortableItem implements Comparable<SortableItem> {
  public SortableItem(Object t, String t2) {
    this.resultObject = t;
    this.orderableItem = t2;
  }

  @Override
  public int compareTo(SortableItem o) {
    return this.orderableItem.compareTo(o.orderableItem);
  }

  public Object resultObject;
  public String orderableItem;
}
