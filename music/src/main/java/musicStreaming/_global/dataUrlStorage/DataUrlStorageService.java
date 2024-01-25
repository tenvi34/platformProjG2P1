package musicStreaming._global.dataUrlStorage;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import org.springframework.stereotype.Service;

import musicStreaming._global.dataUrlStorage.exceptions.DataUrlIOException;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

@Service
public class DataUrlStorageService {
    private String path(String filePath) {
        return "./dataUrlStorage/" + filePath;
    } 
    
    /**
     * 설명)<p>
     * 주어진 DataUrl을 파일에 쓰고, 해당 파일에 해당하는 코드를 반환하기 위해서<p>
     * 예제)<p>
     * writeDataUrlToFile("data:audio/mpeg;base64,SUQzB...") -> "3fc46480-3818-47d9-abcb-8e3167d0a2ab"<p>
     * DATA URL을 전달해서 파일을 생성하고, 생성된 파일과 매칭되는 코드를 반환합니다.<p>
     * 영향)<p>
     * 이 함수는 dataUrlStorage 폴더 내부에 파일을 직접 생성시킵니다.<p>
     * 반환된 코드는 다른 함수에서 읽기나 삭제로 사용할 수 있습니다.<p>
     * @param dataUrl 특정 파일에 대한 데이터를 나타내는 DataUrl
     * @return 쓰여진 파일을 조작하기 위한 DataCode
     */
    public String writeDataUrlToFile(String dataUrl) {
        String dataUrlCode = UUID.randomUUID().toString();
        String writeFilePath = this.path(dataUrlCode);

        try {

            CustomLogger.debug(
                CustomLoggerType.EFFECT, 
                String.format("Try to write dataUrl to '%s'", writeFilePath),
                String.format("{dataUrlLength: %d}", dataUrl.length())
            );
            FileWriter writer = new FileWriter(writeFilePath);
            writer.write(dataUrl);
            writer.close();

        } catch(IOException ex) {
            throw new DataUrlIOException(ex.getMessage());
        }

        return dataUrlCode;
    }

    /**
     * 설명)<p>
     * 데이터 코드로부터 매칭되는 파일에 있는 데이터를 얻기 위해서<p>
     * 예제)<p>
     * readDataUrlFromDataCode("3fc46480-3818-47d9-abcb-8e3167d0a2ab") -> "data:audio/mpeg;base64,SUQzB..."<p>
     * @param dataUrlCode 접근을 위한 특정 파일과 매칭되는 dataUrlCode
     * @return 특정 데이터에 대한 DataUrl
     */
    public String readDataUrlFromDataCode(String dataUrlCode) {
        String readText = "";
        String readFilePath = this.path(dataUrlCode);

        try {

            CustomLogger.debug(CustomLoggerType.EFFECT, String.format("Try to read dataUrl from '%s'", readFilePath));
            List<String> readTexts = new ArrayList<>();
            Scanner sc = new Scanner(new File(readFilePath));
            while (sc.hasNextLine())
                readTexts.add(sc.nextLine());
            sc.close();

            readText = String.join("\n", readTexts);
            CustomLogger.debug(
                CustomLoggerType.EFFECT,
                String.format("DataUrl was read from '%s'", readFilePath),
                String.format("{readTextLength: %d}", readText.length())
            );

        } catch(FileNotFoundException ex) {
            throw new DataUrlIOException(ex.getMessage());
        }
        
        return readText;
    }

    /**
     * 설명)<p>
     * 데이터 코드와 매칭되는 파일을 삭제시키기 위해서<p>
     * 예제)<p>
     * deleteDataUrlFile("3fc46480-3818-47d9-abcb-8e3167d0a2ab")<p>
     * @param dataUrlCode 접근을 위한 특정 파일과 매칭되는 dataUrlCode
     */
    public void deleteDataUrlFile(String dataUrlCode) {
        String deleteFilePath = this.path(dataUrlCode);

        CustomLogger.debug(CustomLoggerType.EFFECT, String.format("Try to delete file from '%s'", deleteFilePath));
        (new File(deleteFilePath)).delete();
    }
}
