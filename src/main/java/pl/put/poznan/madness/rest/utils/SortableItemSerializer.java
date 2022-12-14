package pl.put.poznan.madness.rest.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import pl.put.poznan.madness.rest.models.ISortableItem;

public class SortableItemSerializer extends JsonSerializer<ISortableItem> {

  @Override
  public void serialize(ISortableItem value, JsonGenerator gen, SerializerProvider provider)
      throws IOException {
    gen.writeObject(value.getResultObject());
  }
}
