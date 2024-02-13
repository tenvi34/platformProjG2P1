package musicStreaming._global.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import musicStreaming._global.customExceptionControl.CustomException;

@Getter
public class FileNotFoundException extends CustomException {
    public FileNotFoundException() {
        super(
            HttpStatus.BAD_REQUEST,
            "FileNotFoundException",
            "주어진 데이터와 매칭되는 File을 발견하지 못했습니다."
        );
    }
}
