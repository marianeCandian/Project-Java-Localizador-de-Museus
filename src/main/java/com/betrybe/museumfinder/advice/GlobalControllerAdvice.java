package com.betrybe.museumfinder.advice;

import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Class GlobalControllerAdvice.
 */
@ControllerAdvice
public class GlobalControllerAdvice {

  /**
   * Advice for Invalid Coordinate.
   */
  @ExceptionHandler({
      InvalidCoordinateException.class
  })
  public ResponseEntity<String> handleCoordinateInvalid(Exception exception) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(exception.getMessage());
  }

  /**
   * Advice for Museum Not Found.
   */
  @ExceptionHandler({
      MuseumNotFoundException.class
  })
  public ResponseEntity<String> handleMuseumNotFound(Exception exception) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(exception.getMessage());
  }

  @ExceptionHandler
  public ResponseEntity<String> handlerException(Exception exception) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno!");
  }

}
