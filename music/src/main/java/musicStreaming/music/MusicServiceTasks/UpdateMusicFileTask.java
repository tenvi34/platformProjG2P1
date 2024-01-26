package musicStreaming.music.MusicServiceTasks;

import musicStreaming._global.dataUrlStorage.DataUrlStorageService;
import musicStreaming._global.event.MusicFileUpdateRequested;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

import musicStreaming.domain.Music;
import musicStreaming.domain.MusicRepository;

import musicStreaming.music.reqDtos.UpdateMusicFileReqDto;
import musicStreaming.music.resDtos.UpdateMusicFileResDto;

public class UpdateMusicFileTask {
    // 음악 파일을 업데이트시키기 위해서
    public static UpdateMusicFileResDto updateMusicFileTask(UpdateMusicFileReqDto updateMusicFileReqDto,
        MusicRepository musicRepository, DataUrlStorageService dataUrlStorageService) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: updateMusicFile");

        // [1] updateMusicInfoReqDto에서 musicId를 얻어서 해당하는 Music 데이터 얻기
        Long musicId = updateMusicFileReqDto.getMusicId();
        Music music = musicRepository.findById(musicId)
                .orElseThrow(() -> new IllegalArgumentException("No music found with id: " + musicId));
        // [2] 전달된 dataUrl을 dataUrlStorageService에 저장하고, DataUrlCode를 얻습니다.
        String dataUrl = updateMusicFileReqDto.getDataUrl();
        String dataUrlCode = dataUrlStorageService.writeDataUrlToFile(dataUrl);
        // [3] DataUrlCode와 저장한 Music 데이터를 기반으로 MusicFileUpdateRequested 이벤트를 발생시킵니다.
        MusicFileUpdateRequested musicFileUpdateRequested = new MusicFileUpdateRequested(music, dataUrlCode);
        musicFileUpdateRequested.publish();

        return new UpdateMusicFileResDto(music);
    }
}
