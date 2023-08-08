package com.betrybe.museumfinder.dto;

import com.betrybe.museumfinder.model.Coordinate;

/**
 * MuseumDto.
 */
public record MuseumDto(Long id, String name, String description,
    String address, String collectionType, String url,
    String subject, Coordinate coordinate) {

}
