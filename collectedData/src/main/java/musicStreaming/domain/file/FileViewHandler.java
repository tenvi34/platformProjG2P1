package musicStreaming.domain.file;

import java.util.Optional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import musicStreaming._global.config.kafka.KafkaProcessor;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

import musicStreaming.domain.file.event.MusicFileDeleteFailed;
import musicStreaming.domain.file.event.MusicFileDeleted;
import musicStreaming.domain.file.event.MusicFileUpdateFailed;
import musicStreaming.domain.file.event.MusicFileUpdated;
import musicStreaming.domain.file.event.MusicFileUploadFailed;
import musicStreaming.domain.file.event.MusicFileUploaded;
import musicStreaming.domain.music.exceptions.MusicNotFoundException;

@Service
@RequiredArgsConstructor
public class FileViewHandler {
    private final FileRepository fileRepository;

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MusicFileUploaded'"
    )
    public void whenMusicFileUploaded_then_createFile(
        @Payload MusicFileUploaded musicFileUploaded
    ) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", musicFileUploaded.getClass().getSimpleName(), musicFileUploaded.toString()));
            if (!musicFileUploaded.validate()) return;

            File savedFile = this.fileRepository.save(
                File.builder()
                    .fileId(musicFileUploaded.getId())
                    .createrId(musicFileUploaded.getCreaterId())
                    .path(musicFileUploaded.getPath())
                    .createdDate(musicFileUploaded.getCreatedDate())
                    .updatedDate(musicFileUploaded.getUpdatedDate())
                    .status("musicFileUploaded")
                    .build()
            );

            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", savedFile.getClass().getSimpleName(), savedFile.toString()));

        } catch (Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", musicFileUploaded.getClass().getSimpleName(), musicFileUploaded.toString()));
        }
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MusicFileUploadFailed'"
    )
    public void whenMusicFileUploadFailed_then_createFileWithFail(
        @Payload MusicFileUploadFailed musicFileUploadFailed
    ) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", musicFileUploadFailed.getClass().getSimpleName(), musicFileUploadFailed.toString()));
            if (!musicFileUploadFailed.validate()) return;

            Optional<File> optionalFile = this.fileRepository.findByFileId(musicFileUploadFailed.getId());
            if(!optionalFile.isPresent()) throw new MusicNotFoundException();


            File fileToUpdate = optionalFile.get();
            fileToUpdate.setStatus("MusicFileUploadFailed");
            File savedFile = this.fileRepository.save(fileToUpdate);


            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", savedFile.getClass().getSimpleName(), savedFile.toString()));

        } catch (Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", musicFileUploadFailed.getClass().getSimpleName(), musicFileUploadFailed.toString()));
        }
    }


    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MusicFileUpdated'"
    )
    public void whenMusicFileUpdated_then_updateFile(
        @Payload MusicFileUpdated musicFileUpdated
    ) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", musicFileUpdated.getClass().getSimpleName(), musicFileUpdated.toString()));
            if (!musicFileUpdated.validate()) return;

            Optional<File> optionalFile = this.fileRepository.findByFileId(musicFileUpdated.getId());
            if(!optionalFile.isPresent()) throw new MusicNotFoundException();


            File fileToUpdate = optionalFile.get();
            fileToUpdate.setCreaterId(musicFileUpdated.getCreaterId());
            fileToUpdate.setPath(musicFileUpdated.getPath());
            fileToUpdate.setUpdatedDate(musicFileUpdated.getUpdatedDate());
            fileToUpdate.setStatus("MusicFileUpdated");
            File savedFile = this.fileRepository.save(fileToUpdate);


            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", savedFile.getClass().getSimpleName(), savedFile.toString()));

        } catch (Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", musicFileUpdated.getClass().getSimpleName(), musicFileUpdated.toString()));
        }
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MusicFileUpdateFailed'"
    )
    public void whenMusicFileUpdateFailed_then_updateFileStatus(
        @Payload MusicFileUpdateFailed musicFileUpdateFailed
    ) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", musicFileUpdateFailed.getClass().getSimpleName(), musicFileUpdateFailed.toString()));
            if (!musicFileUpdateFailed.validate()) return;

            Optional<File> optionalFile = this.fileRepository.findByFileId(musicFileUpdateFailed.getId());
            if(!optionalFile.isPresent()) throw new MusicNotFoundException();


            File fileToUpdate = optionalFile.get();;
            fileToUpdate.setStatus("MusicFileUpdateFailed");
            File savedFile = this.fileRepository.save(fileToUpdate);


            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", savedFile.getClass().getSimpleName(), savedFile.toString()));

        } catch (Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", musicFileUpdateFailed.getClass().getSimpleName(), musicFileUpdateFailed.toString()));
        }
    }


    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MusicFileDeleted'"
    )
    public void whenMusicFileDeleted_then_updateFileStatus(
        @Payload MusicFileDeleted musicFileDeleted
    ) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", musicFileDeleted.getClass().getSimpleName(), musicFileDeleted.toString()));
            if (!musicFileDeleted.validate()) return;

            Optional<File> optionalFile = this.fileRepository.findByFileId(musicFileDeleted.getId());
            if(!optionalFile.isPresent()) throw new MusicNotFoundException();


            File fileToUpdate = optionalFile.get();
            fileToUpdate.setStatus("MusicFileDeleted");
            File savedFile = this.fileRepository.save(fileToUpdate);


            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", savedFile.getClass().getSimpleName(), savedFile.toString()));

        } catch (Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", musicFileDeleted.getClass().getSimpleName(), musicFileDeleted.toString()));
        }
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MusicFileDeleteFailed'"
    )
    public void whenMusicFileDeleteFailed_then_updateFileStatus(
        @Payload MusicFileDeleteFailed musicFileDeleteFailed
    ) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", musicFileDeleteFailed.getClass().getSimpleName(), musicFileDeleteFailed.toString()));
            if (!musicFileDeleteFailed.validate()) return;

            Optional<File> optionalFile = this.fileRepository.findByFileId(musicFileDeleteFailed.getId());
            if(!optionalFile.isPresent()) throw new MusicNotFoundException();


            File fileToUpdate = optionalFile.get();
            fileToUpdate.setStatus("MusicFileDeleteFailed");
            File savedFile = this.fileRepository.save(fileToUpdate);


            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", savedFile.getClass().getSimpleName(), savedFile.toString()));

        } catch (Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", musicFileDeleteFailed.getClass().getSimpleName(), musicFileDeleteFailed.toString()));
        }
    }
}
