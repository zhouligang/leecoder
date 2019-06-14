package com.leecoder.halo.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.leecoder.halo.model.entity.Journal;
import com.leecoder.halo.repository.base.BaseRepository;

/**
 * Journal repository.
 *
 * @author johnniang
 * @date 3/22/19
 */
public interface JournalRepository extends BaseRepository<Journal, Integer>, JpaSpecificationExecutor<Journal> {

}
