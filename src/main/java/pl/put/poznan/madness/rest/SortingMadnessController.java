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
import pl.put.poznan.madness.logic.models.SortResult;
import pl.put.poznan.madness.rest.exceptions.InvalidSortInputException;
import pl.put.poznan.madness.rest.models.SortableItem;
import pl.put.poznan.madness.rest.models.SortingInput;

@RestController
@RequestMapping("/api")
public class SortingMadnessController {
  private static final Logger logger = LoggerFactory.getLogger(SortingMadnessController.class);

  @Resource
  private ISortRunner runner;

  @RequestMapping(path = "/sort", method = RequestMethod.POST, produces = "application/json")
  public SortResult<SortableItem> post(@RequestBody() SortingInput input) {
    try {
      SortableItem[] data = input.getSortableItems();

      logger.debug(String.format("[%s] %s:\n\t\t\t%s", RequestMethod.POST, "/api/sort", input.toString()));

      return runner.runSort(input.algorithm, data);
    } catch (InvalidSortInputException e) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
    }
  }
}
