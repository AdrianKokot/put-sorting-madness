package pl.put.poznan.madness.rest.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import pl.put.poznan.madness.rest.models.ISortableItem;

import java.io.IOException;

public class SortableItemSerializer extends JsonSerializer<ISortableItem> {

  @Override
  public void serialize(ISortableItem value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    gen.writeObject(value.getResultObject());
  }
}
