package musicStreaming.playListMusic.reqDtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UpdatePlayListMusicReqDto {
    private Long playListMusicId;
    private String title;
}
