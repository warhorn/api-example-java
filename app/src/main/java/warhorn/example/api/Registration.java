package warhorn.example.api;

import java.util.Collections;
import java.util.List;

public class Registration extends BaseNode {
  public enum ActivationState {
    ACTIVE,
    CANCELED,
  }

  private ActivationState activationState;
  private boolean isClearedForSignup;
  private Event event;
  private User registrant;
  private List<EventRole> roles;

  public Registration() {
    roles = Collections.emptyList();
  }

  public ActivationState getActivationState() {
    return activationState;
  }

  public Event getEvent() {
    return event;
  }

  public User getRegistrant() {
    return registrant;
  }

  public List<EventRole> getRoles() {
    return roles;
  }

  public boolean isActive() {
    return activationState == ActivationState.ACTIVE;
  }

  public boolean isCanceled() {
    return activationState == ActivationState.CANCELED;
  }

  public boolean isClearedForSignup() {
    return isClearedForSignup;
  }

  public void setActivationState(ActivationState activationState) {
    this.activationState = activationState;
  }

  public void setEvent(Event event) {
    this.event = event;
  }

  public void setIsClearedForSignup(boolean isClearedForSignup) {
    this.isClearedForSignup = isClearedForSignup;
  }

  public void setRegistrant(User registrant) {
    this.registrant = registrant;
  }

  public void setRoles(List<EventRole> roles) {
    this.roles = roles;
  }
}
