package warhorn.example;

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

  public String getEvent(String slug) {
    GetEventRequest request = new GetEventRequest(slug);
    return this.client.post(request);
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

    System.out.println(app.getEvent(slug));
  }
}
