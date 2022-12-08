package pl.put.poznan.madness.rest.interfaces;

import pl.put.poznan.madness.logic.SortingAlgorithm;
import pl.put.poznan.madness.rest.models.SortableItem;

public interface ISortRunner {
  public SortableItem[] run(SortingAlgorithm algorithm, SortableItem[] data);
}
