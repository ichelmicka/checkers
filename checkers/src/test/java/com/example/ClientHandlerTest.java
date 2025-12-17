package com.example;

import com.example.model.Player;
import com.example.server.ClientHandler;
import com.example.server.Server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class ClientHandlerTest {

    @BeforeEach
    void resetServerSingleton() throws Exception {
        Field instance = Server.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    void sendWritesToSocket() throws Exception {
        // świeży Server
        Server server = Server.getInstance(0, 19);

        // tworzy lokalne połączenie: klient <-> serverSocket
        try (ServerSocket ss = new ServerSocket(0)) { // port 0 = dynamiczny wolny port
            try (Socket client = new Socket("localhost", ss.getLocalPort());
                 Socket serverSide = ss.accept()) {

                // przechwytujemy output (ręcznie ustawimy writer w handlerze)
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PrintWriter pw = new PrintWriter(baos, true);

                // handler skonstruowany z prawdziwym Socketem po stronie serwera
                ClientHandler handler = new ClientHandler(server, serverSide);
                handler.setOut(pw);

                handler.send("HELLO");

                assertEquals("HELLO\n", baos.toString());
            }
        }
    }

    @Test
    void setPlayerStoresPlayer() throws Exception {
        Server server = Server.getInstance(0, 19);

        // para socketów jak wyżej
        try (ServerSocket ss = new ServerSocket(0)) {
            try (Socket client = new Socket("localhost", ss.getLocalPort());
                 Socket serverSide = ss.accept()) {

                ClientHandler handler = new ClientHandler(server, serverSide);

                Player p = new Player("id1", "Ala", null);
                handler.setPlayer(p);

                assertEquals(p, handler.getPlayer());
            }
        }
    }
}