package warhorn.example;

import java.util.HashMap;

import warhorn.example.api.Event;
import warhorn.example.graphql.GraphQLRequest;
import warhorn.example.graphql.GraphQLResponse;
import warhorn.example.graphql.GraphQLWebClient;

public class App {
  public static final String ENDPOINT_URL = "https://staging.warhorn.net/graphql";
  public static final String USAGE = """
      Usage: gradlew run --args \"EVENT_SLUG APP_TOKEN\"
      """;

  private final GraphQLWebClient client;

  App(GraphQLWebClient client) {
    this.client = client;
  }

  public GraphQLWebClient getClient() {
    return this.client;
  }

  public Event getEvent(String slug) {
    HashMap<String, String> variables = new HashMap<String, String>();
    variables.put("slug", slug);

    GraphQLRequest request = GraphQLRequest.createFromResource(
        "/GetEvent.graphql",
        "GetEvent",
        variables);
    GraphQLResponse response = this.client.post(request);

    return response.get("event", Event.class);
  }

  public static void main(String[] args) {
    if (args.length != 2) {
      System.err.println(USAGE);
      System.exit(-1);
    }

    String slug = args[0];
    String token = args[1];

    GraphQLWebClient client = new GraphQLWebClient(ENDPOINT_URL, token);
    App app = new App(client);

    System.out.println(String.format("Event: %s", app.getEvent(slug).getTitle()));
  }
}
