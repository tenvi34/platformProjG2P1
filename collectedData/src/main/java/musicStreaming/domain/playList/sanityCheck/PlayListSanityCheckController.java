package musicStreaming.domain.playList.sanityCheck;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

import musicStreaming.domain.playList.sanityCheck.reqDtos.MockPlayListCreatedReqDto;
import musicStreaming.domain.playList.sanityCheck.reqDtos.MockPlayListDeletedReqDto;
import musicStreaming.domain.playList.sanityCheck.reqDtos.MockPlayListMusicCountUpdatedReqDto;
import musicStreaming.domain.playList.sanityCheck.reqDtos.MockPlayListUpdatedReqDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/playLists/sanityCheck")
public class PlayListSanityCheckController {
    private final PlayListSanityCheckService playListSanityCheckService;

    // Policy 테스트용으로 PlayListCreated 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/PlayListCreated")
    public void mockPlayListCreated(@RequestBody MockPlayListCreatedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.playListSanityCheckService.mockPlayListCreated(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }

    // Policy 테스트용으로 PlayListUpdated 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/PlayListUpdated")
    public void mockPlayListUpdated(@RequestBody MockPlayListUpdatedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.playListSanityCheckService.mockPlayListUpdated(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }

    // Policy 테스트용으로 PlayListMusicCountUpdated 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/PlayListMusicCountUpdated")
    public void mockPlayListMusicCountUpdated(@RequestBody MockPlayListMusicCountUpdatedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.playListSanityCheckService.mockPlayListMusicCountUpdated(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }

    // Policy 테스트용으로 PlayListDeleted 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/PlayListDeleted")
    public void mockPlayListDeleted(@RequestBody MockPlayListDeletedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.playListSanityCheckService.mockPlayListDeleted(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }
}
