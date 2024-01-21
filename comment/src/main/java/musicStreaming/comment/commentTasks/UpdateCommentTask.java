package musicStreaming.comment.commentTasks;

import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

import musicStreaming.comment.reqDtos.UpdateCommentReqDto;
import musicStreaming.comment.resDtos.UpdateCommentResDto;

import musicStreaming.domain.Comment;
import musicStreaming.domain.CommentRepository;


public class UpdateCommentTask {
    // 코멘트를 업데이트하기 위해서
    public static UpdateCommentResDto updateCommentTask(UpdateCommentReqDto updateCommentReqDto, 
        CommentRepository commentRepository) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: UpdateComment");

        // [1] commentRepository에서 commentId와 일치하는 Comment를 가져옵니다.
        // [2] Comment를 수정합니다.
        // [3] CommentUpdated 이벤트를 발생시킵니다.

        return new UpdateCommentResDto(new Comment());
    }
}
