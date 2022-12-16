package pl.put.poznan.madness.rest;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import pl.put.poznan.madness.logic.interfaces.ISortRunner;
import pl.put.poznan.madness.rest.models.ISortableItem;
import pl.put.poznan.madness.rest.models.SortDto;
import pl.put.poznan.madness.rest.utils.SortDtoParser;

/**
 * SortingMadnessController is a Spring MVC controller that handles requests related to benchmarking sorting operations.
 */
@RestController
@RequestMapping("/api")
public class SortingMadnessController {

  private static final Logger logger = LoggerFactory.getLogger(SortingMadnessController.class);

  @Resource
  private ISortRunner runner;

  /**
   * Accepts a POST request with a JSON body containing the sorting algorithms and data to be sorted.
   * Validates the input and runs a benchmark on the provided algorithms using the given data.
   * Example valid input data:
   * <pre>{@code {
   *   "algorithms": ["BUBBLE_SORT", "QUICK_SORT"],
   *   "data": [3, 2, 5, 1, 4]
   * }}</pre>
   *
   * @param sortInput a {@link pl.put.poznan.madness.rest.models.SortDto} object containing the algorithms and data to be sorted
   * @return a {@link pl.put.poznan.madness.logic.models.SortBenchmarkResult SortBenchmarkResult} object representing the results of the benchmark
   * @throws ResponseStatusException if the input is invalid
   */
  @RequestMapping(path = "/sort", method = RequestMethod.POST, produces = "application/json")
  public Object post(@RequestBody() SortDto sortInput) {

    SortDtoParser validator = SortDtoParser.parse(sortInput);

    if (!validator.isValid()) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, validator.getMessage());
    }

    logger.info(String.format("[%s] Request at '%s'", RequestMethod.POST, "/api/sort"));

    return runner.runBenchmark(validator.getAlgorithms(),
        validator.getParsedData().toArray(ISortableItem[]::new));
  }
}
