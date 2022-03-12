package warhorn.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GraphQLResponse {
  private final JsonNode data;
  private final ObjectMapper objectMapper;

  GraphQLResponse(String body, ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;

    // TODO: handle errors

    JsonNode tree = readTree(body);
    this.data = tree.get("data");
  }

  public <T> T get(String fieldName, Class<T> type) {
    if (data.hasNonNull(fieldName)) {
      return objectMapper.convertValue(data.get(fieldName), type);
    }
    return null;
  }

  private JsonNode readTree(String body) {
    try {
      return objectMapper.readTree(body);
    } catch (JsonMappingException e) {
      throw new RuntimeException(e);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
