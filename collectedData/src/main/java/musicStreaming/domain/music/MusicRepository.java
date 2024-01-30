package musicStreaming.domain.music;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "musics",
    path = "musics"
)
public interface MusicRepository
    extends PagingAndSortingRepository<Music, Long> {
    Optional<Music> findByMusicId(Long musicId);
    List<Music> findByTitleContainingIgnoreCase(String title);
    List<Music> findByCreaterContainingIgnoreCase(String creater);
}
