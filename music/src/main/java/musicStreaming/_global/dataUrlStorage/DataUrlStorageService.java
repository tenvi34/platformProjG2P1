package musicStreaming._global.dataUrlStorage;

import java.io.FileWriter;
import java.io.IOException;

import java.util.UUID;

import org.springframework.stereotype.Service;

import musicStreaming._global.dataUrlStorage.exceptions.DataUrlIOException;

@Service
public class DataUrlStorageService {
    private String path(String filePath) {
        return "./dataUrlStorage/" + filePath;
    } 

    // DO)
    // 주어진 DataUrl을 파일에 쓰고, 해당 파일에 해당하는 코드를 반환하기 위해서
    // EX)
    // writeDataUrlToFile("data:audio/mpeg;base64,SUQzB...") -> "3fc46480-3818-47d9-abcb-8e3167d0a2ab"
    // DATA URL을 전달해서 파일을 생성하고, 생성된 파일과 매칭되는 코드를 반환합니다.
    // EF)
    // 이 함수는 dataUrlStorage 폴더 내부에 파일을 직접 생성시킵니다.
    // 반환된 코드는 다른 함수에서 읽기나 삭제로 사용할 수 있습니다.
    public String writeDataUrlToFile(String dataUrl) {
        String dataUrlCode = UUID.randomUUID().toString();
        try {

            FileWriter writer = new FileWriter(this.path(dataUrlCode));
            writer.write(dataUrl);
            writer.close();

        } catch(IOException ex) {
            throw new DataUrlIOException(ex.getMessage());
        }

        return dataUrlCode;
    }
}
