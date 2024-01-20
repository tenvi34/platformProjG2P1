package musicStreaming.sanityCheck.reqDtos;

import java.util.Date;

import lombok.Data;

@Data
public class MockMusicFileDeleteRequestedReqDto {
    private Long id;
    private String fileId;
    private Date updatedDate;
}
