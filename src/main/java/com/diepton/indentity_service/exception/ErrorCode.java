package com.diepton.indentity_service.exception;

public enum ErrorCode {

    Msg_000(500, "Invalid message key"),
    Msg_001(500, "Uncategorized Exception"),
    Msg_002(400, "User already exists"),
    Msg_003(400, "Username must be at least 3 characters"),
    Msg_004(400, "Password must be at least 6 characters"),
    Msg_005(400, "User not found");

    private final int errorCode;
    private final String errorMessage;

    ErrorCode(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
