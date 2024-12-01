package com.diepton.indentity_service.dto.request;

import com.diepton.indentity_service.validator.DoBConstraint;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {

    String firstName;
    String lastName;

    @DoBConstraint(min = 18, message = "Msg_008")
    LocalDate dayOfBirth;
    String password;
    List<String> roles;
}
