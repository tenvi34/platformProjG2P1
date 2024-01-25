package musicStreaming._global.resources;

import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;

import java.util.Base64;
import java.util.UUID;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.springframework.stereotype.Service;

import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;
import musicStreaming._global.resources.exceptions.ResourcesIOException;


@Service
public class ResourcesService {
    private String path(String filePath) {
        return "./resources/" + filePath;
    } 

    /**
     * 설명)<p>
     * 주어진 DataUrl로 .mp3파일을 생성시키기 위해서<p>
     * 예제)<p>
     * writeMp3FileFromDataUrl("data:audio/mpeg;base64,SUQzB...") -> "3fc46480-3818-47d9-abcb-8e3167d0a2ab.mp3"<p>
     * DATA URL을 전달해서 파일을 생성하고, 생성된 파일의 이름을 반환합니다.<p>
     * 영향)<p>
     * 이 함수는 resouces 폴더 내부에 파일을 직접 생성시킵니다.<p>
     * @param dataUrl 특정 파일에 대한 데이터를 나타내는 DataUrl
     * @return 쓰여진 파일을 조작하기 위한 파일명
     */
    public String writeMp3FileFromDataUrl(String dataUrl) {
        String fileName = UUID.randomUUID().toString() + ".mp3";

        byte[] decodedBytes = Base64.getDecoder().decode(dataUrl.split(",")[1]);
        try {
            
            FileOutputStream fos = new FileOutputStream(this.path(fileName));
            fos.write(decodedBytes);
            fos.close();

        } catch (IOException e) {
            throw new ResourcesIOException(e.getMessage());
        }

        return fileName;
    }

    /**
     * 설명)<p>
     * 주어진 mp3 파일의 재생 시간을 초단위로 얻기 위해서<p>
     * 예제)<p>
     * getMp3TotalSeconds("3fc46480-3818-47d9-abcb-8e3167d0a2ab.mp3") -> 150(150초)<p>
     * 영향)<p>
     * 이 함수는 resouces 폴더 내부의 파일에 접근해서 읽기 연산을 합니다.<p>
     * @param fileName 대상이 되는 .mp3 파일명
     * @return .mp3 파일의 재생시간(초)
     */
    public Integer getMp3TotalSeconds(String fileName) {
        try {

            AudioFileFormat fileFormat = new MpegAudioFileReader().getAudioFileFormat(new File(this.path(fileName)));
            long microseconds = (long) fileFormat.properties().get("duration");
            Integer totalSeconds = (int)(microseconds / 1000000);
            return totalSeconds;

        } catch (UnsupportedAudioFileException | IOException e) {
            throw new ResourcesIOException(e.getMessage());
        }
    }

    /**
     * 설명)<p>
     * 해당 경로에 있는 파일을 삭제시키기 위해서<p>
     * 예제)<p>
     * deleteFile("3fc46480-3818-47d9-abcb-8e3167d0a2ab.mp3")<p>
     * 영향)<p>
     * 이 함수는 resouces 폴더 내부의 파일에 접근해서 삭제 연산을 합니다.<p>
     * @param fileName 대상이 되는 파일명
     */
    public void deleteFile(String fileName) {
        (new File(this.path(fileName))).delete();
    }
}
