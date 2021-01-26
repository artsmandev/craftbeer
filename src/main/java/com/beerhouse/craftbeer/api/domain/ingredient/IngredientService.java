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

import static java.lang.String.format;

import com.beerhouse.craftbeer.api.domain.exception.EntityAlreadyExistsException;
import com.beerhouse.craftbeer.api.domain.exception.EntityInUseException;
import com.beerhouse.craftbeer.api.domain.exception.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 * Extra layer in order to {@link IngredientController} do not manipulate the {@link IngredientRepository} directly.
 *
 * @author Bruno Andrade
 * @since 1.0
 */
@Service
class IngredientService {
  private IngredientRepository repository;

  IngredientService(IngredientRepository repository) {
    this.repository = repository;
  }

  /**
   * Save/Create a new Ingredient.
   *
   * @param ingredient that represents a new ingredient to be save/create.
   * @return a {@link Ingredient} created contend yours id, or, throw {@link EntityAlreadyExistsException}.
   */
  Ingredient save(Ingredient ingredient) {
    try {
      return repository.save(ingredient);
    } catch (DataIntegrityViolationException ex) {
      throw new EntityAlreadyExistsException(
        format("Ingredient: %s already exists.", ingredient.getName())
      );
    }
  }

  /**
   * Obtain all Ingredients already registered.
   *
   * @return all {@link Ingredient} that exists, or, an {@link EntityNotFoundException}.
   */
  Set<Ingredient> findAll() {
    var ingredients = new HashSet<>(repository.findAll());
    if (ingredients.isEmpty()) {
      throw new EntityNotFoundException("There is no Ingredients registered.");
    }
    return ingredients;
  }

  /**
   * Obtain a specific Ingredient from a specific id.
   *
   * @param id represents the specific ingredient.
   * @return a {@link Ingredient}, or, throw {@link EntityNotFoundException}.
   */
  Ingredient findById(Long id) {
    var ingredient = repository.findById(id);
    if (ingredient.isEmpty()) {
      throw new EntityNotFoundException(
        format("Ingredient id: %d, not found.", id)
      );
    }
    return ingredient.get();
  }

  /**
   * Delete an existing Ingredient from specific id.
   *
   * @param id from the existing {@link Ingredient}, should lunch {@link EntityInUseException}.
   */
  void delete(Long id) {
    var ingredient = findById(id);
    try {
      repository.delete(ingredient);
    } catch (DataIntegrityViolationException ex) {
      throw  new EntityInUseException(
        format("Ingredient name: %s, already associate with beers and can't be deleted.", ingredient.getName())
      );
    }
  }

}
