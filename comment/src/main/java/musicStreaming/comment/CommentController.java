package musicStreaming.comment;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    // private final CommentService commentService;
}
