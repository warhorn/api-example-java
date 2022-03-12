package warhorn.example;

import warhorn.example.api.Api;
import warhorn.example.api.Event;
import warhorn.example.graphql.GraphQLWebClient;

public class App {
  public static final String ENDPOINT_URL = "https://staging.warhorn.net/graphql";
  public static final String USAGE = """
      Usage: gradlew run --args EVENT_SLUG

      The WARHORN_API_TOKEN environment variable must contain a user or application access
      token for the Warhorn API. See https://warhorn.net/developers/docs/guides/access-tokens
      for more information.
      """;

  public static void main(String[] args) {
    if (args.length != 1) {
      System.err.println(USAGE);
      System.exit(-1);
    }
    String slug = args[0];

    String token = System.getenv("WARHORN_API_TOKEN");
    if (token == null) {
      System.err.println(USAGE);
      System.exit(-1);
    }

    GraphQLWebClient client = new GraphQLWebClient(ENDPOINT_URL, token);
    Api api = new Api(client);
    Event event = api.fetchEvent(slug);

    System.out.println(String.format("Event: %s", event.getTitle()));
  }
}
