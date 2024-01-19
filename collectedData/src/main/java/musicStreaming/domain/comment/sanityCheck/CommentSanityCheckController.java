package musicStreaming.domain.comment.sanityCheck;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments/sanityCheck")
public class CommentSanityCheckController {
    // private final CommentSanityCheckService commentSanityCheckService;
}
