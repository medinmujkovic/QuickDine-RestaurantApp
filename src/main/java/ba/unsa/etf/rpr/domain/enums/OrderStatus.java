package ba.unsa.etf.rpr.domain.enums;

public enum OrderStatus {
    RECEIVED(1),
    IN_PROGRESS(2),
    READY_FOR_PICKUP(3);

    private final int statusId;

    OrderStatus(int status) {
        this.statusId = status;
    }

    public int getStatusId() {
        return statusId;
    }

    public static OrderStatus fromStatusId(int statusId) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getStatusId() == statusId) {
                return status;
            }
        }
        return null;
    }
}
