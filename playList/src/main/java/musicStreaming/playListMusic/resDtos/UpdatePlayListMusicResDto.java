package musicStreaming.playListMusic.resDtos;

import lombok.Getter;
import lombok.ToString;

import musicStreaming.domain.playListMusic.PlayListMusic;

@Getter
@ToString
public class UpdatePlayListMusicResDto {
    private final Long id;

    public UpdatePlayListMusicResDto(PlayListMusic playListMusic) {
        this.id = playListMusic.getId();
    }
}
