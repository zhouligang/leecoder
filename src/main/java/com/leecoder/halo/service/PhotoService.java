package com.leecoder.halo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import com.leecoder.halo.model.dto.PhotoDTO;
import com.leecoder.halo.model.entity.Photo;
import com.leecoder.halo.model.params.PhotoParam;
import com.leecoder.halo.model.params.PhotoQuery;
import com.leecoder.halo.model.vo.PhotoTeamVO;
import com.leecoder.halo.service.base.CrudService;

import java.util.List;

/**
 * Photo service.
 *
 * @author johnniang
 */
public interface PhotoService extends CrudService<Photo, Integer> {

    /**
     * List photo dtos.
     *
     * @param sort sort
     * @return all photos
     */
    List<PhotoDTO> listDtos(@NonNull Sort sort);

    /**
     * Lists photo team vos.
     *
     * @param sort must not be null
     * @return a list of photo team vo
     */
    List<PhotoTeamVO> listTeamVos(@NonNull Sort sort);

    /**
     * List photos by team.
     *
     * @param team team
     * @param sort sort
     * @return list of photos
     */
    List<PhotoDTO> listByTeam(@NonNull String team, Sort sort);

    /**
     * Pages photo output dtos.
     *
     * @param pageable   page info must not be null
     * @param photoQuery photoQuery
     * @return a page of photo output dto
     */
    @NonNull
    Page<PhotoDTO> pageDtosBy(@NonNull Pageable pageable, PhotoQuery photoQuery);

    /**
     * Creates photo by photo param.
     *
     * @param photoParam must not be null
     * @return create photo
     */
    @NonNull
    Photo createBy(@NonNull PhotoParam photoParam);
}
