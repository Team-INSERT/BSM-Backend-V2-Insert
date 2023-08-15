package bssm.bsm.domain.board.post.service;

import bssm.bsm.domain.board.board.domain.Board;
import bssm.bsm.domain.board.post.domain.Post;
import bssm.bsm.domain.board.post.domain.PostPk;
import bssm.bsm.domain.board.post.exception.NoSuchPostException;
import bssm.bsm.domain.board.post.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostProvider {
    private final PostRepository postRepository;

    public Post findPost(Board board, long postId) {
        return postRepository.findByPkAndDelete(PostPk.create(postId, board), false)
                .orElseThrow(NoSuchPostException::new);
    }

    public Long getNewPostId(Board board) {
        return postRepository.countByBoard(board) + 1;
    }

    public Page<Post> findRecentPostList(Board board, String category, Pageable pageable) {
        return postRepository.findPostList(board, category, pageable);
    }

    public Page<Post> findPostListByCursor(Board board, String category, Pageable pageable) {
        return postRepository.findPostList(board, category, pageable);
    }

}
