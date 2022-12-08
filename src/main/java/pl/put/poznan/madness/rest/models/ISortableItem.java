package pl.put.poznan.madness.rest.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pl.put.poznan.madness.rest.utils.SortableItemSerializer;

@JsonSerialize(using = SortableItemSerializer.class)
public interface ISortableItem extends Comparable<ISortableItem> {
  public Object getResultObject();
  public Object getOrderableValue();
}
