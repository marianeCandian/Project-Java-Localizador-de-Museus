package com.betrybe.museumfinder.service;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.util.CoordinateUtil;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Class MuseumService.
 */
@Service
public class MuseumService implements MuseumServiceInterface {
  private final MuseumFakeDatabase museumFakeDatabase;

  @Autowired
  public MuseumService(MuseumFakeDatabase museumFakeDatabase) {
    this.museumFakeDatabase = museumFakeDatabase;
  }

  /**
  * Metodo getClosesMuseum.
  */
  public Museum getClosestMuseum(Coordinate coordinate, Double maxDistance) {
    CoordinateUtil coordinateUtil = new CoordinateUtil();
    boolean isValid = coordinateUtil.isCoordinateValid(coordinate);
    if (!isValid) {
      throw new InvalidCoordinateException();
    }

    Optional<Museum> museum = museumFakeDatabase.getClosestMuseum(coordinate, maxDistance);

    if (museum.isEmpty()) {
      throw new MuseumNotFoundException();
    }

    return museum.get();
  }

  /**
   * Metodo createMuseum.
   */
  @Override
  public Museum createMuseum(Museum museum) {
    CoordinateUtil coordinateUtil = new CoordinateUtil();
    boolean isValid = coordinateUtil.isCoordinateValid(museum.getCoordinate());
    if (!isValid) {
      throw new InvalidCoordinateException();
    }

    Museum newMuseum = museumFakeDatabase.saveMuseum(museum);
    return newMuseum;
  }

  /**
   * Metodo getMuseum.
   */
  public Museum getMuseum(Long id) {
    Optional<Museum> museum = museumFakeDatabase.getMuseum(id);

    if (museum.isEmpty()) {
      throw new MuseumNotFoundException();
    }

    return museum.get();
  }

}
