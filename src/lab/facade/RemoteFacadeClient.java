package lab.facade;

import java.io.*;
import java.net.*;

public class RemoteFacadeClient {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java RemoteFacadeClient <COMMAND>");
            return;
        }

        String command = args[0];
        try (Socket socket = new Socket("localhost", 5555);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(command);
            System.out.println("Server says: " + in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
