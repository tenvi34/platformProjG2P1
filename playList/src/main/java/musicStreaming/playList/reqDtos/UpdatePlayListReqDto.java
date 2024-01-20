package musicStreaming.playList.reqDtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UpdatePlayListReqDto {
    private Long playListId;
    private String title;
}
