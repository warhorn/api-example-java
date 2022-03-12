package warhorn.example;

import warhorn.example.api.Api;
import warhorn.example.api.EventRole;
import warhorn.example.api.Registration;
import warhorn.example.graphql.GraphQLWebClient;

public class App {
  public static final String ENDPOINT_URL = "https://warhorn.net/graphql";
  public static final String USAGE = """
      Usage: gradlew run --args \"SLUG EMAIL <ROLE>\"

      Clears a Warhorn event registration to sign up for games. Optionally also assigns an
      event role to the registration.

        SLUG:   the unique identifier for the event (found in Warhorn event page URLs)
        EMAIL:  the email address of the user whose registration you want to find
        ROLE:   the name of an event role to assign to the registration (optional)

      The WARHORN_API_TOKEN environment variable must contain a user or application access
      token for the Warhorn API. See https://warhorn.net/developers/docs/guides/access-tokens
      for more information.
      """;

  public static void main(String[] args) {
    if (args.length < 2) {
      die(USAGE, 0);
    }

    final String slug = args[0];
    final String email = args[1];
    final String roleName = args.length > 2 ? args[2] : null;

    final String token = System.getenv("WARHORN_API_TOKEN");
    if (token == null) {
      die(USAGE, 0);
    }

    final GraphQLWebClient client = new GraphQLWebClient(ENDPOINT_URL, token);
    final Api api = new Api(client);

    api.doWithErrorHandling(() -> {
      Registration registration = api.fetchEventRegistration(slug, email);

      if (registration == null) {
        printRegistrationNotFoundBanner();
        return;
      }

      printRegistrationFoundBanner(registration);

      if (registration.isCanceled()) {
        printRegistrationCanceledBanner();
        return;
      }

      if (registration.isClearedForSignup()) {
        printRegistrationAlreadyCleared();
      } else {
        api.clearRegistrationForSignup(registration);
        printRegistrationCleared();
      }

      if (roleName != null) {
        final EventRole roleToAssign = registration.getEvent().getRoles().stream()
            .filter(role -> role.getName().equals(roleName))
            .findAny().orElse(null);
        if (roleToAssign == null) {
          printRoleNotFoundBanner(roleName);
          return;
        }

        final EventRole existingRole = registration.getRoles().stream()
            .filter(role -> role.getId().equals(roleToAssign.getId()))
            .findAny().orElse(null);
        if (existingRole != null) {
          printRoleAlreadyAssigned(existingRole);
        } else {
          api.assignRegistrationRole(registration, roleToAssign);
          printRoleAssigned(roleToAssign);
        }
      }

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
    System.out.println(String.format("\u2705 %s, %s registration was found for %s at %s.",
        registration.isActive() ? "An active" : "A canceled",
        registration.isClearedForSignup() ? "cleared" : "uncleared",
        registration.getRegistrant().getEmail(),
        registration.getEvent().getTitle()));
  }

  static void printRegistrationNotFoundBanner() {
    System.out.println(
        "\u274C The registration was not found. Double-check the event slug and email address.");
  }

  static void printRegistrationCanceledBanner() {
    System.out.println(
        "\n\u274C The registration cannot be updated since it has already been canceled.");
  }

  static void printRoleNotFoundBanner(String roleName) {
    System.out.println(String.format(
        "\u274C The %s role was not found for the event. Double-check the spelling and " +
            "capitalization of the role name.",
        roleName));
  }

  static void printRoleAlreadyAssigned(EventRole role) {
    System.out.println(String.format(
        "\u2705 The %s role was already assigned to the registration.", role.getName()));
  }

  static void printRoleAssigned(EventRole role) {
    System.out.println(String.format(
        "\u2705 The %s role was assigned to the registration.", role.getName()));
  }

  static void printRegistrationAlreadyCleared() {
    System.out.println(String.format(
        "\u2705 The registration was already cleared for signup."));
  }

  static void printRegistrationCleared() {
    System.out.println(String.format(
        "\u2705 The registration was cleared for signup."));
  }
}
