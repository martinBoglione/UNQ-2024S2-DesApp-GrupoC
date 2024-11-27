package ar.edu.unq.desapp.model.dto;

public record LoginResponseDto(String token, long expiresIn) {
}