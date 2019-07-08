package com.leecoder.halo.controller.admin.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import com.leecoder.halo.model.dto.OptionDTO;
import com.leecoder.halo.model.params.OptionParam;
import com.leecoder.halo.service.OptionService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Option Controller.
 *
 * @author johnniang
 * @date 3/20/19
 */
@RestController
@RequestMapping("/api/admin/options")
public class OptionController {

    private final OptionService optionService;

    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @GetMapping
    public List<OptionDTO> listAll() {
        return optionService.listDtos();
    }

    @PostMapping("saving")
    public void saveOptions(@Valid @RequestBody List<OptionParam> optionParams) {
        optionService.save(optionParams);
    }

    @PostMapping("map_view")
    @ApiOperation("Lists all options with map view")
    public Map<String, Object> listAllWithMapView(@RequestBody(required = false) List<String> keys) {
        if (CollectionUtils.isEmpty(keys)) {
            return optionService.listOptions();
        }

        return optionService.listOptions(keys);
    }

    @PostMapping("map_view/saving")
    @ApiOperation("Saves options by option map")
    public void saveOptionsWithMapView(@RequestBody Map<String, String> optionMap) {
        optionService.save(optionMap);
    }

}
