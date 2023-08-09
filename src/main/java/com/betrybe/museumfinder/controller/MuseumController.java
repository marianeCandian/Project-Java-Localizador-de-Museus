package com.betrybe.museumfinder.controller;

import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import com.betrybe.museumfinder.util.ModelDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class Controller.
 */
@RestController
@RequestMapping("/museums")
public class MuseumController {
  private MuseumServiceInterface museumServiceInterface;

  @Autowired
  public MuseumController(MuseumServiceInterface museumServiceInterface) {
    this.museumServiceInterface = museumServiceInterface;
  }

  /**
   * Metodo para criar um Museum.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public MuseumDto createMuseum(@RequestBody MuseumCreationDto museum) {
    Museum dto = ModelDtoConverter.dtoToModel(museum);
    Museum create = museumServiceInterface.createMuseum(dto);
    return ModelDtoConverter.modelToDto(create);
  }

  /**
   * Metodo para buscar pelo Museum mais próximo.
   */
  @GetMapping("/closest")
  @ResponseStatus(HttpStatus.OK)
  public MuseumDto getMuseum(@RequestParam(name = "lat") double latitude,
      @RequestParam(name = "lng") double longitude,
      @RequestParam(name = "max_dist_km") double maxDistance) {
    Coordinate coordinate = new Coordinate(latitude, longitude);
    Museum result = museumServiceInterface.getClosestMuseum(coordinate, maxDistance);
    return ModelDtoConverter.modelToDto(result);
  }
}
