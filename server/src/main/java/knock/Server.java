package knock;

import knock.common.KConsole;
import knock.common.KServerSocket;
import knock.common.KSocket;

import java.io.*;

public class Server {
    public static void main(String[] args) throws IOException {

        int port = 4444;
        KServerSocket serverSocket = new KServerSocket(port);
        KSocket socket = serverSocket.accept();
        String inputLine, outputLine;
        KConsole console = new KConsole();

        Protocol protocol = new Protocol();

        outputLine = protocol.processInput(null);
        socket.writeLine(outputLine);

        while ((inputLine = socket.readLine()) != null) {
            console.writeLine("Client: "+ inputLine);
            outputLine = protocol.processInput(inputLine);
            socket.writeLine(outputLine);
            if (outputLine.equals("Bye."))
                break;
        }
    }
}