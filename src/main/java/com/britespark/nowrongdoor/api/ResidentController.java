package com.britespark.nowrongdoor.api;

import com.britespark.nowrongdoor.dto.UnifiedResponse;
import com.britespark.nowrongdoor.service.UnifiedService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/residents")
public class ResidentController {

    private final UnifiedService unifiedService;

    public ResidentController(UnifiedService unifiedService) {
        this.unifiedService = unifiedService;
    }

    @GetMapping("/unified")
    public UnifiedResponse getUnifiedData() {

        return unifiedService.getUnifiedData();
    }
}