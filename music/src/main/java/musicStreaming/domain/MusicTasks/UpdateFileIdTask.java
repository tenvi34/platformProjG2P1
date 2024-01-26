package musicStreaming.domain.MusicTasks;

import musicStreaming._global.dataUrlStorage.DataUrlStorageService;
import musicStreaming._global.event.MusicCreated;
import musicStreaming._global.exceptions.MusicNotFoundException;

import musicStreaming.domain.Music;
import musicStreaming.domain.MusicRepository;

public class UpdateFileIdTask {
    // 음악 파일이 업로드되었을 경우, 관련 파일 정보를 음악 정보에 반영시키기 위해서
    // 또한, Music 서비스에 임시로 저장되었던 DataUrlCode 파일을 삭제시키기 위해서
    public static void updateFileIdTask(Long musicId, Long fileId, Integer totalSeconds, String dataUrlCode, 
            MusicRepository musicRepository, DataUrlStorageService dataUrlStorageService) {

        // [1] musicRepository를 이용해서 주어진 musicId에 해당하는 Music 클래스를 얻습니다.
        Music musicToUpdate = musicRepository.findById(musicId)
            .orElseThrow(() -> new MusicNotFoundException());

        // [2] 얻어진 Music 클래스에서 fileId, totalSeconds를 업데이트하고, 저장합니다.
        musicToUpdate.setFileId(fileId);
        musicToUpdate.setTotalSeconds(totalSeconds);
        Music savedMusic = musicRepository.save(musicToUpdate);


        // [3] Music 데이터가 최종적으로 생성되었으므로, MusicCreated 이벤트를 발행합니다.
        (new MusicCreated(savedMusic, totalSeconds)).publishAfterCommit();

        // [4] dataUrlStorageService를 이용해서 dataUrlCode에 해당하는 파일을 삭제시킵니다.
        dataUrlStorageService.deleteDataUrlFile(dataUrlCode);

    }
}
