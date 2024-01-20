package musicStreaming.domain.file.sanityCheck.reqDtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MockMusicFileDeletedReqDto {
    private Long id;
    private Long musicId;
}
