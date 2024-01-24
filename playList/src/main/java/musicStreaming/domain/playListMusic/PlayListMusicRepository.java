package musicStreaming.domain.playListMusic;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "playListMusics", path = "playListMusics")
public interface PlayListMusicRepository
    extends PagingAndSortingRepository<PlayListMusic, Long> {
    List<PlayListMusic> findAllByMusicId(Long musicId);
}
