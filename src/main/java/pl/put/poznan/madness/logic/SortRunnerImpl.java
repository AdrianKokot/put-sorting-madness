package pl.put.poznan.madness.logic;

import java.time.Instant;
import java.time.Duration;

import org.springframework.stereotype.Component;

import pl.put.poznan.madness.logic.interfaces.ISortRunner;
import pl.put.poznan.madness.logic.models.SortResult;

@Component
public class SortRunnerImpl implements ISortRunner {

  @Override
  public <T extends Comparable<T>> SortResult<T> runSort(SortingAlgorithm algorithm, T[] data) {
    Sort sorter = new JavaSort();

    Instant start = Instant.now();
    sorter.sort(data);
    Instant end = Instant.now();

    return new SortResult<T>(algorithm, Duration.between(start, end).getNano()/1000000.0, data);
  }

}
