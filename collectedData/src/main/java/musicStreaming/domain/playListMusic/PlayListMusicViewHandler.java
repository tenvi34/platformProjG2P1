package musicStreaming.domain.playListMusic;

import java.util.Optional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import musicStreaming._global.config.kafka.KafkaProcessor;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

import musicStreaming.domain.playListMusic.event.PlayListMusicCreated;
import musicStreaming.domain.playListMusic.event.PlayListMusicDeleted;
import musicStreaming.domain.playListMusic.event.PlayListMusicUpdated;
import musicStreaming.domain.playListMusic.exceptions.PlayListMusicNotFoundException;

@Service
@RequiredArgsConstructor
public class PlayListMusicViewHandler {
    private final PlayListMusicRepository playListMusicRepository;

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PlayListMusicCreated'"
    )
    public void whenPlayListMusicCreated_then_createPlayListMusic(
        @Payload PlayListMusicCreated playListMusicCreated
    ) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", playListMusicCreated.getClass().getSimpleName(), playListMusicCreated.toString()));
            if (!playListMusicCreated.validate()) return;

            PlayListMusic savedPlayListMusic = this.playListMusicRepository.save(
                PlayListMusic.builder()
                    .playListMusicId(playListMusicCreated.getId())
                    .playListId(playListMusicCreated.getPlayListId())
                    .musicId(playListMusicCreated.getMusicId())
                    .createrId(playListMusicCreated.getCreaterId())
                    .title(playListMusicCreated.getTitle())
                    .createdDate(playListMusicCreated.getCreatedDate())
                    .updatedDate(playListMusicCreated.getUpdatedDate())
                    .status("PlayListMusicCreated")
                    .build()
            );

            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", savedPlayListMusic.getClass().getSimpleName(), savedPlayListMusic.toString()));

        } catch (Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", playListMusicCreated.getClass().getSimpleName(), playListMusicCreated.toString()));
        }
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PlayListMusicUpdated'"
    )
    public void whenPlayListMusicUpdated_then_updatePlayListMusicTitle(
        @Payload PlayListMusicUpdated playListMusicUpdated
    ) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", playListMusicUpdated.getClass().getSimpleName(), playListMusicUpdated.toString()));
            if (!playListMusicUpdated.validate()) return;

            Optional<PlayListMusic> optionalPlayListMusic = this.playListMusicRepository.findByPlayListMusicId(playListMusicUpdated.getId());
            if(!optionalPlayListMusic.isPresent()) throw new PlayListMusicNotFoundException();


            PlayListMusic playListMusicToUpdate = optionalPlayListMusic.get();
            playListMusicToUpdate.setTitle(playListMusicToUpdate.getTitle());
            playListMusicToUpdate.setUpdatedDate(playListMusicToUpdate.getUpdatedDate());
            playListMusicToUpdate.setStatus("PlayListMusicUpdated");

            PlayListMusic savedPlayListMusic = this.playListMusicRepository.save(playListMusicToUpdate);


            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", savedPlayListMusic.getClass().getSimpleName(), savedPlayListMusic.toString()));

        } catch (Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", playListMusicUpdated.getClass().getSimpleName(), playListMusicUpdated.toString()));
        }
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PlayListMusicDeleted'"
    )
    public void whenPlayListMusicDeleted_then_updatePlayListMusicStatus(
        @Payload PlayListMusicDeleted playListMusicDeleted
    ) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", playListMusicDeleted.getClass().getSimpleName(), playListMusicDeleted.toString()));
            if (!playListMusicDeleted.validate()) return;

            Optional<PlayListMusic> optionalPlayListMusic = this.playListMusicRepository.findByPlayListMusicId(playListMusicDeleted.getId());
            if(!optionalPlayListMusic.isPresent()) throw new PlayListMusicNotFoundException();


            PlayListMusic playListMusicToUpdate = optionalPlayListMusic.get();
            playListMusicToUpdate.setStatus("PlayListMusicDeleted");

            PlayListMusic savedPlayListMusic = this.playListMusicRepository.save(playListMusicToUpdate);


            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", savedPlayListMusic.getClass().getSimpleName(), savedPlayListMusic.toString()));

        } catch (Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", playListMusicDeleted.getClass().getSimpleName(), playListMusicDeleted.toString()));
        }
    }
}
