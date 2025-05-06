package Collections.task4;

import java.util.List;
import java.util.Random;

public class User {
    private final Long id;
    private final String name;
    private List<User> friends;

    public User(String name) {
        this.name = name;
        this.id = new Random().nextLong();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;

        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}