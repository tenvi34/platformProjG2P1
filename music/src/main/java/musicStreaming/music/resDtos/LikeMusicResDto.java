package musicStreaming.music.resDtos;

import lombok.Getter;
import lombok.ToString;
import musicStreaming.domain.Music;

@Getter
@ToString
public class LikeMusicResDto {
    private final Long id;
    private final Integer likes;

    public LikeMusicResDto(Music music) {
        this.id = music.getId();
        this.likes = music.getLikes();
    }
}
