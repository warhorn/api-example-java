package warhorn.example.api;

public class Registration {
  private String email;
  private Event event;
  private String id;

  public String getEmail() {
    return email;
  }

  public Event getEvent() {
    return event;
  }

  public String getId() {
    return id;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setEvent(Event event) {
    this.event = event;
  }

  public void setId(String id) {
    this.id = id;
  }
}
