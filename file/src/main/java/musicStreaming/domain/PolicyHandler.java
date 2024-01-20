package musicStreaming.domain;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import musicStreaming._global.config.kafka.KafkaProcessor;
import musicStreaming._global.event.MusicFileDeleteRequested;
import musicStreaming._global.event.MusicFileUpdateRequested;
import musicStreaming._global.event.MusicFileUploadRequested;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

@Service
@Transactional
public class PolicyHandler {
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    // 관련 음악 파일 DATA URL을 요청해서 얻고, 디코딩 후, 파일을 저장시키기 위해서
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MusicFileUploadRequested'"
    )
    public void wheneverMusicFileUploadRequested_UploadMusicFile(
        @Payload MusicFileUploadRequested musicFileUploadRequested
    ) {
        try
        {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", musicFileUploadRequested.getClass().getSimpleName(), musicFileUploadRequested.toString()));
            File.uploadMusicFile(musicFileUploadRequested);
            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", musicFileUploadRequested.getClass().getSimpleName(), musicFileUploadRequested.toString()));
        }
    }

    // 이미 존재하는 파일 정보를 갱신하면서 관련 음악 파일 DATA URL을 요청해서 얻고, 디코딩 후, 파일을 저장시키기 위해서
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MusicFileUpdateRequested'"
    )
    public void wheneverMusicFileUpdateRequested_UpdateMusicFile(
        @Payload MusicFileUpdateRequested musicFileUpdateRequested
    ) {
        try
        {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", musicFileUpdateRequested.getClass().getSimpleName(), musicFileUpdateRequested.toString()));
            File.updateMusicFile(musicFileUpdateRequested);
            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", musicFileUpdateRequested.getClass().getSimpleName(), musicFileUpdateRequested.toString()));
        }
    }

    // 요청된 음악 파일을 삭제시키기 위해서
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MusicFileDeleteRequested'"
    )
    public void wheneverMusicFileDeleteRequested_DeleteMusicFile(
        @Payload MusicFileDeleteRequested musicFileDeleteRequested
    ) {
        try
        {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", musicFileDeleteRequested.getClass().getSimpleName(), musicFileDeleteRequested.toString()));
            File.deleteMusicFile(musicFileDeleteRequested);
            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", musicFileDeleteRequested.getClass().getSimpleName(), musicFileDeleteRequested.toString()));
        }
    }
}
