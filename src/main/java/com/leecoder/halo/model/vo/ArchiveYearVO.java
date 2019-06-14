package com.leecoder.halo.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import com.leecoder.halo.model.dto.post.BasePostMinimalDTO;

import java.util.Comparator;
import java.util.List;

/**
 * Year archive vo.
 *
 * @author johnniang
 * @date 4/2/19
 */
@Data
@ToString
@EqualsAndHashCode
public class ArchiveYearVO {

    private Integer year;

    private List<BasePostMinimalDTO> posts;

    public static class ArchiveComparator implements Comparator<ArchiveYearVO> {

        @Override
        public int compare(ArchiveYearVO left, ArchiveYearVO right) {
            return right.getYear() - left.getYear();
        }
    }
}
