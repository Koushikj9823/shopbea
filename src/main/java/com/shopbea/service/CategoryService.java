package com.shopbea.service;

import com.shopbea.model.Category;
import com.shopbea.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public void addCategory(Category category){
        categoryRepository.save(category);
    }
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

}
