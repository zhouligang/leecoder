package com.leecoder.halo.service.impl;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import com.leecoder.halo.exception.AlreadyExistsException;
import com.leecoder.halo.model.dto.LinkDTO;
import com.leecoder.halo.model.entity.Link;
import com.leecoder.halo.model.params.LinkParam;
import com.leecoder.halo.model.vo.LinkTeamVO;
import com.leecoder.halo.repository.LinkRepository;
import com.leecoder.halo.service.LinkService;
import com.leecoder.halo.service.base.AbstractCrudService;
import com.leecoder.halo.utils.ServiceUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LinkService implementation class
 *
 * @author ryanwang
 * @date : 2019-03-14
 */
@Service
public class LinkServiceImpl extends AbstractCrudService<Link, Integer> implements LinkService {

    private final LinkRepository linkRepository;

    public LinkServiceImpl(LinkRepository linkRepository) {
        super(linkRepository);
        this.linkRepository = linkRepository;
    }

    /**
     * List link dtos.
     *
     * @param sort sort
     * @return all links
     */
    @Override
    public List<LinkDTO> listDtos(Sort sort) {
        Assert.notNull(sort, "Sort info must not be null");

        return convertTo(listAll(sort));
    }

    @Override
    public List<LinkTeamVO> listTeamVos(Sort sort) {
        Assert.notNull(sort, "Sort info must not be null");

        // List all links
        List<LinkDTO> links = listDtos(sort);

        // Get teams
        Set<String> teams = ServiceUtils.fetchProperty(links, LinkDTO::getTeam);

        // Convert to team link list map (Key: team, value: link list)
        Map<String, List<LinkDTO>> teamLinkListMap = ServiceUtils.convertToListMap(teams, links, LinkDTO::getTeam);

        List<LinkTeamVO> result = new LinkedList<>();

        // Wrap link team vo list
        teamLinkListMap.forEach((team, linkList) -> {
            // Build link team vo
            LinkTeamVO linkTeamVO = new LinkTeamVO();
            linkTeamVO.setTeam(team);
            linkTeamVO.setLinks(linkList);

            // Add it to result
            result.add(linkTeamVO);
        });

        return result;
    }

    @Override
    public Link createBy(LinkParam linkParam) {
        Assert.notNull(linkParam, "Link param must not be null");

        // Check the name
        boolean exist = existByName(linkParam.getName());

        if (exist) {
            throw new AlreadyExistsException("友情链接 " + linkParam.getName() + " 已存在").setErrorData(linkParam.getName());
        }

        return create(linkParam.convertTo());
    }

    @Override
    public boolean existByName(String name) {
        Assert.hasText(name, "Link name must not be blank");
        Link link = new Link();
        link.setName(name);

        return linkRepository.exists(Example.of(link));
    }

    @NonNull
    private List<LinkDTO> convertTo(@Nullable List<Link> links) {
        if (CollectionUtils.isEmpty(links)) {
            return Collections.emptyList();
        }

        return links.stream().map(link -> (LinkDTO) new LinkDTO().convertFrom(link))
                .collect(Collectors.toList());
    }
}
