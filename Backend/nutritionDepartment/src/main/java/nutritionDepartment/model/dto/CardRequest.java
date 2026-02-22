package nutritionDepartment.model.dto;

import java.util.Date;

public record CardRequest(Long studentId, Double balance, Date expirationDate) {}
