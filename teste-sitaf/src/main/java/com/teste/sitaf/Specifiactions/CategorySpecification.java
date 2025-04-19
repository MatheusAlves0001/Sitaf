package com.teste.sitaf.Specifiactions;

import com.teste.sitaf.Models.RepositoryModel.CategoryModel;
import org.springframework.data.jpa.domain.Specification;

public class CategorySpecification {

    public static Specification<CategoryModel> getByCategoryId(long categoryId){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("id"), categoryId);
    }
}
