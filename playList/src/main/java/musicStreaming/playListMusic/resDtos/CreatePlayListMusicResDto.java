package musicStreaming.playListMusic.resDtos;

import lombok.Getter;
import lombok.ToString;

import musicStreaming.domain.playListMusic.PlayListMusic;

@Getter
@ToString
public class CreatePlayListMusicResDto {
    private final Long id;

    public CreatePlayListMusicResDto(PlayListMusic playListMusic) {
        this.id = playListMusic.getId();
    }
}
