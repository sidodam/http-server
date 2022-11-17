import java.io.IOException;

public class httpServer {

    public static void main(String[] args) throws IOException {


        ServerThread serverThread = new ServerThread(90);

        serverThread.run();


    }
}



