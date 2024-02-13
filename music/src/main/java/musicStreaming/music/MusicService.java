package musicStreaming.music;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import musicStreaming._global.dataUrlStorage.DataUrlStorageService;

import musicStreaming.domain.MusicRepository;

import musicStreaming.music.MusicServiceTasks.CreateMusicTask;
import musicStreaming.music.MusicServiceTasks.GetDataUrlTask;
import musicStreaming.music.MusicServiceTasks.LikeMusicTask;
import musicStreaming.music.MusicServiceTasks.UpdateMusicFileTask;
import musicStreaming.music.MusicServiceTasks.UpdateMusicInfoTask;
import musicStreaming.music.reqDtos.CreateMusicReqDto;
import musicStreaming.music.reqDtos.GetDataUrlReqDto;
import musicStreaming.music.reqDtos.LikeMusicReqDto;
import musicStreaming.music.reqDtos.UpdateMusicFileReqDto;
import musicStreaming.music.reqDtos.UpdateMusicInfoReqDto;
import musicStreaming.music.resDtos.CreateMusicResDto;
import musicStreaming.music.resDtos.GetDataUrlResDto;
import musicStreaming.music.resDtos.LikeMusicResDto;
import musicStreaming.music.resDtos.UpdateMusicFileResDto;
import musicStreaming.music.resDtos.UpdateMusicInfoResDto;

@Service
@RequiredArgsConstructor
public class MusicService {
    private final MusicRepository musicRepository;
    private final DataUrlStorageService dataUrlStorageService;

    // 주어진 DataURL을 저장하고, File 서비스에 업로드 요청을 수행하기 위해서
    public CreateMusicResDto createMusic(CreateMusicReqDto createMusicReqDto, Long userId) {
        return CreateMusicTask.createMusicTask(createMusicReqDto, userId, this.musicRepository, this.dataUrlStorageService);
    }

    // 주어진 DataUrlCode에 해당하는 DataUrl을 반환하기 위해서
    public GetDataUrlResDto getDataUrl(GetDataUrlReqDto getDataUrlReqDto) {
        return GetDataUrlTask.getDataUrlTask(getDataUrlReqDto, this.dataUrlStorageService);
    }


    // 음악 정보를 업데이트시키기 위해서
    public UpdateMusicInfoResDto updateMusicInfo(UpdateMusicInfoReqDto updateMusicInfoReqDto) {
        return UpdateMusicInfoTask.updateMusicInfoTask(updateMusicInfoReqDto, musicRepository);
    }

    // 음악 파일을 업데이트시키기 위해서
    public UpdateMusicFileResDto updateMusicFile(UpdateMusicFileReqDto updateMusicFileReqDto) {
        return UpdateMusicFileTask.updateMusicFileTask(updateMusicFileReqDto, musicRepository, dataUrlStorageService);
    }


    // 좋아요를 눌렀을 경우, 해당하는 음악의 좋아요 개수를 업데이트하기 위해서
    public LikeMusicResDto likeMusic(LikeMusicReqDto createMusicReqDto) {
        return LikeMusicTask.likeMusicTask(createMusicReqDto, musicRepository);
    }
}