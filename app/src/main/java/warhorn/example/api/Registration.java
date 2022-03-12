package warhorn.example.api;

public class Registration {
  public enum ActivationState {
    ACTIVE,
    CANCELED,
  }

  private ActivationState activationState;
  private Event event;
  private String id;
  private User registrant;

  public ActivationState getActivationState() {
    return activationState;
  }

  public Event getEvent() {
    return event;
  }

  public String getId() {
    return id;
  }

  public User getRegistrant() {
    return registrant;
  }

  public void setActivationState(ActivationState activationState) {
    this.activationState = activationState;
  }

  public void setEvent(Event event) {
    this.event = event;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setRegistrant(User registrant) {
    this.registrant = registrant;
  }
}
