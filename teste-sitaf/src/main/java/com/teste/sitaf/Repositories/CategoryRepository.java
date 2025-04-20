package com.teste.sitaf.Repositories;

import com.teste.sitaf.Models.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Long>, JpaSpecificationExecutor<CategoryModel> {}
