package warhorn.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GraphQLRequest {
  @JsonProperty
  final String query;

  @JsonProperty
  final String operationName;

  @JsonProperty
  final Map<String, String> variables;

  private final ObjectMapper objectMapper;

  GraphQLRequest(String query, String operationName) {
    this.objectMapper = new ObjectMapper();
    this.query = query;
    this.operationName = operationName;
    this.variables = new HashMap<String, String>();
  }

  public String bodyValue() {
    try {
      return this.objectMapper.writeValueAsString(this);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
