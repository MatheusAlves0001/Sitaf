package com.teste.sitaf.Specifiactions;

import com.teste.sitaf.Models.RepositoryModel.ProductModel;
import org.springframework.data.jpa.domain.Specification;


public class ProductSpecification {

    public static Specification<ProductModel> hasCategory(Long categoryId){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("category").get("id"), categoryId);
    }
}
