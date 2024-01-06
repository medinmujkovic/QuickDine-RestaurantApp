package ba.unsa.etf.rpr.domain.enums;

public enum Role {
    ADMIN(1),
    CHEF(2),
    SERVICE(3);
    private final int id;

    Role(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return id;
    }
    public static Role fromRoleId(int roleId) {
        for (Role role : Role.values()) {
            if (role.getRoleId() == roleId) {
                return role;
            }
        }
        return null;
    }
}

