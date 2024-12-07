package db.enums;

import javax.management.relation.Role;
import java.util.Arrays;
import java.util.Optional;

public enum Roles {

    MODERATOR(1),
    USER(2),
    GUEST(3);

    private final int id;

    Roles(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Optional<Roles> find(String role) {
        return Arrays.stream(values())
                .filter(it -> it.name().equals(role))
                .findFirst();
    }
}
