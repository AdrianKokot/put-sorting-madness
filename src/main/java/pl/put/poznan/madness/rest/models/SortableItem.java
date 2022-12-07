package pl.put.poznan.madness.rest.models;

// import java.util.List;

// // import pl.put.poznan.madness.logic.SortingAlgorithm;

public class SortableItem<T, K extends Comparable<K>> implements Comparable<SortableItem<T, K>> {
  public SortableItem(T t, K t2) {
    this.resultObject = t;
    this.orderableItem = t2;
  }

  @Override
  public int compareTo(SortableItem<T, K> o) {
    return this.orderableItem.compareTo(o.orderableItem);
  }

  public T resultObject;
  public K orderableItem;
}
