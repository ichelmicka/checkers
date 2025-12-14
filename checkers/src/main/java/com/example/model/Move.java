package com.example.model;

public class Move {
    public final MoveType type;
    public final Position pos;
    public final String playerId;

    public Move(MoveType type, Position pos, String playerId) {
        this.type = type;
        this.pos = pos;
        this.playerId = playerId;
    }

    public static Move parse(String line, String playerId) {
        // np. MOVE 3 4
        String[] parts = line.trim().split("\\s+");
        if (parts.length == 0) return null;
        if (parts[0].equalsIgnoreCase("MOVE") && parts.length >= 3) {
            try {
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);
                return new Move(MoveType.MOVE, new Position(x, y), playerId);
            } catch (NumberFormatException e) { return null; } 
        }
        return null;
    }
}
