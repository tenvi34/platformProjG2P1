package musicStreaming.domain.playListMusic.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import musicStreaming._global.customExceptionControl.CustomException;

@Getter
public class PlayListMusicNotFoundException extends CustomException {
    public PlayListMusicNotFoundException() {
        super(
            HttpStatus.BAD_REQUEST,
            "PlayListMusicNotFoundException",
            "주어진 데이터와 매칭되는 PlayListMusic을 발견하지 못했습니다."
        );
    }
}
