package warhorn.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetEventRequest {
  private static final String QUERY = """
      query GetEvent($slug: String!) {
        event(slug: $slug) {
          id
          title
        }
      }
      """;

  private static final String OPERATION = "GetEvent";

  @JsonProperty("query")
  private final String query;

  @JsonProperty("operationName")
  private final String operation;

  @JsonProperty
  private final Map<String, String> variables;

  private final ObjectMapper objectMapper;

  GetEventRequest(String slug) {
    this.objectMapper = new ObjectMapper();
    this.query = QUERY;
    this.operation = OPERATION;
    this.variables = new HashMap<String, String>();
    this.variables.put("slug", slug);
  }

  public String bodyValue() {
    try {
      return this.objectMapper.writeValueAsString(this);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
