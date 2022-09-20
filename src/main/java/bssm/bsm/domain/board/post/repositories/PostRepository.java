package bssm.bsm.domain.board.post.repositories;

import bssm.bsm.domain.board.post.entities.Board;
import bssm.bsm.domain.board.post.entities.Post;
import bssm.bsm.domain.board.post.entities.PostCategory;
import bssm.bsm.domain.board.post.entities.PostPk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, PostPk> {

    Optional<Post> findByPkAndDelete(PostPk pk, boolean delete);

    // 전체 게시글
    Page<Post> findByPkBoardAndDeleteOrderByPkIdDesc(Board board, boolean delete, Pageable pageable);
    List<Post> findByPkLessThanAndDeleteOrderByPkIdDesc(PostPk pk, boolean delete, Pageable pageable);

    // 카테고리 있는 게시글
    Page<Post> findByCategoryAndDeleteOrderByPkIdDesc(PostCategory postCategory, boolean delete, Pageable pageable);
    List<Post> findByPkLessThanAndCategoryAndDeleteOrderByPkIdDesc(PostPk pk, PostCategory postCategory, boolean delete, Pageable pageable);

    // 카테고리 없는 게시글
    Page<Post> findByPkBoardAndCategoryIdAndDeleteOrderByPkIdDesc(Board board, String categoryId, boolean delete, Pageable pageable);
    List<Post> findByPkLessThanAndCategoryIdAndDeleteOrderByPkIdDesc(PostPk pk, String categoryId, boolean delete, Pageable pageable);

    @Query(value = "SELECT COUNT(p) FROM Post p WHERE p.pk.board.id = :boardId")
    long countByBoardId(@Param("boardId") String boardId);
}