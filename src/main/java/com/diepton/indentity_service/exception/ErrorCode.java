package com.diepton.indentity_service.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {

    Msg_001(400, "Invalid message key", HttpStatus.BAD_REQUEST),
    Msg_000(500, "Uncategorized Exception", HttpStatus.INTERNAL_SERVER_ERROR),
    Msg_002(400, "User already exist", HttpStatus.BAD_REQUEST),
    Msg_003(400, "Username must be at least 3 characters", HttpStatus.BAD_REQUEST),
    Msg_004(400, "Password must be at least 6 characters", HttpStatus.BAD_REQUEST),
    Msg_005(404, "User not found", HttpStatus.NOT_FOUND),
    Msg_006(401, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    Msg_007(403, "You do not have permission", HttpStatus.FORBIDDEN);

    final int errorCode;
    final String errorMessage;
    final HttpStatusCode httpStatusCode;
}
