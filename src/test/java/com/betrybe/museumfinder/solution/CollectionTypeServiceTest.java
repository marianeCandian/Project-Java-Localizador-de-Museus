package com.betrybe.museumfinder.solution;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.service.CollectionTypeService;
import com.betrybe.museumfinder.dto.CollectionTypeCount;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;

class CollectionTypeServiceTest {

  MuseumFakeDatabase museumFakeDatabase;
  CollectionTypeService collectionTypeService;

  @BeforeEach
  public void setUp() {
    museumFakeDatabase = mock(MuseumFakeDatabase.class);
    collectionTypeService = new CollectionTypeService(museumFakeDatabase);
  }

  @Test
  void testCollectionTypeService() throws Exception {
    String typeList = "história";
    when(museumFakeDatabase.countByCollectionType("história")).thenReturn(13L);
    CollectionTypeCount collectionTypeCount = collectionTypeService.countByCollectionTypes(typeList);

    assertEquals(collectionTypeCount.count(), collectionTypeService.
        countByCollectionTypes(typeList).count());
    assertEquals(collectionTypeCount.collectionTypes()[0], collectionTypeService
        .countByCollectionTypes(typeList).collectionTypes()[0]);
  }
}
