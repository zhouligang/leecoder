package com.leecoder.halo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import com.leecoder.halo.model.entity.Sheet;
import com.leecoder.halo.model.enums.PostStatus;
import com.leecoder.halo.repository.base.BasePostRepository;

import java.util.Optional;

/**
 * Sheet repository.
 *
 * @author johnniang
 * @date 3/22/19
 */
public interface SheetRepository extends BasePostRepository<Sheet> {

    @Override
    @Query("select sum(p.visits) from Sheet p")
    Long countVisit();

    @Override
    @Query("select sum(p.likes) from Sheet p")
    Long countLike();

    @NonNull
    Optional<Sheet> getByUrlAndStatus(@NonNull String url, @NonNull PostStatus status);
}
