package com.stussy.stussyclone20220929.controller.admin.api;

import com.stussy.stussyclone20220929.aop.annotation.LogAspect;
import com.stussy.stussyclone20220929.aop.annotation.ValidAspect;
import com.stussy.stussyclone20220929.dto.CMRespDto;
import com.stussy.stussyclone20220929.dto.admin.ProductAdditionReqDto;
import com.stussy.stussyclone20220929.dto.validation.ValidationSequence;
import com.stussy.stussyclone20220929.exception.CustomValidationException;
import com.stussy.stussyclone20220929.service.admin.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/admin")
@RestController
@RequiredArgsConstructor
public class ProductApi {

    private final ProductService productService;

    @ValidAspect
    @LogAspect
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@Validated(ValidationSequence.class) ProductAdditionReqDto productAdditionReqDto, BindingResult bindingResult) throws Exception {

        String productName = productAdditionReqDto.getName();

//        for(int i = 0; i < 200; i++) {
//            if(i % 4 == 0) {
//                productAdditionReqDto.setName(productName + "-" + (i+1));
//            }
//            productService.addProduct(productAdditionReqDto);
//        }

        return ResponseEntity
                .created(null)
                .body(new CMRespDto<>(1, "Successfully",  null));
    }

    @GetMapping("/products")
    public ResponseEntity<?> getProductList(@RequestParam int pageNumber,
                                            @RequestParam @Nullable String category,
                                            @RequestParam @Nullable String searchText) throws Exception {

        return ResponseEntity.ok(new CMRespDto<>(1, "Successfully", productService.getProductList(pageNumber, category, searchText)));
    }
}
