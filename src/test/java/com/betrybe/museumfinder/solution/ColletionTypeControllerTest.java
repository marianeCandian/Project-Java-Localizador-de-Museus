package com.betrybe.museumfinder.solution;

import com.betrybe.museumfinder.service.CollectionTypeService;
import com.betrybe.museumfinder.controller.CollectionTypeController;
import org.springframework.test.web.servlet.MockMvc;
import com.betrybe.museumfinder.dto.CollectionTypeCount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CollectionTypeController.class)
class CollectionTypeControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  private CollectionTypeService collectionTypeService;

  @BeforeEach
  public void setUp() {
    when(collectionTypeService.countByCollectionTypes("história"))
        .thenReturn(new CollectionTypeCount(new String[]{"história"}, 10));
    when(collectionTypeService.countByCollectionTypes("hist,imag"))
        .thenReturn(new CollectionTypeCount(new String[]{"hist", "imag"}, 492));
    when(collectionTypeService.countByCollectionTypes("unknown"))
        .thenReturn(new CollectionTypeCount(new String[]{"unknown"}, 0));
  }

  @Test
  void testCollectionTypeSingle() throws Exception {
    mockMvc.perform(get("/collections/count/{typesList}", "história")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.collectionTypes[0]").value("história"))
        .andExpect(jsonPath("$.count").value(10));
  }

  @Test
  void testCollectionTypeMultiple() throws Exception {
    mockMvc.perform(get("/collections/count/{typesList}", "hist,imag")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.collectionTypes[0]").value("hist"))
        .andExpect(jsonPath("$.collectionTypes[1]").value("imag"))
        .andExpect(jsonPath("$.count").value(492));
  }

  @Test
  void testCollectionTypeNoResult() throws Exception {
    mockMvc.perform(get("/collections/count/{typesList}", "unknown")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }
}