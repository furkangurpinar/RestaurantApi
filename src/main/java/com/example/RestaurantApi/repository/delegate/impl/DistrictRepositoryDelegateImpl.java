package com.example.RestaurantApi.repository.delegate.impl;

import com.example.RestaurantApi.exception.district.DistrictNotFoundException;
import com.example.RestaurantApi.exception.user.UserNotFoundException;
import com.example.RestaurantApi.model.converter.DistrictConverter;
import com.example.RestaurantApi.model.dto.DistrictDto;
import com.example.RestaurantApi.model.entity.District;
import com.example.RestaurantApi.repository.DistrictRepository;
import com.example.RestaurantApi.repository.delegate.DistrictRepositoryDelegate;
import com.example.RestaurantApi.request.DistrictRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DistrictRepositoryDelegateImpl implements DistrictRepositoryDelegate {

    private final DistrictRepository districtRepository;

    @Transactional(readOnly = true)
    @Override
    public List<DistrictDto> getDistricts() {
        return districtRepository.findAll()
                .stream()
                .map(DistrictConverter::convert)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public DistrictDto getDistrict(int districtId){
        Optional<District> response = districtRepository.findById(districtId);
        if (response.isEmpty()){
            throw new DistrictNotFoundException("District ID Not Found - " + districtId);
        }
        return DistrictConverter.convert(response.get());

    }

    @Transactional
    @Override
    public DistrictDto createDistrict(DistrictRequest request) {
        District district = DistrictConverter.convert(request.getDistrictDto());
        district.setCreateDate(LocalDateTime.now());
        districtRepository.save(district);
        return DistrictConverter.convert(district);
    }

    @Transactional
    @Override
    public void updateDistrict(DistrictDto districtDto) {
        District district = DistrictConverter.convert(districtDto);
        district.setUpdateDate(LocalDateTime.now());
        districtRepository.save(district);

    }

    @Transactional
    @Override
    public void deleteDistrict(int districtId) {

        Optional<District> response = districtRepository.findById(districtId);
        if (response.isEmpty()) {
            throw new DistrictNotFoundException("District ID Not Found - " + districtId);
        }
        districtRepository.delete(response.get());
    }
}

