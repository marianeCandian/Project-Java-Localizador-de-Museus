package com.betrybe.museumfinder.solution;

import com.betrybe.museumfinder.controller.MuseumController;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// ... imports

public class MuseumControllerTest {

  @Mock
  private MuseumServiceInterface museumService;

  @InjectMocks
  private MuseumController museumController;

  private MockMvc mockMvc;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(museumController).build();
  }

  @Test
  public void gettingMuseumSuccessfully() throws Exception {
    Long museumId = 1L;
    Museum requiredMuseum = new Museum();
    requiredMuseum.setId(museumId);
    requiredMuseum.setName("Goku Museum");
    requiredMuseum.setDescription("Dragon Ball museum");
    requiredMuseum.setAddress("Tokyo, Japan");
    requiredMuseum.setCollectionType("history");
    requiredMuseum.setCoordinate(new Coordinate(23.1254, -46.4051));

    when(museumService.getMuseum(museumId)).thenReturn(requiredMuseum);

    mockMvc.perform(get("/museums/{id}", museumId))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(requiredMuseum.getId()))
        .andExpect(jsonPath("$.name").value(requiredMuseum.getName()))
        .andExpect(jsonPath("$.description").value(requiredMuseum.getDescription()))
        .andExpect(jsonPath("$.address").value(requiredMuseum.getAddress()))
        .andExpect(jsonPath("$.collectionType").value(requiredMuseum.getCollectionType()));
  }

  @Test
  public void gettingMuseumNotFound() throws Exception {
    when(museumService.getMuseum(anyLong())).thenThrow(MuseumNotFoundException.class);
    String url = "/museums/999";
    mockMvc.perform(get(url))
        .andExpect((status().isNotFound()));
  }
}
