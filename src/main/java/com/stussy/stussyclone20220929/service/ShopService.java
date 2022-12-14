package com.stussy.stussyclone20220929.service;

import com.stussy.stussyclone20220929.dto.shop.CollectionListRespDto;
import com.stussy.stussyclone20220929.dto.shop.ProductDetailRespDto;

import java.util.List;

public interface ShopService {
    public List<CollectionListRespDto> getCollections(String category, int page) throws Exception;
    public ProductDetailRespDto getProductDetails(int groupId) throws Exception;
}

