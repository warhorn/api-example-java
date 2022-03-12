package warhorn.example;

import warhorn.example.graphql.GraphQLRequest;

public class GetEventRequest extends GraphQLRequest {
  private static final String QUERY = """
      query GetEvent($slug: String!) {
        event(slug: $slug) {
          id
          title
        }
      }
      """;

  private static final String OPERATION = "GetEvent";

  public GetEventRequest(String slug) {
    super(QUERY, OPERATION);
    setVariable("slug", slug);
  }
}
