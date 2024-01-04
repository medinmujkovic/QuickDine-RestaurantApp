package ba.unsa.etf.rpr.domain.enums;

public enum StatusEnum {
    order_received(1),
    in_progress(2),
    ready_for_pickup(3);

    private final int statusId;

    StatusEnum(int status) {
        this.statusId = status;
    }

    public int getStatusId() {
        return statusId;
    }

    public static StatusEnum fromStatusId(int statusId) {
        for (StatusEnum status : StatusEnum.values()) {
            if (status.getStatusId() == statusId) {
                return status;
            }
        }
        return null;
    }
}
