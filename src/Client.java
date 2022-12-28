
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * 1 대 1 소켓 통신 클라이언트 예제
 */
public class Client {
    public static void main(String[] args) {
        // 소켓 및 input/output 스트림 선언 (클라이언트 소켓은 서버소켓이 필요없다)
        Socket socket = null;
        BufferedReader in = null;
        BufferedWriter out = null;
        Scanner sc = new Scanner(System.in);

        try{
            // Port 번호로 소켓 생성
            socket = new Socket("localhost", 5555);

            // input/output 스트림 생성
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while(true){
                // Clinet의 Request
                System.out.print("입력 (BYE 입력시 종료) >> ");
                String outMsg = sc.nextLine();

                // 종료 메세지
                if(outMsg.equalsIgnoreCase("bye")){
                    out.write(outMsg + "\n");
                    out.flush();
                    System.out.println("종료");
                    break;
                }

                // 정상 메세지
                out.write(outMsg + "\n");
                out.flush();

                // Server의 Response 메시지 수신 및 출력
                String inMsg = in.readLine();
                System.out.println("서버 >> : " + inMsg);


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