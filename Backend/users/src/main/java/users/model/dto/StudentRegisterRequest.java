package users.model.dto;

import users.model.entity.StudyStatus;

public record StudentRegisterRequest(
        String firstName,
        String lastName,
        String username,
        String password,
        String indexNumber,
        String facultyName,
        StudyStatus status,
        String city
) {}
