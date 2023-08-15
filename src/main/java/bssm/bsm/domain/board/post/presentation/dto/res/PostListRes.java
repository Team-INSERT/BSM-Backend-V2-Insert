package bssm.bsm.domain.board.post.presentation.dto.res;

import bssm.bsm.domain.board.post.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostListRes {

    List<PostRes> postList;

    public static PostListRes create(Page<Post> postList) {
        List<PostRes> postResList = postList.stream()
                .map(PostRes::create)
                .toList();

        PostListRes postListRes = new PostListRes();
        postListRes.postList = postResList;
        return postListRes;
    }
}
