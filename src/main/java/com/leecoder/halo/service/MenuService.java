package com.leecoder.halo.service;

import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import com.leecoder.halo.model.dto.MenuDTO;
import com.leecoder.halo.model.entity.Menu;
import com.leecoder.halo.model.params.MenuParam;
import com.leecoder.halo.model.vo.MenuVO;
import com.leecoder.halo.service.base.CrudService;

import java.util.List;

/**
 * Menu service.
 *
 * @author johnniang
 * @author ryanwang
 */
public interface MenuService extends CrudService<Menu, Integer> {

    /**
     * Lists all menu dtos.
     *
     * @param sort must not be null
     * @return a list of menu output dto
     */
    @NonNull
    List<MenuDTO> listDtos(@NonNull Sort sort);

    /**
     * Creates a menu.
     *
     * @param menuParam must not be null
     * @return created menu
     */
    @NonNull
    Menu createBy(@NonNull MenuParam menuParam);

    /**
     * Lists as menu tree.
     *
     * @param sort sort info must not be null
     * @return a menu tree
     */
    List<MenuVO> listAsTree(Sort sort);
}
