package com.example.game;

import com.example.model.*;
import java.util.*;



public class Game {
    private String id;
    private Board board;
    private Map<Stone, Player> players;
    private Stone currentTurn;
    private GameState state;

    public Game(String id, int boardSize) {
        this.id = id;
        this.board = new Board(boardSize);
        this.players = new HashMap<>();
        this.currentTurn = Stone.BLACK;
        this.state = GameState.WAITING;
    }

    public Board getBoard() {
         return board; 
    }

    public void start() {
        if (players.size() == 2) {
            state = GameState.RUNNING;
        }
    }

    public Stone getCurrentTurn() {
        return currentTurn;
    }

    public MoveResult applyMove(Move move) {
        Player current = players.get(move.getPlayerId()); //wez gracza, ktory gra kolorem podanym w ruchu
        if (state != GameState.RUNNING) return MoveResult.error("Game not running");
        if (current.getColor() != currentTurn) return MoveResult.error("Not your turn");

        MoveResult result = board.placeStone(move.getX(), move.getY(), current.getColor());

        if (!result.isOk()) {
            return result;
        }

        int captured = result.getCaptures().size();
        
        current.addPrisoners(captured);
        
        if (currentTurn == Stone.BLACK) {
            currentTurn = Stone.WHITE;
        }
        else {
            currentTurn = Stone.BLACK;
        }
        
        return result;
    }

    public String serializeState() {
        return board.toString();
    }

}
