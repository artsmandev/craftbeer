/*
 * MIT License
 *
 * Copyright (c) 2021 Bruno Andrade
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE
 */
package com.beerhouse.craftbeer.api.domain.category;

import static java.lang.String.format;

import com.beerhouse.craftbeer.api.domain.exception.EntityAlreadyExistsException;
import com.beerhouse.craftbeer.api.domain.exception.EntityInUseException;
import com.beerhouse.craftbeer.api.domain.exception.EntityNotFoundException;
import com.beerhouse.craftbeer.api.domain.ingredient.Ingredient;
import java.util.HashSet;
import java.util.Set;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 * This classe encapsules Category's logic from database.
 *
 * @author Bruno Andrade
 * @since 1.0
 */
@Service
class CategoryService {
  private CategoryRepository repository;

  CategoryService(CategoryRepository repository) {
    this.repository = repository;
  }

  /**
   * Save/Create a new Category.
   *
   * @param category that represents a new category to be save/create.
   * @return a {@link Category} created contend yours id, or, throw {@link EntityAlreadyExistsException}.
   */
  Category save(Category category) {
    try {
      return repository.save(category);
    } catch (DataIntegrityViolationException ex) {
     throw new EntityAlreadyExistsException(
       format("Category name: %s, already exists.", category.getName())
     );
    }
  }

  /**
   * Obtain all Category already registered.
   *
   * @return all {@link Category} that exists, or, an {@link EntityNotFoundException}.
   */
  Set<Category> findAll() {
    var categories = new HashSet<>(repository.findAll());
    if (categories.isEmpty()) {
      throw new EntityNotFoundException("There is no Categories registered.");
    }
    return categories;
  }

  /**
   * Obtain a specific Category from a specific id.
   *
   * @param id represents the specific category.
   * @return a {@link Category}, or, throw {@link EntityNotFoundException}.
   */
  Category findById(Long id) {
    var category = repository.findById(id);
    if (category.isEmpty()) {
      throw new EntityNotFoundException(
        format("Category id: %d, not found.", id)
      );
    }
    return category.get();
  }

  /**
   * Delete an existing Category from specific id.
   *
   * @param id from the existing {@link Category}, should lunch {@link EntityInUseException}.
   */
  public void delete(Long id) {
    var category = findById(id);
    try {
      repository.delete(category);
    } catch (DataIntegrityViolationException exception) {
      throw new EntityInUseException(
        format("Category name: %s, already associated with beers and can't be deleted.", category.getName())
      );
    }
  }

}
