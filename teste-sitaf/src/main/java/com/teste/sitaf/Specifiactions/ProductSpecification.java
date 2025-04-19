package com.teste.sitaf.Specifiactions;

import com.teste.sitaf.Models.RepositoryModel.ProductModel;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;


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
