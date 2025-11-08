package lab.facade;

import java.io.*;
import java.net.*;

public class RemoteFacadeServer {
    private final ComputerFacade facade;
    private final int port;

    public RemoteFacadeServer(ComputerFacade facade, int port) {
        this.facade = facade;
        this.port = port;
    }

    public void startServer() {
        System.out.println("RemoteFacadeServer: listening on port " + port);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket client = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                     PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {

                    String command = in.readLine();
                    if (command == null) continue;
                    switch (command.toUpperCase()) {
                        case "START":
                            facade.start();
                            out.println("OK: STARTED");
                            break;
                        case "SHUTDOWN":
                            facade.shutdown();
                            out.println("OK: SHUTDOWN");
                            break;
                        case "SLEEP":
                            facade.sleep();
                            out.println("OK: SLEEPING");
                            break;
                        case "WAKE":
                            facade.wake();
                            out.println("OK: AWAKE");
                            break;
                        default:
                            out.println("ERROR: Unknown command");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ComputerFacade facade = new ComputerFacade(new Hdd());
        new RemoteFacadeServer(facade, 5555).startServer();
    }
}
