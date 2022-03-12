package warhorn.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.Map;

public class GraphQLRequest {
  @JsonProperty
  final String query;

  @JsonProperty
  final String operationName;

  @JsonProperty
  final Map<String, String> variables;

  GraphQLRequest(String query, String operationName) {
    this.query = query;
    this.operationName = operationName;
    this.variables = new HashMap<String, String>();
  }
}
