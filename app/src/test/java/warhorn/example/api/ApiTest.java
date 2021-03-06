/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package warhorn.example.api;

import org.mockito.*;

import warhorn.example.graphql.GraphQLRequest;
import warhorn.example.graphql.GraphQLResponse;
import warhorn.example.graphql.GraphQLWebClient;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ApiTest {
  static final String EMAIL = "test@example.com";
  static final String SLUG = "deadbeef";
  static final String TOKEN = "foobar";

  @Test
  void testFetchEvent() {
    Registration expected = new Registration();

    GraphQLResponse response = Mockito.mock(GraphQLResponse.class);
    Mockito.when(response.get("eventRegistration", Registration.class)).thenReturn(expected);

    GraphQLWebClient client = Mockito.mock(GraphQLWebClient.class);
    Mockito.when(client.post(ArgumentMatchers.any(GraphQLRequest.class))).thenReturn(response);

    Api Api = new Api(client);
    Registration actual = Api.fetchEventRegistration(SLUG, EMAIL);

    assertEquals(expected, actual);
  }
}
