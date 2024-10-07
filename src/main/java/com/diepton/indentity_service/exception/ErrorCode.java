package com.diepton.indentity_service.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {

    Msg_000(500, "Invalid message key"),
    Msg_001(400, "Uncategorized Exception"),
    Msg_002(400, "User already exist"),
    Msg_003(400, "Username must be at least 3 characters"),
    Msg_004(400, "Password must be at least 6 characters"),
    Msg_005(400, "User not found"),
    Msg_006(400, "User is not exist"),
    Msg_007(400, "Unauthenticated");

    final int errorCode;
    final String errorMessage;

    ErrorCode(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
