package musicStreaming.comment.commentTasks;

import musicStreaming._global.event.CommentCreated;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

import musicStreaming.comment.reqDtos.CreateCommentReqDto;
import musicStreaming.comment.resDtos.CreateCommentResDto;
import musicStreaming.domain.Comment;
import musicStreaming.domain.CommentRepository;


public class CreateCommentTask {
    // 코멘트를 생성하기 위해서
    public static CreateCommentResDto createCommentTask(CreateCommentReqDto createCommentReqDto, Long userId,
        CommentRepository commentRepository) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: createComment");

        // [1] commentRepository를 이용해서 새로운 Comment 데이터를 저장합니다.
        Comment comment = new Comment();
        CommentCreated commentCreated = new CommentCreated(comment);

        comment.setId(commentCreated.getId());
        comment.setCreaterId(commentCreated.getCreaterId());
        comment.setMusicId(commentCreated.getMusicId());
        comment.setContent(commentCreated.getContent());
        comment.setCreatedDate(commentCreated.getCreatedDate());
        comment.setUpdatedDate(commentCreated.getUpdatedDate());

        commentRepository.save(comment);        
        
        // [2] CommentCreated 이벤트를 발생시킵니다.
        commentCreated.publishAfterCommit();

        return new CreateCommentResDto(new Comment());
    }
}
