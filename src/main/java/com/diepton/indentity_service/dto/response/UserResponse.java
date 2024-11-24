package com.diepton.indentity_service.dto.response;

import com.diepton.indentity_service.entity.Role;
import jakarta.persistence.ElementCollection;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

    //    @JsonIgnore // @JsonIgnore (ignore id in api) = @Mapping(target = "id", ignore = true) in UserMapper (no mapping id)
    String id;

    String username;
    String firstName;
    String lastName;
    LocalDate dayOfBirth;

    @ElementCollection
    Set<Role> roles;
}
