package warhorn.example.api;

public class AssignRegistrationRoleInput {
  private String registrationId;
  private String roleId;

  public AssignRegistrationRoleInput(String registrationId, String roleId) {
    this.registrationId = registrationId;
    this.roleId = roleId;
  }

  public String getRegistrationId() {
    return registrationId;
  }

  public String getRoleId() {
    return roleId;
  }
}
