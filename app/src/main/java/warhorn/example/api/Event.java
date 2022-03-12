package warhorn.example.api;

import java.util.Collections;
import java.util.List;

public class Event extends BaseNode {
  private List<EventRole> roles;
  private String title;

  public Event() {
    roles = Collections.emptyList();
  }

  public List<EventRole> getRoles() {
    return roles;
  }

  public String getTitle() {
    return title;
  }

  public void setRoles(List<EventRole> roles) {
    this.roles = roles;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
