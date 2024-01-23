package musicStreaming.domain.playList;

import java.util.Optional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import musicStreaming._global.config.kafka.KafkaProcessor;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

import musicStreaming.domain.playList.event.PlayListCreated;
import musicStreaming.domain.playList.event.PlayListDeleted;
import musicStreaming.domain.playList.event.PlayListMusicCountUpdated;
import musicStreaming.domain.playList.event.PlayListUpdated;
import musicStreaming.domain.playList.exceptions.PlayListNotFoundException;

@Service
@RequiredArgsConstructor
public class PlayListViewHandler {
    private final PlayListRepository playListRepository;

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PlayListCreated'"
    )
    public void whenPlayListCreated_then_createPlayList(
        @Payload PlayListCreated playListCreated
    ) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", playListCreated.getClass().getSimpleName(), playListCreated.toString()));
            if (!playListCreated.validate()) return;

            PlayList savedPlayList = this.playListRepository.save(
                PlayList.builder()
                    .playListId(playListCreated.getId())
                    .createrId(playListCreated.getCreaterId())
                    .title(playListCreated.getTitle())
                    .musicCount(playListCreated.getMusicCount())
                    .createdDate(playListCreated.getCreatedDate())
                    .updatedDate(playListCreated.getUpdatedDate())
                    .status("PlayListCreated")
                    .build()
            );

            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", savedPlayList.getClass().getSimpleName(), savedPlayList.toString()));

        } catch (Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", playListCreated.getClass().getSimpleName(), playListCreated.toString()));
        }
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PlayListUpdated'"
    )
    public void whenPlayListUpdated_then_updatePlayListStatus(
        @Payload PlayListUpdated playListUpdated
    ) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", playListUpdated.getClass().getSimpleName(), playListUpdated.toString()));
            if (!playListUpdated.validate()) return;

            Optional<PlayList> optionalPlayList = this.playListRepository.findByPlayListId(playListUpdated.getId());
            if(!optionalPlayList.isPresent()) throw new PlayListNotFoundException();


            PlayList playListToUpdate = optionalPlayList.get();
            playListToUpdate.setTitle(playListUpdated.getTitle());
            playListToUpdate.setUpdatedDate(playListUpdated.getUpdatedDate());
            playListToUpdate.setStatus("PlayListUpdated");

            PlayList savedPlayList = this.playListRepository.save(playListToUpdate);


            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", savedPlayList.getClass().getSimpleName(), savedPlayList.toString()));

        } catch (Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", playListUpdated.getClass().getSimpleName(), playListUpdated.toString()));
        }
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PlayListMusicCountUpdated'"
    )
    public void whenPlayListMusicCountUpdated_then_updatePlayListMusicCount(
        @Payload PlayListMusicCountUpdated playListMusicCountUpdated
    ) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", playListMusicCountUpdated.getClass().getSimpleName(), playListMusicCountUpdated.toString()));
            if (!playListMusicCountUpdated.validate()) return;

            Optional<PlayList> optionalPlayList = this.playListRepository.findByPlayListId(playListMusicCountUpdated.getId());
            if(!optionalPlayList.isPresent()) throw new PlayListNotFoundException();


            PlayList playListToUpdate = optionalPlayList.get();
            playListToUpdate.setMusicCount(playListMusicCountUpdated.getMusicCount());
            playListToUpdate.setStatus("PlayListMusicCountUpdated");
            PlayList savedPlayList = this.playListRepository.save(playListToUpdate);


            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", savedPlayList.getClass().getSimpleName(), savedPlayList.toString()));

        } catch (Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", playListMusicCountUpdated.getClass().getSimpleName(), playListMusicCountUpdated.toString()));
        }
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PlayListDeleted'"
    )
    public void whenPlayListDeleted_then_updatePlayListStatus(
        @Payload PlayListDeleted playListDeleted
    ) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", playListDeleted.getClass().getSimpleName(), playListDeleted.toString()));
            if (!playListDeleted.validate()) return;

            Optional<PlayList> optionalPlayList = this.playListRepository.findByPlayListId(playListDeleted.getId());
            if(!optionalPlayList.isPresent()) throw new PlayListNotFoundException();


            PlayList playListToUpdate = optionalPlayList.get();
            playListToUpdate.setStatus("PlayListDeleted");

            PlayList savedPlayList = this.playListRepository.save(playListToUpdate);


            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", savedPlayList.getClass().getSimpleName(), savedPlayList.toString()));

        } catch (Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", playListDeleted.getClass().getSimpleName(), playListDeleted.toString()));
        }
    }
}
