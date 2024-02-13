package musicStreaming.domain.playList.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import musicStreaming._global.customExceptionControl.CustomException;

@Getter
public class PlayListNotFoundException extends CustomException {
    public PlayListNotFoundException() {
        super(
            HttpStatus.BAD_REQUEST,
            "PlayListNotFoundException",
            "주어진 데이터와 매칭되는 PlayList을 발견하지 못했습니다."
        );
    }
}
