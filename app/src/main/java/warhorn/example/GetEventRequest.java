package warhorn.example;

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

  GetEventRequest(String slug) {
    super(QUERY, OPERATION);
    this.variables.put("slug", slug);
  }
}
