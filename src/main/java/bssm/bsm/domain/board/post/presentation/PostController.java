package bssm.bsm.domain.board.post.presentation;

import bssm.bsm.domain.board.post.domain.Post;
import bssm.bsm.domain.board.post.presentation.dto.req.DeletePostReq;
import bssm.bsm.domain.board.post.presentation.dto.req.FindPostListReq;
import bssm.bsm.domain.board.post.presentation.dto.req.FindPostReq;
import bssm.bsm.domain.board.post.presentation.dto.req.FindRecentPostListReq;
import bssm.bsm.domain.board.post.presentation.dto.req.UpdatePostReq;
import bssm.bsm.domain.board.post.presentation.dto.req.WritePostReq;
import bssm.bsm.domain.board.post.presentation.dto.res.PostListRes;
import bssm.bsm.domain.board.post.presentation.dto.res.DetailPostRes;
import bssm.bsm.domain.board.post.service.PostService;
import bssm.bsm.global.auth.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("post")
@RequiredArgsConstructor
public class PostController {

    private final CurrentUser currentUser;
    private final PostService postService;

    @GetMapping("/{boardId}")
    public PostListRes findPostList(
            @PathVariable String boardId,
            @RequestParam(value = "category", defaultValue = "all") String category,
            @RequestParam(value = "limit", defaultValue = "15") int limit,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return postService.findPostList(currentUser.getUserOrNull(), new FindPostListReq(boardId, category, pageable));
    }

    @GetMapping("/{boardId}/recent")
    public Page<Post> findRecentPostList(
            @PathVariable String boardId,
            @RequestParam(value = "category", defaultValue = "all") String category,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return postService.findRecentPostList(currentUser.getUserOrNull(), new FindRecentPostListReq(boardId, category, pageable));
    }

    @GetMapping("/{boardId}/{postId}")
    public DetailPostRes findPost(@PathVariable String boardId, @PathVariable int postId) {
        return postService.findPost(currentUser.getUserOrNull(), new FindPostReq(boardId, postId));
    }

    @PostMapping
    public long createPost(@Valid @RequestBody WritePostReq req) {
        return postService.createPost(currentUser.getUser(), req);
    }

    @PutMapping
    public void updatePost(@RequestBody UpdatePostReq req) {
        postService.updatePost(currentUser.getUser(), req);
    }

    @DeleteMapping("/{boardId}/{postId}")
    public void deletePost(@PathVariable String boardId, @PathVariable int postId) {
        postService.deletePost(currentUser.getUser(), new DeletePostReq(boardId, postId));
    }
}
