package musicStreaming.domain.playList;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "playLists", path = "playLists")
public interface PlayListRepository
    extends PagingAndSortingRepository<PlayList, Long> {}
