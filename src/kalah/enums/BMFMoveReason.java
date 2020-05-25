package kalah.enums;

public enum BMFMoveReason {
    ADDITIONAL_MOVE("it leads to an extra move"),
    CAPTURE("it leads to a capture"),
    LEGAL("it is the first legal move"),
    NONE("no moves are available");


    private final String explanation;

    BMFMoveReason(String explanation) {
        this.explanation = explanation;
    }

    @Override
    public String toString() {
        return this.explanation;
    }
}
