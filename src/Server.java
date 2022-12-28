
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

        // 데이터 송수신을 위한 input/output 스트림 선언
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

            // input/output 스트림 생성
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while(true){
                // Client의 Request 수신
                String inMsg = in.readLine();

                // GET/HEAD/POST/PUT 의 Request 에 대한 Response
                if(inMsg.equalsIgnoreCase("bye")) {
                    // 종료 메세지
                    System.out.println("종료");
                    out.write("종료" + "\n");
                    out.flush();
                    break;
                }
                System.out.println("클라이언트 >> : " + inMsg);

                if(inMsg.equals("GET-응답 200")){
                    out.write("HTTP/1.1 200 OK" + "\n");
                } else if(inMsg.equals("GET-응답 404")){
                    out.write("HTTP/1.1 404 Not Found" + "\n");
                } else if(inMsg.equals("HEAD-응답 100")){
                    out.write("HTTP/1.1 100 Continue" + "\n");
                } else if(inMsg.equals("HEAD-응답 101")){
                    out.write("HTTP/1.1 101 Switching Protocols" + "\n");
                } else if(inMsg.equals("POST-응답 500")){
                    out.write("HTTP/1.1 500 Internal Server Error" + "\n");
                } else if(inMsg.equals("POST-응답 400")){
                    out.write("HTTP/1.1 400 Bad Request" + "\n");
                } else if(inMsg.equals("PUT-응답 301")){
                    out.write("HTTP/1.1 301 Moved Permanently" + "\n");
                } else if(inMsg.equals("PUT-응답 503")){
                    out.write("HTTP/1.1 503 Service Unavailable" + "\n");
                }
                out.flush();
            }

        } catch(IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try{
                // 소켓, 서버 및 input/output stream 종료
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