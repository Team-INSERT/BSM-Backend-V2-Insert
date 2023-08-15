package bssm.bsm.domain.board.post.domain.repository;

import bssm.bsm.domain.board.board.domain.Board;
import bssm.bsm.domain.board.category.domain.PostCategory;
import bssm.bsm.domain.board.category.service.CategoryProvider;
import bssm.bsm.domain.board.post.domain.Post;
import bssm.bsm.global.utils.SortToOrderSp;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static bssm.bsm.domain.board.post.domain.QPost.post;
import static bssm.bsm.domain.user.domain.QUser.user;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final CategoryProvider categoryProvider;

    @Override
    public Page<Post> findPostList(Board board, String category, Pageable pageable) {

        List<Post> result = jpaQueryFactory.selectFrom(post)
                 .join(post.writer, user)
                 .where(
                         post.delete.isFalse(),
                         post.board.eq(board),
                         categoryEq(board,category)
                 )
                 .offset(pageable.getOffset())
                 .limit(pageable.getPageSize())
                 .orderBy(SortToOrderSp.getOrderSpecifier(pageable.getSort()).stream().toArray(OrderSpecifier[]::new))
                 .fetch();

        JPAQuery<Long> count = jpaQueryFactory.select(post.count())
                .from(post)
                .where(
                        post.delete.isFalse(),
                        post.board.eq(board),
                        categoryEq(board,category)
                );

        return PageableExecutionUtils.getPage(result, pageable, count::fetchOne);

    }

    private BooleanExpression categoryEq(Board board, String category) {
        if (category.equals("all")) {
            return null;
        }
        PostCategory postCategory = categoryProvider.findCategory(category, board);
        if (postCategory == null) {
            return post.categoryId.isNull();
        }
        return post.category.eq(postCategory);
    }

    private BooleanExpression postIdLt(Long startPostId) {
        if (startPostId == null) {
            return null;
        }
        return post.pk.id.lt(startPostId);
    }

}
