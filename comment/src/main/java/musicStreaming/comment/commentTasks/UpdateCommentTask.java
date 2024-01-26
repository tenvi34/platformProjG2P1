package musicStreaming.comment.commentTasks;

import musicStreaming._global.event.CommentUpdated;
import musicStreaming._global.exceptions.CommentNotFoundException;

import musicStreaming.comment.reqDtos.UpdateCommentReqDto;
import musicStreaming.comment.resDtos.UpdateCommentResDto;

import musicStreaming.domain.Comment;
import musicStreaming.domain.CommentRepository;


public class UpdateCommentTask {
    // 코멘트를 업데이트하기 위해서
    public static UpdateCommentResDto updateCommentTask(UpdateCommentReqDto updateCommentReqDto, 
        CommentRepository commentRepository) {

      // [1] commentRepository에서 commentId와 일치하는 Comment를 가져옵니다.
      Comment commentToUpdate = commentRepository.findById(updateCommentReqDto.getCommentId())
              .orElseThrow(() -> new CommentNotFoundException());     

      // [2] Comment를 수정합니다.
      commentToUpdate.setContent(updateCommentReqDto.getContent());
      Comment savedComment = commentRepository.save(commentToUpdate);

      // [3] CommentUpdated 이벤트를 발생시킵니다.
      (new CommentUpdated(savedComment)).publishAfterCommit();

      return new UpdateCommentResDto(savedComment);
    }
}
