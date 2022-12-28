
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 1 대 1 소켓 통신 서버 예제
 */
public class Server {
    public static void main(String[] args) {
        // 서버, 소켓 선언
        ServerSocket server = null;
        Socket socket = null;

        // reader, writer 선언
        BufferedReader in = null;
        BufferedWriter out = null;
        Scanner sc = new Scanner(System.in);

        try{
            // Port 번호 5555로 서버 생성
            server = new ServerSocket(5555);
            System.out.println("연결 대기...");

            // 서버와 연결이 성공 시
            socket = server.accept();
            System.out.println("연결 됨.");

            // 입력, 출력 준비
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while(true){
                // Client의 Request 수신
                String msg = in.readLine();

                // GET/HEAD/POST/PUT 의 Request 에 대한 Response
                if(msg.equals("GET-응답 200")){
                    System.out.println("HTTP/1.1 200 OK");
                } else if(msg.equals("GET-응답 404")){
                    System.out.println("HTTP/1.1 404 Not Found");
                } else if(msg.equals("HEAD-응답 100")){
                    System.out.println("HTTP/1.1 100 Continue");
                } else if(msg.equals("HEAD-응답 101")){
                    System.out.println("HTTP/1.1 101 Switching Protocols");
                } else if(msg.equals("POST-응답 500")){
                    System.out.println("HTTP/1.1 500 Internal Server Error");
                } else if(msg.equals("POST-응답 400")){
                    System.out.println("HTTP/1.1 400 Bad Request");
                } else if(msg.equals("PUT-응답 301")){
                    System.out.println("HTTP/1.1 301 Moved Permanently");
                } else if(msg.equals("PUT-응답 503")){
                    System.out.println("HTTP/1.1 503 Service Unavailable");
                } else if(msg.equals("BYE")){
                    // 종료 메세지
                    System.out.println("종료");
                    break;
                }
            }

        } catch(IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try{
                sc.close();
                in.close();
                out.close();
                socket.close();
                server.close();
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}