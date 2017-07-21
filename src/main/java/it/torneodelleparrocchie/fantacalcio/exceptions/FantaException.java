package it.torneodelleparrocchie.fantacalcio.exceptions;

public class FantaException extends Exception {

    protected long timestamp;
    protected String errorKey;
    protected String errorMsg;

    @Override
    public String getMessage() {
        if (errorKey != null && errorMsg != null) {
            return String.format("[%s] %s", errorKey, errorMsg);
        }
        return super.getMessage();
    }

    public FantaException(String errorKey, String errorMsg) {
        super(String.format("[%s] %s", errorKey, errorMsg));
        this.errorKey = errorKey;
        this.errorMsg = errorMsg;
        timestamp = System.currentTimeMillis();
    }

    public FantaException(String errorKey, String errorMsg, Throwable cause) {
        super(String.format("[%s] %s", errorKey, errorMsg), cause);
        this.errorKey = errorKey;
        this.errorMsg = errorMsg;
        timestamp = System.currentTimeMillis();
    }
}
