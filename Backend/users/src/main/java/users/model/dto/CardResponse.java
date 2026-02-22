package users.model.dto;

import java.util.Date;
import java.util.List;

public record CardResponse(Long id, Double balance, Date expirationDate, Long studentId, List<MealResponse> meals) {}