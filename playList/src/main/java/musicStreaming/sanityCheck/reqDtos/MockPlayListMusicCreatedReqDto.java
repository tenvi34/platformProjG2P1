package musicStreaming.sanityCheck.reqDtos;

import java.util.Date;

import lombok.Data;

@Data
public class MockPlayListMusicCreatedReqDto {
    private Long id;
    private Long playListId;
    private Long musicId;
    private Long createrId;
    private String title;
    private Date createdDate;
    private Date updatedDate;
}
