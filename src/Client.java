
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * 1 대 1 소켓 통신 서버 예제
 */
public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        BufferedWriter out = null;
        Scanner sc = new Scanner(System.in);

        try{
            socket = new Socket("localhost", 5555);

            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while(true){
                // Clinet의 Request
                System.out.print("입력 (BYE 입력시 종료) >> ");
                String outMsg = sc.nextLine();

                // 종료 메세지
                if(outMsg.equals("BYE")){
                    out.write(outMsg + "\n");
                    out.flush();
                    System.out.println("종료");
                    break;
                }

                // 정상 메세지
                out.write(outMsg + "\n");
                out.flush();


            }

        } catch(IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try{
                sc.close();
                out.close();
                socket.close();
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}