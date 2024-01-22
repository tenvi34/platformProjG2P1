package musicStreaming.domain.FileTasks;

import musicStreaming._global.event.MusicFileDeleteRequested;
import musicStreaming._global.event.MusicFileDeleted;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;
import musicStreaming._global.resources.ResourcesService;
import musicStreaming.domain.File;
import musicStreaming.domain.FileRepository;

public class DeleteMusicFileTask {
    // 요청된 음악 파일을 삭제시키기 위해서
    public static void deleteMusicFileTask(MusicFileDeleteRequested musicFileDeleteRequested,
            FileRepository fileRepository, ResourcesService resourcesService) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: deleteMusicFile");

        // [1] 주어진 fileId에 해당하는 File 데이터를 얻습니다.
        fileRepository.findById(musicFileDeleteRequested.getFileId()).ifPresent(deleteMusicFile -> {
            File file = new File(
                deleteMusicFile.getId(),
                deleteMusicFile.getCreaterId(),
                deleteMusicFile.getPath(),
                deleteMusicFile.getCreatedDate(),
                deleteMusicFile.getUpdatedDate()
            );

            // [2] 주어진 File의 path경로로 resourcesService를 이용해서 파일을 삭제합니다.
            resourcesService.deleteFile(file.getPath());

            // [3] 주어진 File도 삭제합니다.
            fileRepository.deleteAll();

            // [4] MusicFileDeleted 이벤트를 발생시킵니다.
            MusicFileDeleted musicFileDeleted = new MusicFileDeleted(deleteMusicFile, file.getId());
            musicFileDeleted.publishAfterCommit();
        });
    }
}
