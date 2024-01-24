package musicStreaming.domain.FileTasks;

import java.util.Optional;

import musicStreaming._global.event.MusicFileDeleteRequested;
import musicStreaming._global.event.MusicFileDeleted;
import musicStreaming._global.exceptions.FileNotFoundException;
import musicStreaming._global.resources.ResourcesService;

import musicStreaming.domain.File;
import musicStreaming.domain.FileRepository;

public class DeleteMusicFileTask {
    // 요청된 음악 파일을 삭제시키기 위해서
    public static void deleteMusicFileTask(MusicFileDeleteRequested musicFileDeleteRequested,
            FileRepository fileRepository, ResourcesService resourcesService) {

        // [1] 주어진 fileId에 해당하는 File 데이터를 얻습니다.
        Optional<File> optionalFile = fileRepository.findById(musicFileDeleteRequested.getFileId());
        if(!optionalFile.isPresent()) throw new FileNotFoundException();
        File fileToDelete = optionalFile.get();

        // [2] 주어진 File의 path경로로 resourcesService를 이용해서 파일을 삭제합니다.
        resourcesService.deleteFile(fileToDelete.getPath());

        // [3] 주어진 File도 삭제합니다.
        fileRepository.delete(fileToDelete);

        // [4] MusicFileDeleted 이벤트를 발생시킵니다.
        MusicFileDeleted musicFileDeleted = new MusicFileDeleted(fileToDelete, fileToDelete.getId());
        musicFileDeleted.publishAfterCommit();

    }
}
