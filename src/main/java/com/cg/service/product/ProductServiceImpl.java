package com.cg.service.product;

import com.cg.exception.DataInputException;
import com.cg.exception.UnauthorizedException;
import com.cg.model.Category;
import com.cg.model.Product;
import com.cg.model.dto.ProductCreateReqDTO;
import com.cg.repository.CategoryRepository;
import com.cg.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Product> getAllByDeletedIsFalseAndCategoryDeletedIsFalse() {
        return productRepository.getAllByDeletedIsFalseAndCategoryDeletedIsFalse();
    }

    @Override
    public List<Product> filterProductByCategoryId(ArrayList<Long> categoryArr) {
        return productRepository.filterProductByCategoryId(categoryArr);
    }

    @Override
    public Product create(ProductCreateReqDTO productCreateReqDTO) {

        long categoryId = Long.parseLong(productCreateReqDTO.getCategoryId());

        boolean existsCategory = categoryRepository.existsById(categoryId);

        if (!existsCategory) {
            throw new DataInputException("Danh mục sản phẩm không tồn tại");
        }

        Category category = categoryRepository.getById(categoryId);

        if (category.getDeleted()) {
            throw new DataInputException("Danh mục sản phẩm đã ngừng bán");
        }

        boolean existsTitle = productRepository.existsByTitleAndCategoryId(productCreateReqDTO.getTitle(), categoryId);

        if (existsTitle) {
            throw new UnauthorizedException("Tiêu đề sản phẩm đã tồn tại");
        }

        Product product = productCreateReqDTO.toProduct(category);

        return productRepository.save(product);
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public void delete(Product product) {

    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
