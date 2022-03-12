package warhorn.example;

import warhorn.example.api.Api;
import warhorn.example.api.Registration;
import warhorn.example.graphql.GraphQLWebClient;

public class App {
  public static final String ENDPOINT_URL = "https://staging.warhorn.net/graphql";
  public static final String USAGE = """
      Usage: gradlew run --args SLUG EMAIL

      Fetches the details of a Warhorn event registration.

        SLUG:   the unique identifier for the event (found in Warhorn event page URLs)
        EMAIL:  the email address of the user whose registration you want to find

      The WARHORN_API_TOKEN environment variable must contain a user or application access
      token for the Warhorn API. See https://warhorn.net/developers/docs/guides/access-tokens
      for more information.
      """;

  public static void main(String[] args) {
    if (args.length != 2) {
      die(USAGE);
    }

    String slug = args[0];
    String email = args[1];

    String token = System.getenv("WARHORN_API_TOKEN");
    if (token == null) {
      die(USAGE);
    }

    GraphQLWebClient client = new GraphQLWebClient(ENDPOINT_URL, token);
    Api api = new Api(client);

    api.doWithErrorHandling(() -> {
      Registration registration = api.fetchEventRegistration(slug, email);

      if (registration == null) {
        printRegistrationNotFoundBanner();
        return;
      }

      printRegistrationFoundBanner(registration);
    }, () -> die("\n\u274C Aborting due to API errors.", -2));
  }

  static void die(String message, int exitValue) {
    if (message != null) {
      System.err.println(message);
    }
    System.exit(exitValue);
  }

  static void die(String message) {
    die(message, -1);
  }

  static void die(int exitValue) {
    die(null, exitValue);
  }

  static void printRegistrationFoundBanner(Registration registration) {
    System.out.println(String.format("\u2705 Found an %s, %s registration for %s at %s.",
        registration.getActivationState().toString().toLowerCase(),
        registration.isClearedForSignup() ? "cleared" : "uncleared",
        registration.getRegistrant().getEmail(),
        registration.getEvent().getTitle()));
  }

  static void printRegistrationNotFoundBanner() {
    System.out.println(
        "\u274C Registration not found. Double-check the event slug and email address.");
  }
}
