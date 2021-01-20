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
package com.beerhouse.craftbeer.api.beer;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Extra layer in order to {@link BeerController} do not manipulate the {@link BeerRepository} directly.
 *
 * @author Bruno Andrade
 * @since 1.0
 */
@Service
public class BeerService {
  private BeerRepository repository;

  public BeerService(BeerRepository repository) {
    this.repository = repository;
  }

  /**
   * Save/Create a new Beer.
   *
   * @param beer that represents a new beer to be save/create.
   */
  public void save(Beer beer) {
    repository.save(beer);
  }

  /**
   * Obtain all Beers already registered.
   *
   * @return all beers that exists, or, an empty collection.
   */
  public List<Beer> findAll() {
    return repository.findAll();
  }

  /**
   * Obtain an specific Beer from an specific id.
   *
   * @param id represents the specific Beer.
   * @return a beer, or, an empty optional.
   */
  public Optional<Beer> findById(long id) {
    return repository.findById(id);
  }

  /**
   * Delete an existing Beer from specific id.
   *
   * @param beer id from the existing beer.
   */
  public void delete(Beer beer) {
    repository.delete(beer);
  }

}
