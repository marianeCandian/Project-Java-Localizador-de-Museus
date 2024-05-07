package com.betrybe.museumfinder.solution;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class MuseumServiceTest {

  @Mock
  private MuseumFakeDatabase museumFakeFakeDatabase;

  @InjectMocks
  private MuseumService museumServ;

  @BeforeEach
  public void initSetting() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void gettingMuseumSuccessfully() {
    Long museumId = 1L;
    Coordinate coordinate = new Coordinate(23.1254, -46.4051);
    Museum requiredMuseum = new Museum();
    requiredMuseum.setId(museumId);
    requiredMuseum.setName("Goku Museum");
    requiredMuseum.setDescription("Dragon Ball museum");
    requiredMuseum.setAddress("Tokyo, Japan");
    requiredMuseum.setCollectionType("history");
    requiredMuseum.setCoordinate(coordinate);

    when(museumFakeFakeDatabase.getMuseum(museumId)).thenReturn(Optional.of(requiredMuseum));

    Museum museum = museumServ.getMuseum(museumId);

    assertEquals(requiredMuseum, museum);
  }

  @Test
  public void gettingMuseumNotFound() {
    Long museumId = 1L;
    when(museumFakeFakeDatabase.getMuseum(museumId)).thenReturn(Optional.empty());

    assertThrows(MuseumNotFoundException.class, () -> museumServ.getMuseum(museumId));
  }
}
