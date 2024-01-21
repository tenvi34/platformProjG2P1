package musicStreaming._global.resources.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import musicStreaming._global.customExceptionControl.CustomException;

@Getter
public class ResourcesIOException extends CustomException {
    public ResourcesIOException(String message) {
        super(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "ResourcesIOException",
            "Resources 처리 도중에 IO 예외가 발생했습니다.: " + message
        );
    }
}
