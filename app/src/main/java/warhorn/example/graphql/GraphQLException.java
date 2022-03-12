package warhorn.example.graphql;

public class GraphQLException extends RuntimeException {
  public GraphQLException(String message, Throwable cause) {
    super(message, cause);
  }
}
