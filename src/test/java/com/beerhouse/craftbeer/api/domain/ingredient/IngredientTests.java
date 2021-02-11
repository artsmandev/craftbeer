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

import static java.util.Objects.nonNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unity tests for Ingredient Entity.
 *
 * @author Bruno Andrade
 * @since 1.0
 */
public class IngredientTests {

  @Test
  public void new_ingredient_instance_with_all_parameters_the_instance_attributes_must_be_the_same_passed_as_args() {
    var hops = new Ingredient(1L, "hops");
    var yeast = new Ingredient(2L, "yeast");
    var maltedBarley = new Ingredient(3L, "malted_barley");
    var water = new Ingredient(4L, "water");

    assertTrue(ingredientWasCreatedWithSuccess(1L, "hops", hops));
    assertTrue(ingredientWasCreatedWithSuccess(2L, "yeast", yeast));
    assertTrue(ingredientWasCreatedWithSuccess(3L, "malted_barley", maltedBarley));
    assertTrue(ingredientWasCreatedWithSuccess(4L, "water", water));
  }

  private boolean ingredientWasCreatedWithSuccess(Long idEspected, String nameEspected, Ingredient ingredientCreated) {
    return (nonNull(ingredientCreated) &&
            idEspected == ingredientCreated.getId() &&
            nameEspected.equals(ingredientCreated.getName())
    );
  }

}
