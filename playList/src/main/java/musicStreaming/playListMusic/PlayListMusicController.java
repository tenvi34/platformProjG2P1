package musicStreaming.playListMusic;

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

import musicStreaming.playListMusic.reqDtos.CreatePlayListMusicReqDto;
import musicStreaming.playListMusic.reqDtos.UpdatePlayListMusicReqDto;
import musicStreaming.playListMusic.resDtos.CreatePlayListMusicResDto;
import musicStreaming.playListMusic.resDtos.UpdatePlayListMusicResDto;

import javax.transaction.Transactional;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/playListMusics")
public class PlayListMusicController {
    private final PlayListMusicService playListMusicService;

    // 플레이 리스트 음악을 생성시키기 위해서
    @PutMapping("/createPlayListMusic")
    public ResponseEntity<CreatePlayListMusicResDto> createPlayListMusic(@RequestBody CreatePlayListMusicReqDto createPlayListMusicReqDto, @RequestHeader("User-Id") Long userId) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", createPlayListMusicReqDto.getClass().getSimpleName(), createPlayListMusicReqDto.toString()));
        
            CreatePlayListMusicResDto createPlayListMusicResDto = this.playListMusicService.createPlayListMusic(createPlayListMusicReqDto, userId);
        
            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", createPlayListMusicResDto.getClass().getSimpleName(), createPlayListMusicResDto.toString()));
            return ResponseEntity.ok(createPlayListMusicResDto);

        } catch(Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", createPlayListMusicReqDto.getClass().getSimpleName(), createPlayListMusicReqDto.toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 플레이 리스트 음악을 수정시키기 위해서
    @PutMapping("/updatePlayListMusic")
    public ResponseEntity<UpdatePlayListMusicResDto> updatePlayListMusic(@RequestBody UpdatePlayListMusicReqDto updatePlayListMusicReqDto) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", updatePlayListMusicReqDto.getClass().getSimpleName(), updatePlayListMusicReqDto.toString()));
        
            UpdatePlayListMusicResDto updatePlayListMusicResDto = this.playListMusicService.updatePlayListMusic(updatePlayListMusicReqDto);
        
            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", updatePlayListMusicResDto.getClass().getSimpleName(), updatePlayListMusicResDto.toString()));
            return ResponseEntity.ok(updatePlayListMusicResDto);

        } catch(Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", updatePlayListMusicReqDto.getClass().getSimpleName(), updatePlayListMusicReqDto.toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
