package musicStreaming.playList.resDtos;

import lombok.Getter;
import lombok.ToString;

import musicStreaming.domain.playList.PlayList;

@Getter
@ToString
public class CreatePlayListResDto {
    private final Long id;

    public CreatePlayListResDto(PlayList playList) {
        this.id = playList.getId();
    }
}
