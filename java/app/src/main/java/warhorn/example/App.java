package warhorn.example;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class App {
  public static final String ENDPOINT_URL = "https://staging.warhorn.net/graphql";
  public static final String USAGE = """
      Usage: gradlew run --args \"EVENT_SLUG APP_TOKEN\"
      """;

  private final WebClient client;
  private final String token;

  App(String token) {
    this.client = WebClient.create(ENDPOINT_URL);
    this.token = token;
  }

  public String postGraphQLRequest(GetEventRequest request) {
    return this.client.post()
      .header("Authorization", String.format("Bearer %s", this.token))
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      .bodyValue(request.bodyValue())
      .retrieve()
      .bodyToMono(String.class).block();
  }

  public String getEvent(String slug) {
    GetEventRequest request = new GetEventRequest(slug);
    return this.postGraphQLRequest(request);
  }

  public static void main(String[] args) {
    if (args.length != 2) {
      System.err.println(USAGE);
      System.exit(-1);
    }

    String slug = args[0];
    String token = args[1];

    App app = new App(token);

    System.out.println(app.getEvent(slug));
  }
}
