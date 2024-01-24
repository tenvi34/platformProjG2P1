package musicStreaming.domain.FileTasks;

import java.util.Optional;

import org.bouncycastle.asn1.dvcs.Data;

import musicStreaming._global.event.MusicFileUpdateRequested;
import musicStreaming._global.event.MusicFileUpdated;
import musicStreaming._global.externalSystemProxy.ExternalSystemProxyService;
import musicStreaming._global.externalSystemProxy.reqDtos.GetDataUrlReqDto;
import musicStreaming._global.externalSystemProxy.resDtos.GetDataUrlResDto;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;
import musicStreaming._global.resources.ResourcesService;
import musicStreaming.domain.File;
import musicStreaming.domain.FileRepository;

public class UpdateMusicFileTask {
    // 이미 존재하는 파일 정보를 갱신하면서 관련 음악 파일 DATA URL을 요청해서 얻고, 디코딩 후, 파일을 저장시키기 위해서
    public static void updateMusicFileTask(MusicFileUpdateRequested musicFileUpdateRequested,
            FileRepository fileRepository, ResourcesService resourcesService,
            ExternalSystemProxyService externalSystemProxyService
        ) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: updateMusicFile");

        // [1] 얻어진 DataUrlCode를 externalSystemProxyService를 이용해서 요청해서 실제 DataUrl을 얻습니다.
        GetDataUrlReqDto dto = new GetDataUrlReqDto(musicFileUpdateRequested.getDataUrlCode());
        GetDataUrlResDto getDataDto = null;
        try {
            getDataDto = externalSystemProxyService.getDataUrl(dto);
        } catch (Exception e) { return ;}
        
        String DataUrl = getDataDto.getDataUrl();
        // [2] resourcesService로 DataUrl을 저장해서 Path를 얻습니다.
        String path = resourcesService.writeMp3FileFromDataUrl(DataUrl);
        
        // [3] fileRepository에서 fildId를 통해서 수정시킬 File 데이터를 얻습니다.
        Optional<File> file = fileRepository.findById(musicFileUpdateRequested.getFileId());
        File files = file.get();
        // [4] 기존에 존재하는 파일을 삭제시킵니다.
        if (file.isPresent()) {
            resourcesService.deleteFile(files.getPath());
        }
        // [5] path를 새로운 파일의 path로 수정합니다.
        files.setPath(path);
        fileRepository.save(files);
        // [6] 총 음악의 길이를 resourcesService로 얻습니다.
        int musicLength = resourcesService.getMp3TotalSeconds(files.getPath());
        // [7] MusicFileUpdated 이벤트를 발생시킵니다.
        MusicFileUpdated musicFileUpdated = new MusicFileUpdated(files,files.getId(), musicLength,files.getPath());
        musicFileUpdated.publishAfterCommit();
    }
}
