
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
/**
 * 1 대 1 소켓 통신 서버 예제
 */
public class Server {
    public static void main(String[] args) {
        // 서버, 소켓 선언
        ServerSocket server = null;
        Socket socket = null;

        // 데이터 송수신을 위한 input/output 스트림 선언
        InputStream in = null;
        OutputStream out = null;
        Scanner sc = new Scanner(System.in);

        try{
            // Port 번호 5555로 서버 생성
            server = new ServerSocket(5555);
            System.out.println("연결 대기...");

            // 서버와 연결이 성공 시
            socket = server.accept();
            System.out.println("연결 됨.");

            // input/output 스트림 생성
            in = socket.getInputStream();
            out = socket.getOutputStream();

            while(true){
                // Client 로 부터 request 메세지 수신
                byte[] bytes = new byte[300];
                int inputBytes = in.read(bytes);
                String request_msg = new String(bytes, 0, inputBytes, "UTF-8");

                // 종료 메세지(bye) 수신 시 서버 종료
                if(request_msg.equalsIgnoreCase("bye")){
                    System.out.println("연결 종료");
                    break;
                }

                // 요청 메세지를 쪼개 사용 가능한 폼으로 바꿈
                String[] arr_request_msg = request_msg.split("\r\n");
                String[] msg = arr_request_msg[0].split(" "); // 요청 메세지 첫번째 줄
                String request_method = msg[0]; // 요청 메세지의 메소드
                String request_uri = msg[1]; // 요청 메세지의 uri
                String request_protocol = msg[2]; // 요청 메세지의 응답코드

                String response_msg = "";
                String server_name = "Socket Server";

                // 현재 날짜와 시간을 불러옴
                String strDate = "";
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
                strDate = sdf.format(date);


                // GET/HEAD/POST/PUT 의 Request 에 대한 Response
                if(request_method.equals("GET") && request_protocol.equals("HTTP/1.1")) {
                    System.out.printf("%s %s %s 200\n", request_method, request_protocol, request_uri);
                    response_msg = String.format("%s 200 OK\nServer: %s\nDate: %s\n", request_protocol, server_name, strDate);
                } else if(request_method.equals("POST") && request_protocol.equals("HTTP/1.1")) {
                    System.out.printf("%s %s %s 204\n", request_method, request_protocol, request_uri);
                    response_msg = String.format("%s 204 No content\nServer: %s\nDate: %s\n", request_protocol, server_name, strDate);
                } else if(request_method.equals("HEAD") && request_protocol.equals("HTTP/1.1")) {
                    System.out.printf("%s %s %s 200\n", request_method, request_protocol, request_uri);
                    response_msg = String.format("%s 200 OK\nServer: %s\nDate: %s\n", request_protocol, server_name, strDate);
                } else if(request_method.equals("PUT") && request_protocol.equals("HTTP/1.1")) {
                    System.out.printf("%s %s %s 201\n", request_method, request_protocol, request_uri);
                    response_msg = String.format("%s 201 Created\nServer: %s\nDate: %s\n", request_protocol, server_name, strDate);
                } else if(request_method.equals("DELETE") && request_protocol.equals("HTTP/1.1")) {
                    System.out.printf("%s %s %s 404\n", request_method, request_protocol, request_uri);
                    response_msg = String.format("%s 404 Not found\nServer: %s\nDate: %s\n", request_protocol, server_name, strDate);
                } else {
                    System.out.printf("%s %s %s 405\n", request_method, request_protocol, request_uri);
                    response_msg = String.format("%s 405 NotAllowed\nServer: %s\nDate: %s\n", request_protocol, server_name, strDate);
                }

                // Response 메세지를 output 스트림으로 전송
                out = socket.getOutputStream();
                bytes = response_msg.getBytes("UTF-8");
                out.write(bytes);
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