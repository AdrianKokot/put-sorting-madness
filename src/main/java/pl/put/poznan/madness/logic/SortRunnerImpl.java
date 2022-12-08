package pl.put.poznan.madness.logic;

import org.springframework.stereotype.Component;

import pl.put.poznan.madness.rest.interfaces.ISortRunner;
import pl.put.poznan.madness.rest.models.SortableItem;

@Component
public class SortRunnerImpl implements ISortRunner {

  @Override
  public SortableItem[] run(SortingAlgorithm algorithm, SortableItem[] data) {
    Sort sorter = new JavaSort();
    sorter.sort(data);
    return data;
  }

}
