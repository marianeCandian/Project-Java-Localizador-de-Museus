package com.betrybe.museumfinder.exception;

/**
 * Class Exception for Not Found Museum.
 */
public class MuseumNotFoundException extends RuntimeException {
  public MuseumNotFoundException() {
    super("Museu não encontrado!");
  }
}
