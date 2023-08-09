package com.betrybe.museumfinder.exception;

/**
 * Class Exception for Coordinates invalidates.
 */
public class InvalidCoordinateException extends RuntimeException {
  public InvalidCoordinateException() {
    super("Coordenada inválida!");
  }
}
