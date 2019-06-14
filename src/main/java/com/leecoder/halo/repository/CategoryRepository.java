package com.leecoder.halo.repository;

import org.springframework.lang.NonNull;
import com.leecoder.halo.model.entity.Category;
import com.leecoder.halo.repository.base.BaseRepository;

import java.util.Optional;

/**
 * Category repository.
 *
 * @author johnniang
 */
public interface CategoryRepository extends BaseRepository<Category, Integer> {

    /**
     * Counts by category name.
     *
     * @param name category name must not be blank
     * @return the count
     */
    long countByName(@NonNull String name);

    /**
     * Counts by category id.
     *
     * @param id category id must not be null
     * @return the count
     */
    long countById(@NonNull Integer id);

    /**
     * Get category by slug name
     *
     * @param slugName slug name
     * @return Optional of Category
     */
    Optional<Category> getBySlugName(@NonNull String slugName);

    /**
     * Get category by name.
     *
     * @param name name
     * @return Optional of Category
     */
    Optional<Category> getByName(@NonNull String name);
}
