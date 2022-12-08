package pl.put.poznan.madness.rest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import pl.put.poznan.madness.logic.JavaSort;
import pl.put.poznan.madness.logic.Sort;
import pl.put.poznan.madness.rest.models.SortableItem;
import pl.put.poznan.madness.rest.models.SortingInput;

@RestController
@RequestMapping("/api")
public class SortingMadnessController {

  @RequestMapping(path = "/sort", method = RequestMethod.POST, produces = "application/json")
  public <T> List<Object> post(@RequestBody() SortingInput<T> input) {
    List<SortableItem<Object, String>> data;

    if (input.data.stream().anyMatch(Map.class::isInstance)) {
      if (input.property == null) {
        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
            "When sorting custom object 'property' is required.");
      }

      if (input.data.stream().map(x -> (Map)x).anyMatch(x -> !x.containsKey(input.property))) {
        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
            "All object in 'data' must have the same 'property' to be sorted on.");
      }

      data = input.data.stream().map(x -> (Map)x)
          .map(x -> new SortableItem<Object, String>(x, x.get(input.property).toString()))
          .collect(Collectors.toList());

    } else {
      data = input.data.stream()
          .map(x -> new SortableItem<Object, String>(x, x.toString()))
          .collect(Collectors.toList());
    }

    Sort sorter = new JavaSort();
    sorter.sort(data);
    return data.stream().map(x -> x.resultObject).collect(Collectors.toList());
  }
}
