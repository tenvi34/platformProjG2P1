package musicStreaming.music.resDtos;

import lombok.Getter;
import lombok.ToString;
import musicStreaming.domain.Music;

@Getter
@ToString
public class UpdateMusicInfoResDto {
    private final Long id;

    public UpdateMusicInfoResDto(Music music) {
        this.id = music.getId();
    }
}
