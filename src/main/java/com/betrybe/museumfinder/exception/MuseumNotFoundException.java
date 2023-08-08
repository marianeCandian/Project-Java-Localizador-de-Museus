package com.betrybe.museumfinder.exception;

public class MuseumNotFoundException extends RuntimeException{
    public MuseumNotFoundException() {
      super("Museu não encontrado!");
    }
}
