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

import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

import java.util.List;
import org.springframework.http.ResponseEntity;
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
 * This class is responsible for all requests from Beers.
 *
 * @author Bruno Andrade
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1/beers")
public class BeerController {
  private BeerRepository repository;

  public BeerController(BeerRepository repository) {
    this.repository = repository;
  }

  @PostMapping
  @ResponseStatus(CREATED)
  public void saveBeer(@RequestBody Beer beerRequest) {
    repository.save(beerRequest);
  }

  @GetMapping
  public ResponseEntity<List<Beer>> allBeers() {
    var beers = repository.findAll();
    if (beers.isEmpty()) {
      return noContent().build();
    }
    return ok(beers);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Beer> findBeerById(@PathVariable long id) {
    var beer = repository.findById(id);
    return beer.map(ResponseEntity::ok).orElseGet(
      () -> notFound().build()
    );
  }

  @PutMapping("/{id}")
  public ResponseEntity<Beer> updateBeer(@PathVariable long id, @RequestBody Beer beerRequest) {
    var beerFound = repository.findById(id);
    if (beerFound.isPresent()) {
      copyProperties(beerRequest, beerFound.get(), "id");
      repository.save(beerFound.get());
      return ok(beerFound.get());
    }
    return notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Beer> deleteBeer(@PathVariable long id) {
    var beerFound = repository.findById(id);
    if (beerFound.isPresent()) {
      repository.delete(beerFound.get());
      return noContent().build();
    }
    return notFound().build();
  }

}
