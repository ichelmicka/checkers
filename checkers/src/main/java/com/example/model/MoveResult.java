package com.example.model;

public class MoveResult {
    private final boolean ok;
    private final String errorMessage;
    private final List<Positions> captures;
    private final Board boardSnapshot;

    private MoveResult(boolean ok, String errorMessage, List<Position> captures, Board boardSnapshot) {
        this.ok = ok;
        this.errorMessage = errorMessage;
        this.captures = captures;
        this.boardSnapshot = boardSnapshot;
    }

    //fabryka dla bledu
    public static MoveResult error(String message) {
        return new MoveResult(false, message, Collections.emptyList(), null);
    }

    //fabryka dla poprawnego ruchu
    public static MoveResult ok(List<Position> captures, Board snapshot) {
        return new MoveResult(true, null, captures, snapshot);
    }

    public boolean isOk() {
        return ok;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<Positions> getCaptures() {
        return captures;
    }

    public Board getBoardSnapshot() {
        return boardSnapshot;
    }

    @Override
    public String toString() {
        if (!ok) {
            return "MoveResult(ERROR: " + erroeMessage + ")";
        }
        return "MoveResult(OK, captures=" + captures.size() + ")";
    }
}
