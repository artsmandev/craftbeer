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

import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.util.ReflectionUtils.findField;
import static org.springframework.util.ReflectionUtils.getField;
import static org.springframework.util.ReflectionUtils.setField;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
class BeerController {
  private BeerService service;

  public BeerController(BeerService service) {
    this.service = service;
  }

  /**
   * Create a new Beer.
   *
   * @param requestBeer json that represents a new beer to be create.
   * @return a {@link Beer} created contend yours id with status code 201.
   */
  @PostMapping
  @ResponseStatus(CREATED)
  public Beer save(@RequestBody Beer requestBeer) {
    return service.save(requestBeer);
  }

  /**
   * Obtain all Beers already registered.
   *
   * @return all {@link Beer} that exists with status code 200, or, if no content with status code 204.
   */
  @GetMapping
  public ResponseEntity<List<Beer>> findAll() {
    var beers = service.findAll();
    if (beers.isEmpty()) {
      return noContent().build();
    }
    return ok(beers);
  }

  /**
   * Obtain an specific Beer from an specific id.
   *
   * @param id that identified the beer.
   * @return beer's representation in json the specific beer with status code 200, or, not found with status code 404.
   */
  @GetMapping("/{id}")
  public ResponseEntity<Beer> findById(@PathVariable Long id) {
    return ok(
      service.findById(id)
    );
  }

  /**
   * Update an specific Beer already exists.
   *
   * @param id that represents the specific Beer.
   * @param requestBeer json that represents a new beer to be updated.
   * @return beer's representation  in json updated with status code 200, or, not found with status code 404.
   */
  @PutMapping("/{id}")
  public ResponseEntity<Beer> update(@PathVariable Long id, @RequestBody Beer requestBeer) {
    var foundBeer = service.findById(id);
    copyProperties(requestBeer, foundBeer, "id");
    service.save(foundBeer);
    return ok(foundBeer);
  }

  /**
   * Update specifics fields of a Beer, like just a name, a price, or both name and price at same time.
   *
   * @param id that represents the specific Beer
   * @param fieldsBeer json that represents the fields to be updated.
   * @return beer's representation  in json with status code 200, or, not found and status code 404.
   */
  @PatchMapping("/{id}")
  public ResponseEntity<Beer> parcialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> fieldsBeer) {
    var foundBeer = service.findById(id);
    updateFieldsBeerSendOnBeerFound(fieldsBeer, foundBeer);
    update(id, foundBeer);
    return ok(foundBeer);
  }

  /**
   * Identified the specifics fields that the user want to update on an specific Beer that exists.
   * The fields are collected and a beerRequestFromFields is generate and full field with them.
   * Each field is getting from beerRequestFromFields and updated on existing beer.
   *
   * @param fieldsBeer the specifics fields that will be update on an existing beer.
   * @param foundBeer the existing beer that will be updated.
   */
  private void updateFieldsBeerSendOnBeerFound(Map<String, Object> fieldsBeer, Beer foundBeer) {
    var mapper = new ObjectMapper();
    var beerRequestFromFields = mapper.convertValue(fieldsBeer, Beer.class);
    fieldsBeer.forEach((nameField, valueField) -> {
      var field = findField(Beer.class, nameField);
      field.setAccessible(true);
      var value = getField(field, beerRequestFromFields);
      setField(field, foundBeer, value);
    });
  }

  /**
   * Delete an existing Beer from specific id.
   *
   * @param id from the existing beer.
   * @return beer's representation in json with status code 204, or, not found and status code 404.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Beer> deleteBeer(@PathVariable Long id) {
    var beerFound = service.findById(id);
    service.delete(beerFound);
    return noContent().build();
  }

}
