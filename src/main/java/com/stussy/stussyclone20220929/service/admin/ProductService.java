package com.stussy.stussyclone20220929.service.admin;

import com.stussy.stussyclone20220929.dto.admin.ProductAdditionReqDto;

public interface ProductService {
    public boolean addProduct(ProductAdditionReqDto productAdditionReqDto) throws Exception;
}
