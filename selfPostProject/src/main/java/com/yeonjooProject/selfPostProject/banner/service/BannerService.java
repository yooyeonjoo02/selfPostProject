package com.yeonjooProject.selfPostProject.banner.service;

import com.yeonjooProject.selfPostProject.banner.dto.BannerCreateRequestDTO;
import com.yeonjooProject.selfPostProject.banner.dto.BannerResponseDTO;
import com.yeonjooProject.selfPostProject.banner.entity.Banner;
import com.yeonjooProject.selfPostProject.banner.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BannerService {

    private final BannerRepository bannerRepository;

    public BannerResponseDTO createBanner(BannerCreateRequestDTO requestDTO) {
        Banner banner = Banner.builder()
                .imageUrl(requestDTO.getImageUrl())
                .displayOrder(requestDTO.getDisplayOrder())
                .title(requestDTO.getTitle())
                .targetUrl(requestDTO.getTargetUrl())
                .build();
        Banner savedBanner = bannerRepository.save(banner);
        return new BannerResponseDTO(savedBanner);
    }

    public List<BannerResponseDTO> getAllBanners() {
        return bannerRepository.findAll().stream()
                .map(BannerResponseDTO::new)
                .collect(Collectors.toList());
    }

//    public BannerResponseDTO updateBanner(Long id, BannerCreateRequestDTO requestDTO) {
//        Banner banner = bannerRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Banner not found with id: " + id));
//        banner.setImageUrl(requestDTO.getImageUrl());
//        banner.setDisplayOrder(requestDTO.getDisplayOrder());
//        banner.setTitle(requestDTO.getTitle());
//        banner.setTargetUrl(requestDTO.getTargetUrl());
//        Banner updatedBanner = bannerRepository.save(banner);
//        return new BannerResponseDTO(updatedBanner);
//    }
    public BannerResponseDTO updateBanner(Long id, BannerCreateRequestDTO requestDTO) {
        Banner banner = bannerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Banner not found with id: " + id));

        // 엔티티의 update 메서드 호출
        banner.update(
                requestDTO.getImageUrl(),
                requestDTO.getDisplayOrder(),
                requestDTO.getTitle(),
                requestDTO.getTargetUrl()
        );

    Banner updatedBanner = bannerRepository.save(banner); // 변경 사항 저장
    return new BannerResponseDTO(updatedBanner);
}

    public void deleteBanner(Long id) {
        if (!bannerRepository.existsById(id)) {
            throw new IllegalArgumentException("Banner not found with id: " + id);
        }
        bannerRepository.deleteById(id);
    }
}

