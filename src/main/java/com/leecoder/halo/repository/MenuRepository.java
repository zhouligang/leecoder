package com.leecoder.halo.repository;

import org.springframework.lang.NonNull;
import com.leecoder.halo.model.entity.Menu;
import com.leecoder.halo.repository.base.BaseRepository;

/**
 * Menu repository.
 *
 * @author johnniang
 */
public interface MenuRepository extends BaseRepository<Menu, Integer> {

    boolean existsByName(@NonNull String name);

    boolean existsByIdNotAndName(@NonNull Integer id, @NonNull String name);
}
