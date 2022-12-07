package pl.put.poznan.madness.rest;

import java.lang.System.Logger;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
  public <T> Object post(@RequestBody() SortingInput<T> input) {

    if (input.data.stream().allMatch(String.class::isInstance)) {

      // input.data.stream().map(i -> new SortableItem<String, String>(i, i))
      //     .toArray();

      return "String";
    }

    if (input.data.stream().allMatch(Integer.class::isInstance)) {
      return "Integer";
    }

    return "Custom";
  }
}
