package com.example.game;
import com.example.model.Move; 
import com.example.model.MoveResult;
import com.example.model.Board;

public interface GameListener {
    /**
     * Wywoływane po poprawnym wykonaniu ruchu
     *
     * @param move          ruch wykonany przez gracza
     * @param result        wynik ruchu (np. zbicia)
     * @param snapshotBoard kopia planszy po ruchu
     */
    void onMoveApplied(Move move, MoveResult result, Board snapshotBoard);
}
