package bssm.bsm.domain.board.post.domain.repository;

import bssm.bsm.domain.board.board.domain.Board;
import bssm.bsm.domain.board.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PostRepositoryCustom {

    Page<Post> findPostList(Board board, String category, Pageable pageable);

}