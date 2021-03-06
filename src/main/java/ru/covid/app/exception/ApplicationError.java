package ru.covid.app.exception;

public enum ApplicationError {

    QR_LINK_IS_EXPIRED(400, "Link for getting sheet is expired"),
    SHEET_NOT_FOUND_BY_QR(400, "Sheet is not found by QR id"),
    SHEET_NOT_FOUND_BY_USER_ID(400, "Sheet is not found by user id"),
    SHEET_NOT_FOUND_BY_SHEET_ID(400, "Sheet is not found by sheet id"),
    CREATING_QR_ERROR(400, "Error while create QR"),

    ENTRY_NOT_FOUND_BY_TASK_ID(400, "Entry not found error"),

    NOT_ENOUGH_AMOUNT(400, "Not enough amount"),

    ACCOUNT_NOT_FOUND_BY_ID(400, "Account not found error"),
    NOT_ENOUGH_MONEY(400, "Not enough money"),
    WRONG_STATUS(400, "Account status is not 'ACTIVE'"),

    INTERNAL_ERROR(500, "Internal server error");

    public final int status;
    public final String description;

    ApplicationError(int status, String description) {
        this.status = status;
        this.description = description;
    }

    public ApplicationErrorException exception(String body) {
        return new ApplicationErrorException(this, body);
    }

    public ApplicationErrorException exception(Exception e, String body) {
        return new ApplicationErrorException(e, this, body);
    }

    public ApplicationErrorException exception(Exception e) {
        return new ApplicationErrorException(e, this);
    }

    public ApplicationErrorException exception() {
        return new ApplicationErrorException(this);
    }

    public static class ApplicationErrorException extends HttpCodeException {

        public ApplicationErrorException(ApplicationError error, String args) {
            super(error.status, error.description + ": ".concat(args));
        }

        public ApplicationErrorException(Exception e, ApplicationError error, String args) {
            super(e, error.status, error.description + ": ".concat(args));
        }

        public ApplicationErrorException(Exception e, ApplicationError error) {
            super(e, error.status, error.description);
        }

        public ApplicationErrorException(ApplicationError error) {
            super(error.status, error.description);
        }
    }
}
