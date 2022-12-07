package pl.put.poznan.madness.rest.models;

// import java.util.List;

// // import pl.put.poznan.madness.logic.SortingAlgorithm;

public class SortableItem<T, K extends Comparable<K>> implements Comparable<SortableItem<T, K>> {
  public SortableItem(T resultObject, K orderableItem) {
    this.resultObject = resultObject;
    this.orderableItem = orderableItem;
  }

  @Override
  public int compareTo(SortableItem<T, K> o) {
    return 0;
  }

  public T resultObject;
  public K orderableItem;
}
