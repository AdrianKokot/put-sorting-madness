package pl.put.poznan.madness.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import pl.put.poznan.madness.logic.SortRunnerImpl;
import pl.put.poznan.madness.logic.models.SortBenchmarkResult;
import pl.put.poznan.madness.logic.models.SortPerformance;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortDirection;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingAlgorithm;
import pl.put.poznan.madness.rest.models.SortDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SortingMadnessControllerTest {

  @InjectMocks
  private SortingMadnessController sortingMadnessController;

  @Mock
  private SortRunnerImpl runner;

  @Mock
  private SortDto sortDto;

  @Test
  void testRunSort() {
    sortDto.data = List.of(1, 2);
    sortDto.algorithms = List.of(SortingAlgorithm.BUBBLE_SORT);

    List<SortPerformance> performances = List.of(new SortPerformance(SortingAlgorithm.BUBBLE_SORT, 10.0));
    when(runner.runBenchmark(any(), any(), any(), any()))
      .thenReturn(new SortBenchmarkResult(sortDto.data, performances, SortDirection.ASC));

    Object responseEntity = sortingMadnessController.runSort(sortDto, null);
    assertEquals(responseEntity.getClass(), SortBenchmarkResult.class);
  }
}
