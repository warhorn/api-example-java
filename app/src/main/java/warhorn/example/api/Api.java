package warhorn.example.api;

import java.util.HashMap;
import warhorn.example.graphql.GraphQLRequest;
import warhorn.example.graphql.GraphQLResponse;
import warhorn.example.graphql.GraphQLWebClient;

public class Api {
  private final GraphQLWebClient client;

  public Api(GraphQLWebClient client) {
    this.client = client;
  }

  public GraphQLWebClient getClient() {
    return this.client;
  }

  public Registration fetchEventRegistration(String slug, String email) {
    HashMap<String, String> variables = new HashMap<String, String>();
    variables.put("slug", slug);
    variables.put("email", email);

    GraphQLRequest request = GraphQLRequest.createFromResource(
        "/FetchEventRegistration.graphql",
        "FetchEventRegistration",
        variables);
    GraphQLResponse response = this.client.post(request);

    return response.get("eventRegistration", Registration.class);
  }
}
