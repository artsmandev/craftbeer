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
package com.beerhouse.craftbeer.api.domain.beer;

import static java.math.BigDecimal.valueOf;
import static java.util.Set.of;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.beerhouse.craftbeer.api.domain.category.Category;
import com.beerhouse.craftbeer.api.domain.ingredient.Ingredient;
import org.junit.Test;

/**
 * Unity tests for Beer Entity.
 *
 * @author Bruno Andrade
 * @since 1.0
 */
public class BeerTests {

  @Test
  public void new_beer_instance_with_all_parameters_the_instance_attributes_must_be_the_same_passed_as_args() {
    var hops = new Ingredient(1L, "hops");
    var yeast = new Ingredient(2L, "yeast");
    var ingredients = of(hops, yeast);
    var lager = new Category(1L, "lager");
    var alcoholContent = (byte) 10;
    var price = valueOf(12.5);

    Beer budweiser = new Beer(10L, "Budweiser", ingredients, alcoholContent, lager, price);

    assertNotNull(budweiser);

    assertFalse(budweiser.getIngredients().isEmpty());
    assertEquals(2, budweiser.getIngredients().size());
    assertEquals(ingredients, budweiser.getIngredients());
    assertTrue(budweiser.getIngredients().contains(hops));
    assertTrue(budweiser.getIngredients().contains(yeast));

    assertEquals(alcoholContent, budweiser.getAlcoholContent().byteValue());
    assertEquals(price, budweiser.getPrice());
  }

}
