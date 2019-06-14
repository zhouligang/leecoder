package com.leecoder.halo.repository;

import org.springframework.lang.NonNull;
import com.leecoder.halo.model.entity.Tag;
import com.leecoder.halo.repository.base.BaseRepository;

import java.util.Optional;

/**
 * Tag repository.
 *
 * @author johnniang
 */
public interface TagRepository extends BaseRepository<Tag, Integer> {

    /**
     * Count by name or slug name.
     *
     * @param name     tag name must not be null
     * @param slugName tag slug name must not be null
     * @return tag count
     */
    long countByNameOrSlugName(@NonNull String name, @NonNull String slugName);

    /**
     * Get tag by slug name
     *
     * @param slugName slug name
     * @return Tag
     */
    Optional<Tag> getBySlugName(@NonNull String slugName);

    /**
     * Get tag by name
     * @param name name
     * @return Tag
     */
    Optional<Tag> getByName(@NonNull String name);
}
