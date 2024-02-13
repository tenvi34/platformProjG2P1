package musicStreaming.domain.comment;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;


@RepositoryRestResource(
    collectionResourceRel = "comments",
    path = "comments"
)
public interface CommentRepository
    extends PagingAndSortingRepository<Comment, Long> {
    Optional<Comment> findByCommentId(Long commentId);
    List<Comment> findByMusicId(Long musicId);
}
