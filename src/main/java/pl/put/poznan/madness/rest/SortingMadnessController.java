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

@RestController
@RequestMapping("/api")
public class SortingMadnessController {

  private static final Logger logger = LoggerFactory.getLogger(SortingMadnessController.class);

  @Resource
  private ISortRunner runner;

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
