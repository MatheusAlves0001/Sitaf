package com.teste.sitaf.Repositories;

import com.teste.sitaf.Models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long>, JpaSpecificationExecutor<ProductModel> {
    boolean existsByName(String name);
}
