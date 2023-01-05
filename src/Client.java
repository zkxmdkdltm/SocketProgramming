
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 1 대 1 소켓 통신 클라이언트 예제
 */
public class Client {
    public static void main(String[] args) {
        // 소켓 및 input/output 스트림 선언 (클라이언트 소켓은 서버소켓이 필요없다)
        Socket socket = null;

        InputStream in = null;
        OutputStream out = null;

        Scanner sc = new Scanner(System.in);

        try{
            // Port 번호로 소켓 생성
            socket = new Socket("localhost", 5555);

            // input/output 스트림 생성
            in = socket.getInputStream();
            out = socket.getOutputStream();

            while(true){
                // Clinet의 Request
                System.out.print("입력 (BYE 입력시 종료) >> ");

                String outMsg = sc.next();
                String request_msg = "";

                // 종료 메세지
                if(outMsg.equalsIgnoreCase("bye")){
                    System.out.println("종료");
                    request_msg = "bye";
                    byte[] bytes = request_msg.getBytes("UTF-8");
                    out.write(bytes);
                    out.flush();
                    break;

                // GET/POST/PUT/DELETE 요청
                }else if(outMsg.equals("GET")){
                    request_msg = "GET /index.html HTTP/1.1\r\nHost: 127.0.0.1:5555\r\nUser-Agent: insomnia/2023.1.5\r\nAccept: */*\r\n\r\n";
                }else if(outMsg.equals("POST")){
                    request_msg = "POST /index.html HTTP/1.1\r\nHost: 127.0.0.1:5555\r\nUser-Agent: insomnia/2023.1.5\r\nAccept: */*\r\n\r\n";
                }else if(outMsg.equals("HEAD")){
                    request_msg = "HEAD /index.html HTTP/1.1\r\nHost: 127.0.0.1:5555\r\nUser-Agent: insomnia/2023.1.5\r\nAccept: */*\r\n\r\n";
                }else if(outMsg.equals("PUT")){
                    request_msg = "PUT /index.html HTTP/1.1\r\nHost: 127.0.0.1:5555\r\nUser-Agent: insomnia/2023.1.5\r\nAccept: */*\r\n\r\n";
                }else if(outMsg.equals("DELETE")){
                    request_msg = "DELETE /index.html HTTP/1.1\r\nHost: 127.0.0.1:5555\r\nUser-Agent: insomnia/2023.1.5\r\nAccept: */*\r\n\r\n";
                }else{
                    request_msg = "NotAllowed /index.html HTTP/1.1\r\nHost: 127.0.0.1:5555\r\nUser-Agent: insomnia/2023.1.5\r\nAccept: */*\r\n\r\n";                }
                byte[] bytes = request_msg.getBytes("UTF-8");
                out.write(bytes);
                out.flush();

                // Server의 Response 메시지 수신 및 출력
                in = socket.getInputStream();
                bytes = new byte[300];
                int inputBytes = in.read(bytes);
                String message = new String(bytes, 0, inputBytes, "UTF-8");
                System.out.println(message);


            }

        } catch(IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try{
                // 소켓및 input/output stream 종료
                sc.close();
                in.close();
                out.close();
                socket.close();
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}