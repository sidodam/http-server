import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread implements Runnable {
    private int port;
    private ServerSocket serverSocket;

    public ServerThread(int port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(this.port);
    }


    @Override
    public void run() {


        try {


            String testing = "testing , testing.....";
            String notFound = "404 PAGE NOT FOUND";

            while (true) {


                System.out.println("listeneing to port 90");
                Socket socket = serverSocket.accept();
                System.out.println("accpeted connection");
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();


                int unByte;
                String methodString = "";
                int cont = 4;
                while ((unByte = inputStream.read()) >= 0 && cont > 0) {
                    methodString += (char) unByte;
                    cont--;
                }

                System.out.println(methodString);


                String html = ""; //placeholder
                if (methodString.equals("GET ")) {
                    html = "<html><title>Prueba</title><body><h1>" + testing + "</h1></body></html>";

                } else {

                    html = "<html><title>Prueba</title><body><h1>" + notFound + "</h1></body></html>";

                }


                String CRLF = "\n\r";

                String response =
                        "HTTP/1.1 200 OK" + CRLF +
                                "Content-Length" + html.getBytes().length + CRLF +
                                CRLF + html + CRLF + CRLF;

                outputStream.write(response.getBytes());

                inputStream.close();
                outputStream.close();
                socket.close();
                serverSocket.close();


            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
