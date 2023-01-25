package pl.put.poznan.madness.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.put.poznan.madness.logic.SortAlgorithmProposerImpl;
import pl.put.poznan.madness.logic.SortRunnerImpl;
import pl.put.poznan.madness.logic.models.SortBenchmarkResult;
import pl.put.poznan.madness.logic.models.SortPerformance;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortDirection;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingAlgorithm;
import pl.put.poznan.madness.rest.models.NumericKeySortableItem;
import pl.put.poznan.madness.rest.models.SortDto;

@ExtendWith(MockitoExtension.class)
class SortingMadnessControllerTest {

  @Mock private SortRunnerImpl runner;

  @Mock private SortAlgorithmProposerImpl proposer;

  @InjectMocks private SortingMadnessController sortingMadnessController;

  @Test
  void shouldUseSortRunner() {
    SortDto sortDto = new SortDto();
    sortDto.data = List.of(1, 2);
    sortDto.algorithms = List.of(SortingAlgorithm.BUBBLE_SORT);

    List<SortPerformance> performances =
        List.of(new SortPerformance(SortingAlgorithm.BUBBLE_SORT, 10.0));
    when(runner.runBenchmark(any(), any(), any(), any()))
        .thenReturn(new SortBenchmarkResult(sortDto.data, performances, SortDirection.ASC));

    Object responseEntity = sortingMadnessController.runSort(sortDto);
    assertEquals(responseEntity.getClass(), SortBenchmarkResult.class);
    verify(runner, times(1))
        .runBenchmark(eq(sortDto.algorithms), any(), eq(SortDirection.ASC), eq(Optional.empty()));
  }

  @Test
  void shouldUseProposer() {
    SortDto sortDto = new SortDto();
    sortDto.data = List.of(1, 2);

    List<SortPerformance> performances =
        List.of(new SortPerformance(SortingAlgorithm.INSERTION_SORT, 10.0));
    when(runner.runBenchmark(any(), any(), any(), any()))
        .thenReturn(new SortBenchmarkResult(sortDto.data, performances, SortDirection.ASC));

    when(proposer.proposeAlgorithm(any())).thenReturn(SortingAlgorithm.INSERTION_SORT);

    Object responseEntity = sortingMadnessController.suggestSort(sortDto);

    assertEquals(responseEntity.getClass(), SortBenchmarkResult.class);

    SortBenchmarkResult<NumericKeySortableItem> benchmarkResult =
        ((SortBenchmarkResult<NumericKeySortableItem>) responseEntity);
    assertEquals(
        benchmarkResult.algorithmsPerformance.get(0).algorithm, SortingAlgorithm.INSERTION_SORT);

    verify(proposer, times(1)).proposeAlgorithm(sortDto.data);
    verify(runner, times(1))
        .runBenchmark(eq(List.of(SortingAlgorithm.INSERTION_SORT)), any(), any(), any());
  }
}
