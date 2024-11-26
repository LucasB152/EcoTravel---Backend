package be.ecotravel.back.security;

public record LoginResponse(String token, long expiresIn) {
}
