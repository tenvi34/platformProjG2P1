package musicStreaming.playList;

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

import musicStreaming.playList.reqDtos.CreatePlayListReqDto;
import musicStreaming.playList.reqDtos.UpdatePlayListReqDto;
import musicStreaming.playList.resDtos.CreatePlayListResDto;
import musicStreaming.playList.resDtos.UpdatePlayListResDto;

import javax.transaction.Transactional;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/playLists")
public class PlayListController {
    private final PlayListService playListService;

    // 플레이 리스트를 생성시키기 위해서
    @PutMapping("/createPlayList")
    public ResponseEntity<CreatePlayListResDto> createPlayList(@RequestBody CreatePlayListReqDto createPlayListReqDto, @RequestHeader("User-Id") Long userId) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", createPlayListReqDto.getClass().getSimpleName(), createPlayListReqDto.toString()));
        
            CreatePlayListResDto createPlayListResDto = this.playListService.createPlayList(createPlayListReqDto, userId);
        
            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", createPlayListResDto.getClass().getSimpleName(), createPlayListResDto.toString()));
            return ResponseEntity.ok(createPlayListResDto);

        } catch(Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", createPlayListReqDto.getClass().getSimpleName(), createPlayListReqDto.toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 플레이 리스트를 수정시키기 위해서
    @PutMapping("/updatePlayList")
    public ResponseEntity<UpdatePlayListResDto> updatePlayList(@RequestBody UpdatePlayListReqDto updatePlayListReqDto) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", updatePlayListReqDto.getClass().getSimpleName(), updatePlayListReqDto.toString()));
        
            UpdatePlayListResDto updatePlayListResDto = this.playListService.updatePlayList(updatePlayListReqDto);
        
            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", updatePlayListResDto.getClass().getSimpleName(), updatePlayListResDto.toString()));
            return ResponseEntity.ok(updatePlayListResDto);

        } catch(Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", updatePlayListReqDto.getClass().getSimpleName(), updatePlayListReqDto.toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
