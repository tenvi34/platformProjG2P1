package musicStreaming.domain;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import musicStreaming._global.config.kafka.KafkaProcessor;
import musicStreaming._global.event.MusicFileDeleted;
import musicStreaming._global.event.MusicFileUploadFailed;
import musicStreaming._global.event.MusicFileUploaded;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

@Service
@Transactional
public class PolicyHandler {
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    // 음악 파일이 업로드되었을 경우, 관련 파일 정보를 음악 정보에 반영시키기 위해서
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MusicFileUploaded'"
    )
    public void wheneverMusicFileUploaded_UpdateFileId(
        @Payload MusicFileUploaded musicFileUploaded
    ) {
        try
        {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", musicFileUploaded.getClass().getSimpleName(), musicFileUploaded.toString()));
            Music.updateFileId(musicFileUploaded);
            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", musicFileUploaded.getClass().getSimpleName(), musicFileUploaded.toString()));
        }
    }

    // 음악 파일이 업로드에 실패 했을 경우, 음악 정보를 지우고, 관련 이벤트를 발생시키기 위해서
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MusicFileUploadFailed'"
    )
    public void wheneverMusicFileUploadFailed_DeleteMusicByFail(
        @Payload MusicFileUploadFailed musicFileUploadFailed
    ) {
        try
        {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", musicFileUploadFailed.getClass().getSimpleName(), musicFileUploadFailed.toString()));
            Music.deleteMusicByFail(musicFileUploadFailed);
            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", musicFileUploadFailed.getClass().getSimpleName(), musicFileUploadFailed.toString()));
        }
    }

    // 음악 파일이 삭제되었을 경우, 최종적으로 관련 음악 정보를 삭제시키고, 관련 이벤트를 발생시키기 위해서
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MusicFileDeleted'"
    )
    public void wheneverMusicFileDeleted_DeleteMusic(
        @Payload MusicFileDeleted musicFileDeleted
    ) {
        try
        {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", musicFileDeleted.getClass().getSimpleName(), musicFileDeleted.toString()));
            Music.deleteMusic(musicFileDeleted);
            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", musicFileDeleted.getClass().getSimpleName(), musicFileDeleted.toString()));
        }
    }
}
