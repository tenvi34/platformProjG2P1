package musicStreaming.music.resDtos;

import lombok.Getter;
import lombok.ToString;
import musicStreaming.domain.Music;

@Getter
@ToString
public class UpdateMusicFileResDto {
    private final Long id;

    public UpdateMusicFileResDto(Music music) {
        this.id = music.getId();
    }
}
