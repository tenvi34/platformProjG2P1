package musicStreaming._global.dataUrlStorage.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import musicStreaming._global.customExceptionControl.CustomException;

@Getter
public class DataUrlIOException extends CustomException {
    public DataUrlIOException(String message) {
        super(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "DataUrlIOException",
            "DataUrlStoage 처리 도중에 IO 예외가 발생했습니다.: " + message
        );
    }
}
