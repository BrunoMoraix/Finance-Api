package com.moraix.finance_api.service;

import com.moraix.finance_api.model.Category;
import com.moraix.finance_api.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category create(String name) {
        Category category = Category.builder().name(name).build();
        return categoryRepository.save(category);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}