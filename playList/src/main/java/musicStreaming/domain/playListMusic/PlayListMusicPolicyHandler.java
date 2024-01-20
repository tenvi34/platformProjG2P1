package musicStreaming.domain.playListMusic;

import javax.transaction.Transactional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import musicStreaming._global.config.kafka.KafkaProcessor;
import musicStreaming._global.event.MusicDeleted;
import musicStreaming._global.event.PlayListDeleted;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

@Service
@Transactional
public class PlayListMusicPolicyHandler {
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    // 플레이 리스트가 삭제되었을 경우, 관련된 플레이 리스트 음악들을 삭제시키기 위해서
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PlayListDeleted'"
    )
    public void wheneverPlayListDeleted_DeletePlayListMusic(
        @Payload PlayListDeleted playListDeleted
    ) {
        try
        {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", playListDeleted.getClass().getSimpleName(), playListDeleted.toString()));
            PlayListMusic.deletePlayListMusic(playListDeleted);
            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", playListDeleted.getClass().getSimpleName(), playListDeleted.toString()));
        }
    }

    // 음악 정보가 삭제되었을 경우, 플레이 리스트에서 해당하는 음악을 삭제시키기 위해서
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MusicDeleted'"
    )
    public void wheneverMusicDeleted_DeletePlayListMusic(
        @Payload MusicDeleted musicDeleted
    ) {
        try
        {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", musicDeleted.getClass().getSimpleName(), musicDeleted.toString()));
            PlayListMusic.deletePlayListMusic(musicDeleted);
            CustomLogger.debug(CustomLoggerType.EXIT);

        } catch(Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", musicDeleted.getClass().getSimpleName(), musicDeleted.toString()));
        }
    }
}
