package musicStreaming.music.MusicServiceTasks;

import musicStreaming._global.dataUrlStorage.DataUrlStorageService;

import musicStreaming.music.reqDtos.GetDataUrlReqDto;
import musicStreaming.music.resDtos.GetDataUrlResDto;

public class GetDataUrlTask {
    // 주어진 DataUrlCode에 해당하는 DataUrl을 반환하기 위해서
    public static GetDataUrlResDto getDataUrlTask(GetDataUrlReqDto getDataUrlReqDto, DataUrlStorageService dataUrlStorageService) {
        // [1] dataUrlStorageService에서 주어진 DataUrlCode로 DataUrl을 얻고, 반환합니다. 
        String DataUrl = dataUrlStorageService.readDataUrlFromDataCode(getDataUrlReqDto.getDataUrlCode());
        
        return new GetDataUrlResDto(DataUrl);
    }
}
