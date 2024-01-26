package musicStreaming.domain.playListMusic.sanityCheck.reqDtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MockPlayListMusicDeletedReqDto {
    private Long id;
    private Long playListId;
}
