package musicStreaming.domain.comment.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import musicStreaming._global.customExceptionControl.CustomException;

@Getter
public class CommentNotFoundException extends CustomException {
    public CommentNotFoundException() {
        super(
            HttpStatus.BAD_REQUEST,
            "CommentNotFoundException",
            "주어진 데이터와 매칭되는 Comment를 발견하지 못했습니다."
        );
    }
}
