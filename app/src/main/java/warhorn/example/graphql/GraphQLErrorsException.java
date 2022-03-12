package warhorn.example.graphql;

import java.util.List;

public class GraphQLErrorsException extends RuntimeException {
  private List<GraphQLError> errors;
  private String message;

  public GraphQLErrorsException(List<GraphQLError> errors) {
    if (errors.isEmpty()) {
      throw new IllegalArgumentException("errors must not be empty");
    }
    this.errors = errors;
    this.message = errors.get(0).getMessage();
  }

  public List<GraphQLError> getErrors() {
    return errors;
  }

  public String getMessage() {
    return message;
  }
}
