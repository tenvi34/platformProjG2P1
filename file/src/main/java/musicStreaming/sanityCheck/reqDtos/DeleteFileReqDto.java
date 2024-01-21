package musicStreaming.sanityCheck.reqDtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DeleteFileReqDto {
    private String fileName = "";
}
