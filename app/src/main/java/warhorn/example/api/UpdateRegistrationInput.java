package warhorn.example.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateRegistrationInput {
  private String registrationId;
  private boolean isClearedForSignup;

  public UpdateRegistrationInput(String registrationId, boolean isClearedForSignup) {
    this.registrationId = registrationId;
    this.isClearedForSignup = isClearedForSignup;
  }

  public String getRegistrationId() {
    return registrationId;
  }

  @JsonProperty("isClearedForSignup")
  public boolean isClearedForSignup() {
    return isClearedForSignup;
  }
}
