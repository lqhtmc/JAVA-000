import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 简易版http服务器
 */
public class HttpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8081);
        while (true) {
            try {
                Socket socket = ss.accept();
                service(socket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void service(Socket socket) {
        try {
            Thread.sleep(20);
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            pw.println("HTTP/1.1 200 ok");
            pw.println("Content-Type:text/html;charset=utf-8");
            pw.println();
            pw.write("hello,nio");
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
