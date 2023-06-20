package Filmex.api.domain.user;

public record DataDetailingUser(Long id, String name) {

    public DataDetailingUser(User user) {
        this(user.getId(), user.getName());
    }
}
