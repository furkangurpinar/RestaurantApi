package com.example.RestaurantApi.controller;

import com.example.RestaurantApi.model.dto.ProvinceDto;
import com.example.RestaurantApi.request.ProvinceRequest;
import com.example.RestaurantApi.service.ProvinceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/provinces")
public class ProvinceController {

    private final ProvinceService provinceService;

    @GetMapping
    public ResponseEntity<List<ProvinceDto>> getProvinces() {
        return new ResponseEntity<>(provinceService.getProvinces(), HttpStatus.OK);
    }

    @GetMapping("/{provinceId}")
    public ResponseEntity<ProvinceDto> getProvince(@PathVariable int provinceId) {
        return new ResponseEntity<>(provinceService.getProvince(provinceId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProvinceDto> createProvince(@RequestBody ProvinceRequest request) {
        return new ResponseEntity<>(provinceService.createProvince(request), HttpStatus.CREATED);
    }

    @PutMapping("/{provinceId}")
    public ResponseEntity<HttpStatus> updateProvince(@RequestBody ProvinceRequest provinceRequest, @PathVariable int provinceId) {
        provinceService.updateProvince(provinceId, provinceRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{provinceId}")
    public ResponseEntity<HttpStatus> deleteProvince(@PathVariable int provinceId) {
        provinceService.deleteProvince(provinceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
