package musicStreaming.domain.file.sanityCheck.reqDtos;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MockMusicFileUpdatedReqDto {
    private Long id;
    private Long createrId;
    private Long musicId;
    private String path;
    private Date createdDate;
    private Date updatedDate;
    private Integer totalSeconds;
    private String dataUrlCode;
}
