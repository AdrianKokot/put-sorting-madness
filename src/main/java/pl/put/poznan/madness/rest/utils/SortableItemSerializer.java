package pl.put.poznan.madness.rest.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import pl.put.poznan.madness.rest.models.SortableItem;

public class SortableItemSerializer extends JsonSerializer<SortableItem> {
  @Override
  public void serialize(SortableItem value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    gen.writeObject(value.resultObject);
  }
}
