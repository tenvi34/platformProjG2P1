package musicStreaming.domain.FileTasks;

import musicStreaming._global.event.MusicFileDeleteRequested;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;
import musicStreaming._global.resources.ResourcesService;

import musicStreaming.domain.FileRepository;

public class DeleteMusicFileTask {
    // 요청된 음악 파일을 삭제시키기 위해서
    public static void deleteMusicFileTask(MusicFileDeleteRequested musicFileDeleteRequested,
            FileRepository fileRepository, ResourcesService resourcesService) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: deleteMusicFile");

        // [1] 주어진 fileId에 해당하는 File 데이터를 얻습니다.
        // [2] 주어진 File의 path경로로 resourcesService를 이용해서 파일을 삭제합니다.
        // [3] 주어진 File도 삭제합니다.
        // [4] MusicFileDeleted 이벤트를 발생시킵니다.
    }
}
