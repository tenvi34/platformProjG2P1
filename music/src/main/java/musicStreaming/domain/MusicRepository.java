package musicStreaming.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "musics", path = "musics")
public interface MusicRepository
    extends PagingAndSortingRepository<Music, Long> {}
