package musicStreaming.domain.MusicTasks;

import musicStreaming._global.dataUrlStorage.DataUrlStorageService;
import musicStreaming._global.event.MusicFileUpdateFailed;

public class DeleteDataUrlTask {
    // 음악 파일이 업데이트에 실패 했을 경우, 관련 DataUrl을 삭제시키기 위해서
    public static void deleteDataUrlTask(MusicFileUpdateFailed musicFileUpdateFailed, 
        DataUrlStorageService dataUrlStorageService) {

        // [1] dataUrlStorageService를 이용해서 dataUrlCode에 해당하는 파일을 삭제시킵니다.
        dataUrlStorageService.deleteDataUrlFile(musicFileUpdateFailed.getDataUrlCode());

    }
}
