package warhorn.example;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class GraphQLWebClient {

  private final WebClient client;
  private final String token;

  GraphQLWebClient(String endpointUrl, String token) {
    this.client = WebClient.create(endpointUrl);
    this.token = token;
  }

  public String post(GetEventRequest request) {
    return this.client.post()
        .header("Authorization", String.format("Bearer %s", this.token))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .bodyValue(request.bodyValue())
        .retrieve()
        .bodyToMono(String.class).block();
  }
}
