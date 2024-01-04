package ba.unsa.etf.rpr.domain.enums;

public enum RoleEnum {
    admin(1),
    chef(2),
    service(3);
    private final int id;

    RoleEnum(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return id;
    }
    public static RoleEnum fromRoleId(int roleId) {
        for (RoleEnum role : RoleEnum.values()) {
            if (role.getRoleId() == roleId) {
                return role;
            }
        }
        return null;
    }
}

