package com.betrybe.museumfinder.dto;

import com.betrybe.museumfinder.model.Coordinate;

/**
 * MuseumCreationDto.
 */
public record MuseumCreationDto(String name, String description,
    String address, String collectionType, String url,
    String subject, Coordinate coordinate) {

}
