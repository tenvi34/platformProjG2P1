package musicStreaming.sanityCheck.reqDtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GetMp3TotalSecondsReqDto {
    private String fileName = "";
}
