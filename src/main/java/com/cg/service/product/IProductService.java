package com.cg.service.product;

import com.cg.model.Product;
import com.cg.model.dto.ProductCreateReqDTO;
import com.cg.service.IGeneralService;

import java.util.ArrayList;
import java.util.List;

public interface IProductService extends IGeneralService<Product, Long> {

    List<Product> getAllByDeletedIsFalseAndCategoryDeletedIsFalse();

    List<Product> filterProductByCategoryId(ArrayList<Long> categoryArr);

    Product create(ProductCreateReqDTO productCreateReqDTO);
}
