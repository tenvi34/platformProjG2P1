package musicStreaming.domain.playListMusic.sanityCheck;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

import musicStreaming.domain.playListMusic.sanityCheck.reqDtos.MockPlayListMusicCreatedReqDto;
import musicStreaming.domain.playListMusic.sanityCheck.reqDtos.MockPlayListMusicDeletedReqDto;
import musicStreaming.domain.playListMusic.sanityCheck.reqDtos.MockPlayListMusicUpdatedReqDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/playListMusics/sanityCheck")
public class PlayListMusicSanityCheckController {
    private final PlayListMusicSanityCheckService playListMusicSanityCheckService;

    // Policy 테스트용으로 PlayListMusicCreated 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/PlayListMusicCreated")
    public void mockPlayListMusicCreated(@RequestBody MockPlayListMusicCreatedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.playListMusicSanityCheckService.mockPlayListMusicCreated(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }

    // Policy 테스트용으로 PlayListMusicUpdated 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/PlayListMusicUpdated")
    public void mockPlayListMusicUpdated(@RequestBody MockPlayListMusicUpdatedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.playListMusicSanityCheckService.mockPlayListMusicUpdated(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }

    // Policy 테스트용으로 PlayListMusicDeleted 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/PlayListMusicDeleted")
    public void mockPlayListMusicDeleted(@RequestBody MockPlayListMusicDeletedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.playListMusicSanityCheckService.mockPlayListMusicDeleted(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }
}
