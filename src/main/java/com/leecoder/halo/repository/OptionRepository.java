package com.leecoder.halo.repository;

import com.leecoder.halo.model.entity.Option;
import com.leecoder.halo.repository.base.BaseRepository;

import java.util.Optional;

/**
 * Option repository.
 *
 * @author johnniang
 */
public interface OptionRepository extends BaseRepository<Option, Integer> {

    /**
     * Query option by key
     *
     * @param key key
     * @return Option
     */
    Optional<Option> findByKey(String key);

    /**
     * Delete option by key
     *
     * @param key key
     */
    void deleteByKey(String key);
}
