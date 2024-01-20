package musicStreaming.domain.music.sanityCheck;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

import musicStreaming.domain.music.sanityCheck.reqDtos.MockMusicCreatedReqDto;
import musicStreaming.domain.music.sanityCheck.reqDtos.MockMusicDeletedByFailReqDto;
import musicStreaming.domain.music.sanityCheck.reqDtos.MockMusicDeletedReqDto;
import musicStreaming.domain.music.sanityCheck.reqDtos.MockMusicFileDeleteRequestedReqDto;
import musicStreaming.domain.music.sanityCheck.reqDtos.MockMusicFileUpdateRequestedReqDto;
import musicStreaming.domain.music.sanityCheck.reqDtos.MockMusicFileUploadRequestedReqDto;
import musicStreaming.domain.music.sanityCheck.reqDtos.MockMusicInfoUpdatedReqDto;
import musicStreaming.domain.music.sanityCheck.reqDtos.MockMusicLikedReqDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/musics/sanityCheck")
public class MusicSanityCheckController {
    private final MusicSanityCheckService musicSanityCheckService;

    // Policy 테스트용으로 MusicFileUploadRequested 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/MusicFileUploadRequested")
    public void mockMusicFileUploadRequested(@RequestBody MockMusicFileUploadRequestedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.musicSanityCheckService.mockMusicFileUploadRequested(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }

    // Policy 테스트용으로 MusicCreated 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/MusicCreated")
    public void mockMusicCreated(@RequestBody MockMusicCreatedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.musicSanityCheckService.mockMusicCreated(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }

    // Policy 테스트용으로 MusicDeletedByFail 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/MusicDeletedByFail")
    public void mockMusicDeletedByFail(@RequestBody MockMusicDeletedByFailReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.musicSanityCheckService.mockMusicDeletedByFail(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }


    // Policy 테스트용으로 MusicInfoUpdated 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/MusicInfoUpdated")
    public void mockMusicInfoUpdated(@RequestBody MockMusicInfoUpdatedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.musicSanityCheckService.mockMusicInfoUpdated(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }

    // Policy 테스트용으로 MusicFileUpdateRequested 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/MusicFileUpdateRequested")
    public void mockMusicFileUpdateRequested(@RequestBody MockMusicFileUpdateRequestedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.musicSanityCheckService.mockMusicFileUpdateRequested(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }


    // Policy 테스트용으로 MusicFileDeleteRequested 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/MusicFileDeleteRequested")
    public void mockMusicFileDeleteRequested(@RequestBody MockMusicFileDeleteRequestedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.musicSanityCheckService.mockMusicFileDeleteRequested(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }

    // Policy 테스트용으로 MusicDeleted 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/MusicDeleted")
    public void mockMusicDeleted(@RequestBody MockMusicDeletedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.musicSanityCheckService.mockMusicDeleted(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }


    // Policy 테스트용으로 MusicLiked 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/MusicLiked")
    public void mockMusicLiked(@RequestBody MockMusicLikedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.musicSanityCheckService.mockMusicLiked(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }
}
