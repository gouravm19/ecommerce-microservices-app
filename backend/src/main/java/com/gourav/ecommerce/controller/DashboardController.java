package com.gourav.ecommerce.controller;

import com.gourav.ecommerce.service.DashboardService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;
    public DashboardController(DashboardService dashboardService){this.dashboardService=dashboardService;}
    @PreAuthorize("hasRole('ADMIN')") @GetMapping("/stats") public Map<String, Object> stats(){ return dashboardService.stats(); }
}
