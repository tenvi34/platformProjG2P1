package musicStreaming.user.resDtos;

import lombok.Getter;
import lombok.ToString;
import musicStreaming.domain.User;

@Getter
@ToString
public class SignUpResDto {
    private final Long id;
    private final String email;
    private final String name;

    public SignUpResDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
    }
}
