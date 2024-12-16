package db.enums;

public enum Statuses {
    ON_MODERATION(1),
    APPROVED(2),
    REJECTED(3),
    EDITING(4),
    DELETED(5);

    private final int id;

    Statuses(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
