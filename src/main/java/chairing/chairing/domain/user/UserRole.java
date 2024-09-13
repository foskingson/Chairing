package chairing.chairing.domain.user;

public enum UserRole {
    NORMAL("NORMAL"),
    ADMIN("ROLE_ADMIN"),
    GUARDIAN("ROLE_GUARDIAN");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }
}
