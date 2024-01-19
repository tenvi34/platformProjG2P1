package musicStreaming.domain.playListMusic;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "playListMusics", path = "playListMusics")
public interface PlayListMusicRepository
    extends PagingAndSortingRepository<PlayListMusic, Long> {}
