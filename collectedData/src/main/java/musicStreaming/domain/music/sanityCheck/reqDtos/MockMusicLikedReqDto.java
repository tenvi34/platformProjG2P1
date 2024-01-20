package musicStreaming.domain.music.sanityCheck.reqDtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MockMusicLikedReqDto {
    private Long id;
    private Integer likes;
}
