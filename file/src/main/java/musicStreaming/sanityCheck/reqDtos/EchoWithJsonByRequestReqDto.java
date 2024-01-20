package musicStreaming.sanityCheck.reqDtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EchoWithJsonByRequestReqDto {
    private String message = "";
}
