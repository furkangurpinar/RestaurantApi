package com.example.RestaurantApi.controller;

import com.example.RestaurantApi.model.dto.RequirementDto;
import com.example.RestaurantApi.request.RequirementRequest;
import com.example.RestaurantApi.service.RequirementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/requirements")
public class RequirementController {

    private final RequirementService requirementService;

    @GetMapping
    public ResponseEntity<List<RequirementDto>> getRequirements() {
        return new ResponseEntity<>(requirementService.getRequirements(), HttpStatus.OK);
    }

    @GetMapping("/{requirementId}")
    public ResponseEntity<RequirementDto> getRequirement(@PathVariable int requirementId) {
        return new ResponseEntity<>(requirementService.getRequirement(requirementId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RequirementDto> createRequirement(@RequestBody RequirementRequest request) {
        return new ResponseEntity<>(requirementService.createRequirement(request), HttpStatus.CREATED);
    }

    @PutMapping("/{requirementId}")
    public ResponseEntity<HttpStatus> updateRequirement(@RequestBody RequirementRequest requirementRequest, @PathVariable int requirementId) {
        requirementService.updateRequirement(requirementId, requirementRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{requirementId}")
    public ResponseEntity<HttpStatus> deleteRequirement(@PathVariable int requirementId) {
        requirementService.deleteRequirement(requirementId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
