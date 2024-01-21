package musicStreaming.sanityCheck.resDtos;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GetMp3TotalSecondsResDto {
    private final Integer totalSeconds;

    public GetMp3TotalSecondsResDto(Integer totalSeconds) {
        this.totalSeconds = totalSeconds;
    }
}
