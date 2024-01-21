package musicStreaming.domain.MusicTasks;

import musicStreaming._global.dataUrlStorage.DataUrlStorageService;
import musicStreaming._global.event.MusicFileUploadFailed;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

import musicStreaming.domain.MusicRepository;

public class DeleteMusicByFailTask {
    // 음악 파일이 업로드에 실패 했을 경우, 음악 정보를 지우고, 관련 이벤트를 발생시키기 위해서
    public static void deleteMusicByFailTask(MusicFileUploadFailed musicFileUploadFailed, 
            MusicRepository musicRepository, DataUrlStorageService dataUrlStorageService) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: deleteMusicByFail");

        // [1] musicRepository를 이용해서 주어진 musicId에 해당하는 Music 클래스를 얻습니다.
        // [2] 얻어진 Music 클래스을 musicRepository에서 삭제시킵니다.
        // [3] Music 데이터가 최종적으로 삭제되었으므로, MusicDeletedByFail 이벤트를 발행합니다.
        // [4] dataUrlStorageService를 이용해서 dataUrlCode에 해당하는 파일을 삭제시킵니다.
    }
}
