package musicStreaming.playList.resDtos;

import lombok.Getter;
import lombok.ToString;

import musicStreaming.domain.playList.PlayList;

@Getter
@ToString
public class UpdatePlayListResDto {
    private final Long id;

    public UpdatePlayListResDto(PlayList playList) {
        this.id = playList.getId();
    }
}
