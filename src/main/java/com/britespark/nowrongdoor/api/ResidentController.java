package com.britespark.nowrongdoor.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.britespark.nowrongdoor.dto.Resident;
import com.britespark.nowrongdoor.service.UnifiedService;

@RestController
@RequestMapping("/api/residents")
public class ResidentController {

	private final UnifiedService unifiedService;

    public ResidentController(UnifiedService unifiedService) {
        this.unifiedService = unifiedService;
    }

    @GetMapping
    public List<Resident> getResidents() {
    	return unifiedService.getAllResidents();

    }
    
}
