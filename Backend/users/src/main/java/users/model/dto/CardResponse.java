package users.model.dto;

import java.util.Date;

public record CardResponse(Long id, Double balance, Date expirationDate, Long studentId) {}
