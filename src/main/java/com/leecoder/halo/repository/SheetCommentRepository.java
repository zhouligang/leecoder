package com.leecoder.halo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import com.leecoder.halo.model.entity.SheetComment;
import com.leecoder.halo.model.projection.CommentChildrenCountProjection;
import com.leecoder.halo.model.projection.CommentCountProjection;
import com.leecoder.halo.repository.base.BaseCommentRepository;

import java.util.List;

/**
 * Sheet comment repository.
 *
 * @author johnniang
 * @date 19-4-24
 */
public interface SheetCommentRepository extends BaseCommentRepository<SheetComment> {

    @Query("select new com.leecoder.halo.model.projection.CommentCountProjection(count(comment.id), comment.postId) " +
            "from SheetComment comment " +
            "where comment.postId in ?1 group by comment.postId")
    @NonNull
    @Override
    List<CommentCountProjection> countByPostIds(@NonNull Iterable<Integer> postIds);

    @Query("select new com.leecoder.halo.model.projection.CommentChildrenCountProjection(count(comment.id), comment.parentId) " +
            "from SheetComment comment " +
            "where comment.parentId in ?1 " +
            "group by comment.parentId")
    @NonNull
    List<CommentChildrenCountProjection> findDirectChildrenCount(@NonNull Iterable<Long> commentIds);
}
