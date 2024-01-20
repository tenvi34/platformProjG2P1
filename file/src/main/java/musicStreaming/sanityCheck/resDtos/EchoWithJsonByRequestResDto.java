package musicStreaming.sanityCheck.resDtos;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EchoWithJsonByRequestResDto {
    private final String message;

    public EchoWithJsonByRequestResDto(String message) {
        this.message = message;
    }
}
