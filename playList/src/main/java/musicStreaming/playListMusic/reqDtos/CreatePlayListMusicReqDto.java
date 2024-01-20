package musicStreaming.playListMusic.reqDtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreatePlayListMusicReqDto {
    private Long playListId;
    private Long musicId;
    private String title;
}
