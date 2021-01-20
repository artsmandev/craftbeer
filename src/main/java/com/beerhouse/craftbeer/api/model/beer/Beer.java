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
package com.beerhouse.craftbeer.api.model.beer;

import static java.util.Objects.hash;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * This class represents a Beer.
 *
 * @author Bruno Andrade
 * @since 1.0
 */
@Entity
@Table(name = "beer")
public class Beer {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;
  private String name;
  private String ingredients;
  @Column(name = "alcohol_content")
  private byte alcoholContent;
  private BigDecimal price;
  private String category;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIngredients() {
    return ingredients;
  }

  public void setIngredients(String ingredients) {
    this.ingredients = ingredients;
  }

  public byte getAlcoholContent() {
    return alcoholContent;
  }

  public void setAlcoholContent(byte alcoholContent) {
    this.alcoholContent = alcoholContent;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Beer beer = (Beer) o;
    return id == beer.id;
  }

  @Override
  public int hashCode() {
    return hash(id);
  }

  @Override
  public String toString() {
    return new StringBuffer("Beer{")
                    .append("id=").append(id)
                    .append(", name='").append(name).append('\'')
                    .append(", ingredients='").append(ingredients).append('\'')
                    .append(", alcoholContent=").append(alcoholContent)
                    .append(", price=").append(price)
                    .append(", category='").append(category).append('\'')
                    .append('}')
                    .toString();
  }

}
