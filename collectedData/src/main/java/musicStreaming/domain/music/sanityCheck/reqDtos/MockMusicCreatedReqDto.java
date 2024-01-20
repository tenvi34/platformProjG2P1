package musicStreaming.domain.music.sanityCheck.reqDtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MockMusicCreatedReqDto {
    private Long id;
    private Long fileId;
    private Integer totalSeconds;
}
