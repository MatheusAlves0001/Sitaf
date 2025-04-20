package com.teste.sitaf.Specifiactions;

import com.teste.sitaf.Models.ProductModel;
import org.springframework.data.jpa.domain.Specification;


public class ProductSpecification {

    public static Specification<ProductModel> getByCategoryId(long categoryId){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("category").get("id"), categoryId);
    }

    public static Specification<ProductModel> getByProductId(long productId){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("id"), productId);
    }
}
