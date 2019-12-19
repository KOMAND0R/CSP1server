import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    public static final int PORT = 12345;
    public static void main(String[] args) throws IOException
    {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println ("Ожидание подключения...");
        Socket clientSocket = serverSocket.accept();
        System.out.println ("Подключение установлено");

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String cAddress = clientSocket.getInetAddress().toString();

        String inputLine = in.readLine();
        while (!clientSocket.isClosed())
        {
            try
            {
                if (inputLine.equals("stop"))
                {
                    break;
                }
                System.out.println ("Данные: " + inputLine + " от " + cAddress);
                out.println(new Analyser(inputLine).getResult());
                inputLine = in.readLine();
            }
            catch (Exception e)
            {
                break;
            }

        }

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
        System.out.println ("Выключение сервера...");
    }
}

