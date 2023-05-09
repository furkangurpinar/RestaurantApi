package com.example.RestaurantApi.controller;

import com.example.RestaurantApi.model.dto.DistrictDto;
import com.example.RestaurantApi.request.DistrictRequest;
import com.example.RestaurantApi.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/districts")
public class DistrictController {

    private final DistrictService districtService;

    @GetMapping
    public ResponseEntity<List<DistrictDto>> getDistricts() {
        return new ResponseEntity<>(districtService.getDistricts(), HttpStatus.OK);
    }

    @GetMapping("/{districtId}")
    public ResponseEntity<DistrictDto> getDistrict(@PathVariable int districtId) {
        return new ResponseEntity<>(districtService.getDistrict(districtId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DistrictDto> createDistrict(@RequestBody DistrictRequest request) {
        return new ResponseEntity<>(districtService.createDistrict(request), HttpStatus.CREATED);
    }

    @PutMapping("/{districtId}")
    public ResponseEntity<HttpStatus> updateDistrict(@RequestBody DistrictRequest districtRequest, @PathVariable int districtId) {
        districtService.updateDistrict(districtId, districtRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{districtId}")
    public ResponseEntity<HttpStatus> deleteDistrict(@PathVariable int districtId) {
        districtService.deleteDistrict(districtId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
