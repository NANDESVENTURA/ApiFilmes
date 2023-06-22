package Filmex.api.domain.user;

public record DataDetailingUser(Long id, String name, String email) {

    public DataDetailingUser(User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }
}
