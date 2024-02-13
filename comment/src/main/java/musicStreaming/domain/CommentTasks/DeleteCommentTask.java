package musicStreaming.domain.CommentTasks;

import musicStreaming._global.event.MusicDeleted;

import musicStreaming.domain.CommentRepository;

public class DeleteCommentTask {
    // 음악 정보가 삭제되었을 경우, 연관된 코멘트 정보들을 삭제시키기 위해서
    public static void deleteCommentTask(MusicDeleted musicDeleted, CommentRepository commentRepository) {

        commentRepository.findAllByMusicId(musicDeleted.getId()).forEach(commendToDelete->{

            // [1] commentRepository를 이용해서 주어진 musicId를 가진 모든 Comment를 삭제합니다.
            commentRepository.delete(commendToDelete);

            // [2] 각각의 삭제된 Comment마다 CommentDeleted 이벤트를 발생시킵니다.
            // Comment@PostRemove에서 실행됨
            
        });

    }      
}