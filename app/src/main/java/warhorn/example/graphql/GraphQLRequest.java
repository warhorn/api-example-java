package warhorn.example.graphql;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.InputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

public class GraphQLRequest {
  @JsonProperty
  final String query;

  @JsonProperty
  final String operationName;

  @JsonProperty
  final Map<String, String> variables;

  public GraphQLRequest(String query, String operationName, Map<String, String> variables) {
    this.query = query;
    this.operationName = operationName;
    this.variables = new HashMap<String, String>(variables);
  }

  public void setVariable(String key, String value) {
    this.variables.put(key, value);
  }

  public static GraphQLRequest createFromResource(String path,
      String operationName,
      Map<String, String> variables) {
    try {
      Resource resource = new ClassPathResource(path);
      InputStream inputStream = resource.getInputStream();
      String query = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
      return new GraphQLRequest(query, operationName, variables);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
