package musicStreaming.music;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;
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

import javax.transaction.Transactional;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/musics")
public class MusicController {
    private final MusicService musicService;

    // 주어진 DataURL을 저장하고, File 서비스에 업로드 요청을 수행하기 위해서
    @PutMapping("/createMusic")
    public ResponseEntity<CreateMusicResDto> createMusic(@RequestBody CreateMusicReqDto createMusicReqDto, @RequestHeader("User-Id") Long userId) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", createMusicReqDto.getClass().getSimpleName(), createMusicReqDto.toString()));
        
            CreateMusicResDto createMusicResDto = this.musicService.createMusic(createMusicReqDto, userId);
        
            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", createMusicResDto.getClass().getSimpleName(), createMusicResDto.toString()));
            return ResponseEntity.ok(createMusicResDto);

        } catch(Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", createMusicReqDto.getClass().getSimpleName(), createMusicReqDto.toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 주어진 DataUrlCode에 해당하는 DataUrl을 반환하기 위해서
    @PutMapping("/getDataUrl")
    public ResponseEntity<GetDataUrlResDto> getDataUrl(@RequestBody GetDataUrlReqDto getDataUrlReqDto) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", getDataUrlReqDto.getClass().getSimpleName(), getDataUrlReqDto.toString()));
        
            GetDataUrlResDto getDataUrlResDto = this.musicService.getDataUrl(getDataUrlReqDto);
        
            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", getDataUrlResDto.getClass().getSimpleName(), getDataUrlResDto.toString()));
            return ResponseEntity.ok(getDataUrlResDto);

        } catch(Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", getDataUrlReqDto.getClass().getSimpleName(), getDataUrlReqDto.toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    // 음악 정보를 업데이트시키기 위해서
    @PutMapping("/updateMusicInfo")
    public ResponseEntity<UpdateMusicInfoResDto> updateMusicInfo(@RequestBody UpdateMusicInfoReqDto updateMusicInfoReqDto) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", updateMusicInfoReqDto.getClass().getSimpleName(), updateMusicInfoReqDto.toString()));
        
            UpdateMusicInfoResDto updateMusicInfoResDto = this.musicService.updateMusicInfo(updateMusicInfoReqDto);
        
            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", updateMusicInfoResDto.getClass().getSimpleName(), updateMusicInfoResDto.toString()));
            return ResponseEntity.ok(updateMusicInfoResDto);

        } catch(Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", updateMusicInfoReqDto.getClass().getSimpleName(), updateMusicInfoReqDto.toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 음악 파일을 업데이트시키기 위해서
    @PutMapping("/updateMusicFile")
    public ResponseEntity<UpdateMusicFileResDto> updateMusicFile(@RequestBody UpdateMusicFileReqDto updateMusicFileReqDto) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", updateMusicFileReqDto.getClass().getSimpleName(), updateMusicFileReqDto.toString()));
        
            UpdateMusicFileResDto updateMusicFileResDto = this.musicService.updateMusicFile(updateMusicFileReqDto);
        
            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", updateMusicFileResDto.getClass().getSimpleName(), updateMusicFileResDto.toString()));
            return ResponseEntity.ok(updateMusicFileResDto);

        } catch(Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", updateMusicFileReqDto.getClass().getSimpleName(), updateMusicFileReqDto.toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    // 좋아요를 눌렀을 경우, 해당하는 음악의 좋아요 개수를 업데이트하기 위해서
    @PutMapping("/likeMusic")
    public ResponseEntity<LikeMusicResDto> likeMusic(@RequestBody LikeMusicReqDto createMusicReqDto) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", createMusicReqDto.getClass().getSimpleName(), createMusicReqDto.toString()));
        
            LikeMusicResDto likeMusicResDto = this.musicService.likeMusic(createMusicReqDto);
        
            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", likeMusicResDto.getClass().getSimpleName(), likeMusicResDto.toString()));
            return ResponseEntity.ok(likeMusicResDto);

        } catch(Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", createMusicReqDto.getClass().getSimpleName(), createMusicReqDto.toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
