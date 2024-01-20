package musicStreaming.domain.playList;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import musicStreaming._global.config.kafka.KafkaProcessor;
import musicStreaming._global.event.PlayListMusicCreated;
import musicStreaming._global.event.PlayListMusicDeleted;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

@Service
@Transactional
public class PlayListPolicyHandler {
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    // 플레이 리스트에 음악이 추가되었을 경우, 음악 개수를 증가시키기 위해서
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PlayListMusicCreated'"
    )
    public void wheneverPlayListMusicCreated_IncreaseMusicCount(
        @Payload PlayListMusicCreated playListMusicCreated
    ) {
        try
        {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", playListMusicCreated.getClass().getSimpleName(), playListMusicCreated.toString()));
            PlayList.increaseMusicCount(playListMusicCreated);
            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", playListMusicCreated.getClass().getSimpleName(), playListMusicCreated.toString()));
        }
    }

    // 플레이 리스트에 음악이 삭제되었을 경우, 음악 개수를 감소시키기 위해서
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PlayListMusicDeleted'"
    )
    public void wheneverPlayListMusicDeletedd_DecreaseMusicCount(
        @Payload PlayListMusicDeleted playListMusicDeleted
    ) {
        try
        {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", playListMusicDeleted.getClass().getSimpleName(), playListMusicDeleted.toString()));
            PlayList.decreaseMusicCount(playListMusicDeleted);
            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", playListMusicDeleted.getClass().getSimpleName(), playListMusicDeleted.toString()));
        }
    }
}
