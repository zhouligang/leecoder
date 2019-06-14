package com.leecoder.halo.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.leecoder.halo.model.entity.Post;
import com.leecoder.halo.repository.base.BasePostRepository;


/**
 * Post repository.
 *
 * @author johnniang
 * @author ryanwang
 */
public interface PostRepository extends BasePostRepository<Post>, JpaSpecificationExecutor<Post> {

    @Override
    @Query("select sum(p.visits) from Post p")
    Long countVisit();

    @Override
    @Query("select sum(p.likes) from Post p")
    Long countLike();

}
