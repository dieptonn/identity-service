package com.diepton.indentity_service.dto.request;

import com.diepton.indentity_service.validator.DoBConstraint;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    @Size(min = 3, message = "Msg_003")
    String username;
    @Size(min = 6, message = "Msg_004")
    String password;
    String firstName;
    String lastName;

    @DoBConstraint(min = 18, message = "Msg_008")
    LocalDate dayOfBirth;
}
