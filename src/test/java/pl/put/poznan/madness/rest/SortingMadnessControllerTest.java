package pl.put.poznan.madness.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import pl.put.poznan.madness.logic.SortAlgorithmProposerImpl;
import pl.put.poznan.madness.logic.SortRunnerImpl;
import pl.put.poznan.madness.logic.models.SortBenchmarkResult;
import pl.put.poznan.madness.logic.models.SortPerformance;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortDirection;
import pl.put.poznan.madness.logic.sorting.strategies.boundary.SortingAlgorithm;
import pl.put.poznan.madness.rest.models.NumericKeySortableItem;
import pl.put.poznan.madness.rest.models.SortDto;
import pl.put.poznan.madness.rest.utils.SortDtoParser;

@ExtendWith(MockitoExtension.class)
class SortingMadnessControllerTest {

  @Mock private SortRunnerImpl runner;

  @Mock private SortAlgorithmProposerImpl proposer;

  @InjectMocks @Spy private SortingMadnessController sortingMadnessController;

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

  @Test
  void shouldUseParser() {
    SortDto sortDto = new SortDto();
    sortDto.data = List.of(1, 2);
    sortDto.algorithms = List.of(SortingAlgorithm.BUBBLE_SORT);

    SortDtoParser parser = SortDtoParser.parse(sortDto);
    SortDtoParser parserSpy = spy(parser);

    when(sortingMadnessController.parse(any(SortDto.class))).thenReturn(parserSpy);
    when(runner.runBenchmark(any(), any(), any(), any())).thenCallRealMethod();

    sortingMadnessController.runSort(sortDto);

    verify(parserSpy, times(1)).getAlgorithms();
    verify(parserSpy, times(1)).getDirection();
    verify(parserSpy, times(1)).getParsedData();
    verify(parserSpy, times(1)).getIterationsCount();
    verify(parserSpy, times(1)).isValid();
    verify(parserSpy, times(0)).getMessage();
    verify(runner, times(1)).runBenchmark(any(), any(), any(), any());
  }

  @Test
  void shouldUseParserAndReturnValidationError() {
    SortDtoParser parser = SortDtoParser.parse(new SortDto());
    SortDtoParser parserSpy = spy(parser);

    when(sortingMadnessController.parse(any(SortDto.class))).thenReturn(parserSpy);
    when(parserSpy.isValid()).thenReturn(false);
    when(parserSpy.getMessage()).thenReturn("TEST_EXCEPTION");

    ResponseStatusException exception =
        assertThrows(
            ResponseStatusException.class,
            () -> {
              sortingMadnessController.runSort(new SortDto());
            });

    verify(parserSpy, times(0)).getAlgorithms();
    verify(parserSpy, times(0)).getDirection();
    verify(parserSpy, times(0)).getParsedData();
    verify(parserSpy, times(0)).getIterationsCount();
    verify(parserSpy, times(1)).isValid();
    verify(parserSpy, times(1)).getMessage();
    verify(runner, times(0)).runBenchmark(any(), any(), any(), any());

    assertTrue(exception.getMessage().contains("TEST_EXCEPTION"));
  }
}
