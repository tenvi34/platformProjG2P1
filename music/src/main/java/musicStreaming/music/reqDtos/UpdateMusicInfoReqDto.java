package musicStreaming.music.reqDtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UpdateMusicInfoReqDto {
    private Long musicId;
    private String title;
}
