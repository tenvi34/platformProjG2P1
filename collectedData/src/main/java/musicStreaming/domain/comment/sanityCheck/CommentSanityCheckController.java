package musicStreaming.domain.comment.sanityCheck;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

import musicStreaming.domain.comment.sanityCheck.reqDtos.MockCommentCreatedReqDto;
import musicStreaming.domain.comment.sanityCheck.reqDtos.MockCommentDeletedReqDto;
import musicStreaming.domain.comment.sanityCheck.reqDtos.MockCommentUpdatedReqDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments/sanityCheck")
public class CommentSanityCheckController {
    private final CommentSanityCheckService commentSanityCheckService;

    // Policy 테스트용으로 CommentCreated 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/CommentCreated")
    public void mockCommentCreated(@RequestBody MockCommentCreatedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.commentSanityCheckService.mockCommentCreated(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }

    // Policy 테스트용으로 CommentUpdated 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/CommentUpdated")
    public void mockCommentUpdated(@RequestBody MockCommentUpdatedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.commentSanityCheckService.mockCommentUpdated(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }

    // Policy 테스트용으로 CommentDeleted 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/CommentDeleted")
    public void mockCommentDeleted(@RequestBody MockCommentDeletedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.commentSanityCheckService.mockCommentDeleted(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }
}
