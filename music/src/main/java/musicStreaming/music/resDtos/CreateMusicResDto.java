package musicStreaming.music.resDtos;

import lombok.Getter;
import lombok.ToString;
import musicStreaming.domain.Music;

@Getter
@ToString
public class CreateMusicResDto {
    private final Long id;

    public CreateMusicResDto(Music music) {
        this.id = music.getId();
    }
}
