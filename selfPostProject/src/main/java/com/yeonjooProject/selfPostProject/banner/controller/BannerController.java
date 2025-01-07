package com.yeonjooProject.selfPostProject.banner.controller;

import com.yeonjooProject.selfPostProject.banner.dto.BannerCreateRequestDTO;
import com.yeonjooProject.selfPostProject.banner.dto.BannerResponseDTO;
import com.yeonjooProject.selfPostProject.banner.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banners")
@RequiredArgsConstructor
public class BannerController {

    private final BannerService bannerService;

    @PostMapping
    public ResponseEntity<BannerResponseDTO> createBanner(@Validated @RequestBody BannerCreateRequestDTO requestDTO) {
        BannerResponseDTO responseDTO = bannerService.createBanner(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<BannerResponseDTO>> getAllBanners() {
        List<BannerResponseDTO> banners = bannerService.getAllBanners();
        return ResponseEntity.ok(banners);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BannerResponseDTO> updateBanner(
            @PathVariable Long id,
            @Validated @RequestBody BannerCreateRequestDTO requestDTO) {
        BannerResponseDTO responseDTO = bannerService.updateBanner(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBanner(@PathVariable Long id) {
        bannerService.deleteBanner(id);
        return ResponseEntity.noContent().build();
    }
}
