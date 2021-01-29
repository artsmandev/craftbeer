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

import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.Set;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is responsible for all requests from Categories.
 *
 * @author Bruno Andrade
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1/beers/categories")
class CategoryController {
  private CategoryService service;

  CategoryController(CategoryService service) {
    this.service = service;
  }

  /**
   * Create a new {@link Category}.
   *
   * @param requestCategory json that represents a new category to be create.
   * @return a {@link Category} created contend yours id with status code 201.
   */
  @PostMapping
  @ResponseStatus(CREATED)
  public Category save(@RequestBody Category requestCategory) {
    return service.save(requestCategory);
  }

  /**
   * Obtain all Categories already registered.
   *
   * @return all {@link Category} that exists with status code 200.
   */
  @GetMapping
  Set<Category> findAll() {
    return service.findAll();
  }

  /**
   * Obtain an specific Category from an specific id.
   *
   * @param id that identified the {@link Category}.
   * @return category's representation in json the specific category with status code 200.
   */
  @GetMapping("/{id}")
  Category findById(@PathVariable Long id) {
    return service.findById(id);
  }

  /**
   * Update an specific Category already exists.
   *
   * @param id that represents the specific {@link Category}.
   * @param requestCategory json that represents a new Category to be updated.
   * @return category's representation in json updated with status code 200.
   */
  @PutMapping("/{id}")
  Category update(@PathVariable Long id, @RequestBody Category requestCategory) {
    var foundCategory = service.findById(id);
    copyProperties(requestCategory, foundCategory, "id");
    return service.save(foundCategory);
  }

  /**
   * Delete an existing Category from specific id.
   *
   * @param id from the existing {@link Category}.
   */
  @DeleteMapping("/{id}")
  @ResponseStatus(NO_CONTENT)
  void delete(@PathVariable Long id) {
    service.delete(id);
  }

}
