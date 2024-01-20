package musicStreaming.sanityCheck.resDtos;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EchoWithJsonResDto {
    private final String message;

    public EchoWithJsonResDto(String message) {
        this.message = message;
    }
}
