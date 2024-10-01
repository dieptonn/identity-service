package com.diepton.indentity_service.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {

    Msg_000(500, "Invalid message key"),
    Msg_001(500, "Uncategorized Exception"),
    Msg_002(400, "User already exists"),
    Msg_003(400, "Username must be at least 3 characters"),
    Msg_004(400, "Password must be at least 6 characters"),
    Msg_005(400, "User not found");

    final int errorCode;
    final String errorMessage;

    ErrorCode(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
