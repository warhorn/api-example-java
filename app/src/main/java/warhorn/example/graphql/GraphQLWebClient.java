package warhorn.example.graphql;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class GraphQLWebClient {

  private final WebClient client;
  private final String token;
  private final ObjectMapper objectMapper;

  public GraphQLWebClient(String endpointUrl, String token) {
    this.client = WebClient.create(endpointUrl);
    this.token = token;
    this.objectMapper = new ObjectMapper();
  }

  public GraphQLResponse post(GraphQLRequest request) {
    return this.client.post()
        .header("Authorization", String.format("Bearer %s", this.token))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .bodyValue(serializeRequestBody(request))
        .retrieve()
        .bodyToMono(String.class)
        .map(it -> new GraphQLResponse(it, this.objectMapper))
        .block();
  }

  private String serializeRequestBody(GraphQLRequest request) {
    try {
      return this.objectMapper.writeValueAsString(request);
    } catch (IOException e) {
      throw new GraphQLException("Error serializing GraphQL request", e);
    }
  }
}
