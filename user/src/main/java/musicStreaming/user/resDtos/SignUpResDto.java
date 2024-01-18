package musicStreaming.user.resDtos;

import lombok.Getter;
import lombok.ToString;
import musicStreaming.domain.User;

@Getter
@ToString
public class SignUpResDto {
    private final Long id;

    public SignUpResDto(User user) {
        this.id = user.getId();
    }
}
