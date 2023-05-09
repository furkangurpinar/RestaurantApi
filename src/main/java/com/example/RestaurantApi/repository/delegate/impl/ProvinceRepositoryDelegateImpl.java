package com.example.RestaurantApi.repository.delegate.impl;

import com.example.RestaurantApi.exception.Province.ProvinceNotFoundException;
import com.example.RestaurantApi.model.converter.ProvinceConverter;
import com.example.RestaurantApi.model.dto.ProvinceDto;
import com.example.RestaurantApi.model.entity.Province;
import com.example.RestaurantApi.repository.ProvinceRepository;
import com.example.RestaurantApi.repository.delegate.ProvinceRepositoryDelegate;
import com.example.RestaurantApi.request.ProvinceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProvinceRepositoryDelegateImpl implements ProvinceRepositoryDelegate {

    private final ProvinceRepository provinceRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ProvinceDto> getProvinces() {
        return provinceRepository.findAll()
                .stream()
                .map(ProvinceConverter::convert)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public ProvinceDto getProvince(int provinceId) {
        Optional<Province> response = provinceRepository.findById(provinceId);
        if (response.isEmpty()) {
            throw new ProvinceNotFoundException("Province ID Not Found - " + provinceId);
        }
        return ProvinceConverter.convert(response.get());
    }

    @Transactional
    @Override
    public ProvinceDto createProvince(ProvinceRequest request) {
        Province province = ProvinceConverter.convert(request.getProvinceDto());
        province.setCreateDate(LocalDateTime.now());
        provinceRepository.save(province);
        return ProvinceConverter.convert(province);
    }

    @Transactional
    @Override
    public void updateProvince(ProvinceDto provinceDto) {

        Province province = ProvinceConverter.convert(provinceDto);
        province.setUpdateDate(LocalDateTime.now());
        provinceRepository.save(province);
    }

    @Transactional
    @Override
    public void deleteProvince(int provinceId) {
        Optional<Province> response = provinceRepository.findById(provinceId);
        if (response.isEmpty()) {
            throw new ProvinceNotFoundException("Province ID Not Found - " + provinceId);
        }
        provinceRepository.delete(response.get());
    }
}
