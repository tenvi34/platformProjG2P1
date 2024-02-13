package musicStreaming.domain.FileTasks;

import java.util.Optional;

import musicStreaming._global.event.MusicFileUpdateRequested;
import musicStreaming._global.event.MusicFileUpdated;
import musicStreaming._global.exceptions.FileNotFoundException;
import musicStreaming._global.externalSystemProxy.ExternalSystemProxyService;
import musicStreaming._global.externalSystemProxy.reqDtos.GetDataUrlReqDto;
import musicStreaming._global.resources.ResourcesService;

import musicStreaming.domain.File;
import musicStreaming.domain.FileRepository;

public class UpdateMusicFileTask {
    // 이미 존재하는 파일 정보를 갱신하면서 관련 음악 파일 DATA URL을 요청해서 얻고, 디코딩 후, 파일을 저장시키기 위해서
    public static void updateMusicFileTask(MusicFileUpdateRequested musicFileUpdateRequested,
            FileRepository fileRepository, ResourcesService resourcesService,
            ExternalSystemProxyService externalSystemProxyService
        ) throws Exception {

        // [1] 얻어진 DataUrlCode를 externalSystemProxyService를 이용해서 요청해서 실제 DataUrl을 얻습니다.
        GetDataUrlReqDto getDataUrlReqDto = new GetDataUrlReqDto(musicFileUpdateRequested.getDataUrlCode());
        String DataUrlToUpdate = externalSystemProxyService.getDataUrl(getDataUrlReqDto).getDataUrl();
        
        // [2] resourcesService로 DataUrl을 저장해서 Path를 얻습니다.
        String pathToUpdate = resourcesService.writeMp3FileFromDataUrl(DataUrlToUpdate);
        

        // [3] fileRepository에서 fildId를 통해서 수정시킬 File 데이터를 얻습니다.
        Optional<File> optionalFile = fileRepository.findById(musicFileUpdateRequested.getFileId());
        if(!optionalFile.isPresent()) throw new FileNotFoundException();
        File fileToUpdate = optionalFile.get();

        // [4] 기존에 존재하는 파일을 삭제시킵니다.
        resourcesService.deleteFile(fileToUpdate.getPath());

        // [5] path를 새로운 파일의 path로 수정합니다.
        fileToUpdate.setPath(pathToUpdate);
        File savedFile = fileRepository.save(fileToUpdate);


        // [6] 총 음악의 길이를 resourcesService로 얻습니다.
        Integer totalSeconds = resourcesService.getMp3TotalSeconds(fileToUpdate.getPath());

        // [7] MusicFileUpdated 이벤트를 발생시킵니다.
        (new MusicFileUpdated(savedFile, musicFileUpdateRequested.getId(), totalSeconds, musicFileUpdateRequested.getDataUrlCode())).publishAfterCommit();

    }
}
