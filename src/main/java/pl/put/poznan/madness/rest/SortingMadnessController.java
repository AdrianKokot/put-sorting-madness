package pl.put.poznan.madness.rest;

import java.lang.System.Logger;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.SortingFocusTraversalPolicy;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.put.poznan.madness.logic.JavaSort;
import pl.put.poznan.madness.logic.Sort;
import pl.put.poznan.madness.rest.models.SortableItem;
import pl.put.poznan.madness.rest.models.SortingInput;

// import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class SortingMadnessController {

  // private static final Logger logger =
  // LoggerFactory.getLogger(TextTransformerController.class);

  // @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  // public String post(@RequestParam(value = "transforms", defaultValue =
  // "upper,escape") List<String> items) {

  // // log the parameters
  // // logger.debug(items.toString());
  // System.out.println(items.toString());
  // // logger.debug(Arrays.toString(transforms));

  // // perform the transformation, you should run your logic here, below is just
  // a
  // // silly example hehe very silly
  // // TextTransformer transformer = new TextTransformer(transforms);
  // // return transformer.transform(text);
  // return items.toString();
  // }
  @RequestMapping(path = "/sort", method = RequestMethod.POST, produces = "application/json")
  public <T> List<Object> post(@RequestBody() SortingInput<T> input) {

    if (input.data.stream().allMatch(String.class::isInstance)) {

      List<SortableItem<String, String>> data = input.data.stream()
          .map(String::valueOf)
          .map(x -> new SortableItem<String, String>(x, x))
          .collect(Collectors.toList());

      Sort sorter = new JavaSort();
      sorter.sort(data);

      return data.stream().map(x -> x.resultObject).collect(Collectors.toList());

    }

    return List.of();
    // throw
    // String[] strArray = new String[0];
    // return strArray;

    // if (input.data.stream().allMatch(Integer.class::isInstance)) {
    // return "Integer";
    // }

    // return "Custom";
  }
}
