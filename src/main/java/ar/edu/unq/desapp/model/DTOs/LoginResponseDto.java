package ar.edu.unq.desapp.model.DTOs;

public record LoginResponseDto(String token, long expiresIn) {
}