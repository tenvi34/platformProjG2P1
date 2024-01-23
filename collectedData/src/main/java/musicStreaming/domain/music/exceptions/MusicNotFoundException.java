package musicStreaming.domain.music.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import musicStreaming._global.customExceptionControl.CustomException;

@Getter
public class MusicNotFoundException extends CustomException {
    public MusicNotFoundException() {
        super(
            HttpStatus.BAD_REQUEST,
            "MusicNotFoundException",
            "주어진 데이터와 매칭되는 Music을 발견하지 못했습니다."
        );
    }
}
