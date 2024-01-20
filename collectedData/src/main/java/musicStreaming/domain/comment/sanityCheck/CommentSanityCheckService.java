package musicStreaming.domain.comment.sanityCheck;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import musicStreaming.domain.comment.event.CommentCreated;
import musicStreaming.domain.comment.event.CommentDeleted;
import musicStreaming.domain.comment.event.CommentUpdated;
import musicStreaming.domain.comment.sanityCheck.reqDtos.MockCommentCreatedReqDto;
import musicStreaming.domain.comment.sanityCheck.reqDtos.MockCommentDeletedReqDto;
import musicStreaming.domain.comment.sanityCheck.reqDtos.MockCommentUpdatedReqDto;

@Service
@RequiredArgsConstructor
public class CommentSanityCheckService {

    // Policy 테스트용으로 CommentCreated 이벤트를 강제로 발생시키기 위해서
    public void mockCommentCreated(MockCommentCreatedReqDto mockData) {
        (new CommentCreated(mockData)).publish();
    }

    // Policy 테스트용으로 CommentUpdated 이벤트를 강제로 발생시키기 위해서
    public void mockCommentUpdated(MockCommentUpdatedReqDto mockData) {
        (new CommentUpdated(mockData)).publish();
    }

    // Policy 테스트용으로 CommentDeleted 이벤트를 강제로 발생시키기 위해서
    public void mockCommentDeleted(MockCommentDeletedReqDto mockData) {
        (new CommentDeleted(mockData)).publish();
    }

}
