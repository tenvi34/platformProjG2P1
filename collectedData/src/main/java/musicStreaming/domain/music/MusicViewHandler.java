package musicStreaming.domain.music;

import java.util.Optional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import musicStreaming._global.config.kafka.KafkaProcessor;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

import musicStreaming.domain.music.event.MusicCreated;
import musicStreaming.domain.music.event.MusicDeleted;
import musicStreaming.domain.music.event.MusicDeletedByFail;
import musicStreaming.domain.music.event.MusicFileDeleteRequested;
import musicStreaming.domain.music.event.MusicFileUpdateRequested;
import musicStreaming.domain.music.event.MusicFileUploadRequested;
import musicStreaming.domain.music.event.MusicInfoUpdated;
import musicStreaming.domain.music.event.MusicLiked;
import musicStreaming.domain.music.exceptions.MusicNotFoundException;

@Service
@RequiredArgsConstructor
public class MusicViewHandler {
    private final MusicRepository musicRepository;

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MusicFileUploadRequested'"
    )
    public void whenMusicFileUploadRequested_then_createMusic(
        @Payload MusicFileUploadRequested musicFileUploadRequested
    ) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", musicFileUploadRequested.getClass().getSimpleName(), musicFileUploadRequested.toString()));
            if (!musicFileUploadRequested.validate()) return;

            Music savedMusic = this.musicRepository.save(
                Music.builder()
                    .musicId(musicFileUploadRequested.getId())
                    .createrId(musicFileUploadRequested.getCreaterId())
                    .title(musicFileUploadRequested.getTitle())
                    .creater(musicFileUploadRequested.getCreater())
                    .likes(musicFileUploadRequested.getLikes())
                    .createdDate(musicFileUploadRequested.getCreatedDate())
                    .updatedDate(musicFileUploadRequested.getUpdatedDate())
                    .status("MusicFileUploadRequested")
                    .build()
            );

            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", savedMusic.getClass().getSimpleName(), savedMusic.toString()));

        } catch (Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", musicFileUploadRequested.getClass().getSimpleName(), musicFileUploadRequested.toString()));
        }
    }


    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MusicCreated'"
    )
    public void whenMusicCreated_then_updateMusicFileInfo(
        @Payload MusicCreated musicCreated
    ) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", musicCreated.getClass().getSimpleName(), musicCreated.toString()));
            if (!musicCreated.validate()) return;

            Optional<Music> optionalMusic = this.musicRepository.findByMusicId(musicCreated.getId());
            if(!optionalMusic.isPresent()) throw new MusicNotFoundException();


            Music musicToUpdate = optionalMusic.get();
            musicToUpdate.setFileId(musicCreated.getFileId());
            musicToUpdate.setTotalSeconds(musicCreated.getTotalSeconds());
            musicToUpdate.setStatus("MusicCreated");
            Music savedMusic = this.musicRepository.save(musicToUpdate);


            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", savedMusic.getClass().getSimpleName(), savedMusic.toString()));

        } catch (Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", musicCreated.getClass().getSimpleName(), musicCreated.toString()));
        }
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MusicDeletedByFail'"
    )
    public void whenMusicDeletedByFail_then_updateMusicStatus(
        @Payload MusicDeletedByFail musicDeletedByFail
    ) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", musicDeletedByFail.getClass().getSimpleName(), musicDeletedByFail.toString()));
            if (!musicDeletedByFail.validate()) return;

            Optional<Music> optionalMusic = this.musicRepository.findByMusicId(musicDeletedByFail.getId());
            if(!optionalMusic.isPresent()) throw new MusicNotFoundException();


            Music musicToUpdate = optionalMusic.get();
            musicToUpdate.setStatus("MusicDeletedByFail");
            Music savedMusic = this.musicRepository.save(musicToUpdate);


            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", savedMusic.getClass().getSimpleName(), savedMusic.toString()));

        } catch (Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", musicDeletedByFail.getClass().getSimpleName(), musicDeletedByFail.toString()));
        }
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MusicInfoUpdated'"
    )
    public void whenMusicInfoUpdated_then_updateMusicInfo(
        @Payload MusicInfoUpdated musicInfoUpdated
    ) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", musicInfoUpdated.getClass().getSimpleName(), musicInfoUpdated.toString()));
            if (!musicInfoUpdated.validate()) return;

            Optional<Music> optionalMusic = this.musicRepository.findByMusicId(musicInfoUpdated.getId());
            if(!optionalMusic.isPresent()) throw new MusicNotFoundException();


            Music musicToUpdate = optionalMusic.get();
            musicToUpdate.setTitle(musicInfoUpdated.getTitle());
            musicToUpdate.setUpdatedDate(musicInfoUpdated.getUpdatedDate());
            musicToUpdate.setStatus("MusicInfoUpdated");
            Music savedMusic = this.musicRepository.save(musicToUpdate);


            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", savedMusic.getClass().getSimpleName(), savedMusic.toString()));

        } catch (Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", musicInfoUpdated.getClass().getSimpleName(), musicInfoUpdated.toString()));
        }
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MusicFileUpdateRequested'"
    )
    public void whenMusicFileUpdateRequested_then_updateMusicStatus(
        @Payload MusicFileUpdateRequested musicFileUploadRequested
    ) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", musicFileUploadRequested.getClass().getSimpleName(), musicFileUploadRequested.toString()));
            if (!musicFileUploadRequested.validate()) return;

            Optional<Music> optionalMusic = this.musicRepository.findByMusicId(musicFileUploadRequested.getId());
            if(!optionalMusic.isPresent()) throw new MusicNotFoundException();


            Music musicToUpdate = optionalMusic.get();
            musicToUpdate.setUpdatedDate(musicFileUploadRequested.getUpdatedDate());
            musicToUpdate.setStatus("MusicFileUpdateRequested");
            Music savedMusic = this.musicRepository.save(musicToUpdate);


            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", savedMusic.getClass().getSimpleName(), savedMusic.toString()));

        } catch (Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", musicFileUploadRequested.getClass().getSimpleName(), musicFileUploadRequested.toString()));
        }
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MusicFileDeleteRequested'"
    )
    public void whenMusicFileDeleteRequested_then_updateMusicStatus(
        @Payload MusicFileDeleteRequested musicFileDeleteRequested
    ) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", musicFileDeleteRequested.getClass().getSimpleName(), musicFileDeleteRequested.toString()));
            if (!musicFileDeleteRequested.validate()) return;

            Optional<Music> optionalMusic = this.musicRepository.findByMusicId(musicFileDeleteRequested.getId());
            if(!optionalMusic.isPresent()) throw new MusicNotFoundException();


            Music musicToUpdate = optionalMusic.get();
            musicToUpdate.setUpdatedDate(musicFileDeleteRequested.getUpdatedDate());
            musicToUpdate.setStatus("musicFileDeleteRequested");
            Music savedMusic = this.musicRepository.save(musicToUpdate);


            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", savedMusic.getClass().getSimpleName(), savedMusic.toString()));

        } catch (Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", musicFileDeleteRequested.getClass().getSimpleName(), musicFileDeleteRequested.toString()));
        }
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MusicDeleted'"
    )
    public void whenMusicDeleted_then_updateMusicStatus(
        @Payload MusicDeleted musicDeleted
    ) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", musicDeleted.getClass().getSimpleName(), musicDeleted.toString()));
            if (!musicDeleted.validate()) return;

            Optional<Music> optionalMusic = this.musicRepository.findByMusicId(musicDeleted.getId());
            if(!optionalMusic.isPresent()) throw new MusicNotFoundException();


            Music musicToUpdate = optionalMusic.get();
            musicToUpdate.setStatus("MusicDeleted");
            Music savedMusic = this.musicRepository.save(musicToUpdate);


            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", savedMusic.getClass().getSimpleName(), savedMusic.toString()));

        } catch (Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", musicDeleted.getClass().getSimpleName(), musicDeleted.toString()));
        }
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MusicLiked'"
    )
    public void whenMusicLiked_then_updateMusicLikes(
        @Payload MusicLiked musicLiked
    ) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", musicLiked.getClass().getSimpleName(), musicLiked.toString()));
            if (!musicLiked.validate()) return;

            Optional<Music> optionalMusic = this.musicRepository.findByMusicId(musicLiked.getId());
            if(!optionalMusic.isPresent()) throw new MusicNotFoundException();


            Music musicToUpdate = optionalMusic.get();
            musicToUpdate.setLikes(musicLiked.getLikes());
            Music savedMusic = this.musicRepository.save(musicToUpdate);


            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", savedMusic.getClass().getSimpleName(), savedMusic.toString()));

        } catch (Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", musicLiked.getClass().getSimpleName(), musicLiked.toString()));
        }
    }
}
