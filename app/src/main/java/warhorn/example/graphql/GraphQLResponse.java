package warhorn.example.graphql;

import static java.util.Collections.emptyList;

import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GraphQLResponse {
  private final JsonNode data;
  private final List<GraphQLError> errors;
  private final ObjectMapper objectMapper;

  public GraphQLResponse(String body, ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;

    JsonNode tree = readTree(body);
    errors = readErrors(tree);
    data = readData(tree);
  }

  public <T> T get(String fieldName, Class<T> type) {
    if (data.hasNonNull(fieldName)) {
      return objectMapper.convertValue(data.get(fieldName), type);
    }
    return null;
  }

  public <T> List<T> getList(JsonNode node, Class<T> type) {
    return objectMapper.convertValue(node, constructListType(type));
  }

  public void validateNoErrors() {
    if (!errors.isEmpty()) {
      throw new GraphQLErrorsException(errors);
    }
  }

  private JavaType constructListType(Class<?> type) {
    return objectMapper.getTypeFactory().constructCollectionType(List.class, type);
  }

  private JsonNode readTree(String body) {
    try {
      return objectMapper.readTree(body);
    } catch (JsonProcessingException e) {
      throw new GraphQLException("Error deserializaing GraphQL response", e);
    }
  }

  private JsonNode readData(JsonNode tree) {
    return tree.get("data");
  }

  private List<GraphQLError> readErrors(JsonNode tree) {
    if (tree.hasNonNull("errors")) {
      return getList(tree.get("errors"), GraphQLError.class);
    }
    return emptyList();
  }
}
