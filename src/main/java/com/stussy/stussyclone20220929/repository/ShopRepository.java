package com.stussy.stussyclone20220929.repository;

import com.stussy.stussyclone20220929.domain.CollectionProduct;
import com.stussy.stussyclone20220929.domain.Product;
import com.stussy.stussyclone20220929.domain.ProductDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ShopRepository {
    public List<CollectionProduct> getCollectionList(Map<String, Object> map) throws Exception;
    public List<ProductDetail> getProduct(int groupId) throws Exception;
}
