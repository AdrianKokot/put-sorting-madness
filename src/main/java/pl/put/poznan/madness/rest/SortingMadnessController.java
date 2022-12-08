package pl.put.poznan.madness.rest;

import java.util.Map;
import java.util.stream.Collectors;

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
    SortableItem[] data;

    if (input.data.stream().anyMatch(Map.class::isInstance)) {
      if (input.property == null) {
        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
            "When sorting custom object 'property' is required.");
      }

      if (input.data.stream().anyMatch(x -> !(x instanceof Map))
          || input.data.stream().map(x -> (Map) x).anyMatch(x -> !x.containsKey(input.property))) {
        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
            "All object in 'data' must have the same 'property' to be sorted on.");
      }

      data = input.data.stream().map(x -> (Map) x)
          .map(x -> new SortableItem(x, x.get(input.property).toString()))
          .collect(Collectors.toList())
          .toArray(SortableItem[]::new);

    } else {
      data = input.data.stream()
          .map(x -> new SortableItem(x, x.toString()))
          .collect(Collectors.toList())
          .toArray(SortableItem[]::new);
    }

    logger.debug(String.format("[%s] %s:\n\t\t\t%s", RequestMethod.POST, "/api/sort", input.toString()));

    return runner.runSort(input.algorithm, data);
  }
}
