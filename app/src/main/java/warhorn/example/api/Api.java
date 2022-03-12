package warhorn.example.api;

import java.util.HashMap;
import java.util.Map;
import warhorn.example.graphql.GraphQLError;
import warhorn.example.graphql.GraphQLErrorsException;
import warhorn.example.graphql.GraphQLRequest;
import warhorn.example.graphql.GraphQLResponse;
import warhorn.example.graphql.GraphQLWebClient;

public class Api {
  private final GraphQLWebClient client;

  public Api(GraphQLWebClient client) {
    this.client = client;
  }

  public Registration assignRegistrationRole(Registration registration, EventRole role) {
    Map<String, Object> variables = new HashMap<String, Object>();
    AssignRegistrationRoleInput input = new AssignRegistrationRoleInput(
        registration.getId(), role.getId());
    variables.put("input", input);

    GraphQLRequest request = GraphQLRequest.createFromResource(
        "/AssignRegistrationRole.graphql",
        "AssignRegistrationRole",
        variables);
    GraphQLResponse response = this.client.post(request);

    return response.get("assignRegistrationRole/registration", Registration.class);
  }

  public Registration clearRegistrationForSignup(Registration registration) {
    Map<String, Object> variables = new HashMap<String, Object>();
    UpdateRegistrationInput input = new UpdateRegistrationInput(
        registration.getId(), true);
    variables.put("input", input);

    GraphQLRequest request = GraphQLRequest.createFromResource(
        "/UpdateRegistration.graphql",
        "UpdateRegistration",
        variables);
    GraphQLResponse response = this.client.post(request);

    return response.get("UpdateRegistration/registration", Registration.class);
  }

  public GraphQLWebClient getClient() {
    return this.client;
  }

  public Registration fetchEventRegistration(String slug, String email) {
    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put("slug", slug);
    variables.put("email", email);

    GraphQLRequest request = GraphQLRequest.createFromResource(
        "/FetchEventRegistration.graphql",
        "FetchEventRegistration",
        variables);
    GraphQLResponse response = this.client.post(request);

    return response.get("eventRegistration", Registration.class);
  }

  public interface ApiCaller {
    public void call();
  }

  public interface ApiErrorHandler {
    public void onError();
  }

  public void doWithErrorHandling(ApiCaller caller, ApiErrorHandler handler) {
    try {
      caller.call();
    } catch (GraphQLErrorsException e) {
      for (GraphQLError error : e.getErrors()) {
        System.err.println(String.format("GraphQL error: %s (%s)", error.getMessage(),
            String.join("/", error.getPath())));
      }

      handler.onError();
    }
  }
}
