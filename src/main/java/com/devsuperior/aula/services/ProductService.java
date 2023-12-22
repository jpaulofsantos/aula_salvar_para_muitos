package com.devsuperior.aula.services;

import com.devsuperior.aula.dto.CategoryDTO;
import com.devsuperior.aula.dto.ProductDTO;
import com.devsuperior.aula.entities.Category;
import com.devsuperior.aula.entities.Product;
import com.devsuperior.aula.repositories.CategoryRepository;
import com.devsuperior.aula.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public ProductDTO insert(ProductDTO dto){ //copiando os dados do dto para a entidade product
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());

        for (CategoryDTO categoryDTO : dto.getCategories()){

            Category category = categoryRepository.getReferenceById(categoryDTO.getId());
            //Category category = new Category();
            //category.setId(categoryDTO.getId());
            //category.setName(categoryDTO.getName());
            product.getCategories().add(category);
        }

        product = productRepository.save(product);
        return new ProductDTO(product);
    }
}
