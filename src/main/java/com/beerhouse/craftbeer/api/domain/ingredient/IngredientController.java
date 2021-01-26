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
package com.beerhouse.craftbeer.api.domain.ingredient;

import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import com.beerhouse.craftbeer.api.domain.category.Category;
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
 * This class is responsible for all requests from Ingredients.
 *
 * @author Bruno Andrade
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1/beers/ingredients")
class IngredientController {
  private IngredientService service;

  public IngredientController(IngredientService service) {
    this.service = service;
  }

  /**
   * Create a new Ingredient.
   *
   * @param requestIngredient json that represents a new ingredient to be create.
   * @return a {@link Ingredient} created contend yours id with status code 201.
   */
  @PostMapping
  @ResponseStatus(CREATED)
  public Ingredient save(@RequestBody Ingredient requestIngredient) {
    return service.save(requestIngredient);
  }

  /**
   * Obtain all Beers already registered.
   *
   * @return all {@link Ingredient} that exists with status code 200.
   */
  @GetMapping
  Set<Ingredient> findAll() {
    return service.findAll();
  }

  /**
   * Obtain an specific Ingredient from an specific id.
   *
   * @param id that identified the {@link Ingredient}.
   * @return ingredient's representation in json the specific beer with status code 200.
   */
  @GetMapping("/{id}")
  Ingredient findById(@PathVariable Long id) {
    return service.findById(id);
  }

  /**
   * Update an specific Ingredient already exists.
   *
   * @param id that represents the specific {@link Ingredient}.
   * @param requestIngredient json that represents a new Ingredient to be updated.
   * @return ingredient's representation in json updated with status code 200.
   */
  @PutMapping("/{id}")
  Ingredient update(@PathVariable Long id, @RequestBody Ingredient requestIngredient) {
    var foundIngredient = service.findById(id);
    copyProperties(requestIngredient, foundIngredient, "id");
    return service.save(foundIngredient);
  }

  /**
   * Delete an existing Ingredient from specific id.
   *
   * @param id from the existing {@link Ingredient}.
   */
  @DeleteMapping("/{id}")
  @ResponseStatus(NO_CONTENT)
  void delete(@PathVariable Long id) {
    service.delete(id);
  }

}
