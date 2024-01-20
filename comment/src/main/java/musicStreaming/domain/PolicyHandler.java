package musicStreaming.domain;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import musicStreaming._global.config.kafka.KafkaProcessor;
import musicStreaming._global.event.MusicDeleted;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

@Service
@Transactional
public class PolicyHandler {
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    // 음악 정보가 삭제되었을 경우, 연관된 코멘트 정보들을 삭제시키기 위해서
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MusicDeleted'"
    )
    public void wheneverMusicDeleted_DeleteComment(
        @Payload MusicDeleted musicDeleted
    ) {
        try
        {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", musicDeleted.getClass().getSimpleName(), musicDeleted.toString()));
            Comment.deleteComment(musicDeleted);
            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", musicDeleted.getClass().getSimpleName(), musicDeleted.toString()));
        }
    }
}
