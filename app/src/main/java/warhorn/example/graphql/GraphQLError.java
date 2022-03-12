package warhorn.example.graphql;

import java.util.List;
import java.util.Map;

public class GraphQLError {
  private Map<String, Object> extensions;
  private List<GraphQLErrorLocation> locations;
  private String message;
  private List<String> path;

  public Map<String, Object> getExtensions() {
    return extensions;
  }

  public List<GraphQLErrorLocation> getLocations() {
    return locations;
  }

  public String getMessage() {
    return message;
  }

  public List<String> getPath() {
    return path;
  }

  public void setExtensions(Map<String, Object> extensions) {
    this.extensions = extensions;
  }

  public void setLocations(List<GraphQLErrorLocation> locations) {
    this.locations = locations;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setPath(List<String> path) {
    this.path = path;
  }
}
