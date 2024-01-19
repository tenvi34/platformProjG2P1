package musicStreaming._global.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import musicStreaming.domain.Music;

// REST 응답시에 추가적인 참조 URL들을 포함시키기 위해서
@Component
public class MessageHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Music>> {

    @Override
    public EntityModel<Music> process(EntityModel<Music> model) {
        return model;
    }
}
