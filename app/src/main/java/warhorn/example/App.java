package warhorn.example;

import warhorn.example.api.Api;
import warhorn.example.api.Registration;
import warhorn.example.graphql.GraphQLError;
import warhorn.example.graphql.GraphQLErrorsException;
import warhorn.example.graphql.GraphQLWebClient;

public class App {
  public static final String ENDPOINT_URL = "https://staging.warhorn.net/graphql";
  public static final String USAGE = """
      Usage: gradlew run --args SLUG EMAIL

      Fetches the details of a Warhorn event registration.

        SLUG:   the unique identifier for the event (found in Warhorn event page URLs)
        EMAIL:  the email address of the user registered for the event

      The WARHORN_API_TOKEN environment variable must contain a user or application access
      token for the Warhorn API. See https://warhorn.net/developers/docs/guides/access-tokens
      for more information.
      """;

  public static void main(String[] args) {
    if (args.length != 2) {
      System.err.println(USAGE);
      System.exit(-1);
    }
    String slug = args[0];
    String email = args[1];

    String token = System.getenv("WARHORN_API_TOKEN");
    if (token == null) {
      System.err.println(USAGE);
      System.exit(-1);
    }

    GraphQLWebClient client = new GraphQLWebClient(ENDPOINT_URL, token);
    Api api = new Api(client);

    try {
      Registration registration = api.fetchEventRegistration(slug, email);

      System.out.println(String.format("Event: %s", registration.getEvent().getTitle()));
      System.out.println(String.format("Registration: %s", registration.getEmail()));
    } catch (GraphQLErrorsException e) {
      for (GraphQLError error : e.getErrors()) {
        System.err.println(String.format("GraphQL error: %s (%s)", error.getMessage(),
            String.join("/", error.getPath())));
      }

      System.exit(-2);
    }
  }
}
