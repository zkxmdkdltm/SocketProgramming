# SocketProgramming

# 목차
0. 참고 자료
1. 소켓(Socket)과 소켓통신이란
2. 서버와 클라이언트
3. 서버 소켓 구현
4. 클라이언트 소켓 구현
5. 클라이언트 GET/HEAD/POST/PUT Request 목록

## 0. 참고자료
- https://javaplant.tistory.com/18
- https://www.youtube.com/watch?v=dX82Wuc18wk
- https://kadosholy.tistory.com/125

## 1. 소켓(Socket)이란
소켓이란 프로세스가 네트워크 세계로 데이터를 내보내거나 혹은 데이터를 받기 위한 실제적인 창구역할으로 프로토콜, IP주소, 포트 넘버로 정의되며 역할에 따라 서버 소켓, 클라이언트 소켓으로 구분된다.

소켓과 통신을 위해 네트워크상에서 클라이언트와 서버에 해당되는 컴퓨터를 식별하기 위한 **IP주소**와 응용프로그램을 식별하기 위한 **포트번호**가 사용된다.

## 2. 서버와 클라이언트
- **서버** : 클라이언트의 소켓 연결 요청을 대기하고, 연결요청이 오면 클라이언트 소켓을 생성하여 통신을 가능하게 한다.
- **클라이언트** : 실제로 데이터 송수신이 일어나는 소켓이다.

## 3. 서버 소켓 구현
서버, 소켓 선언
```
ServerSocket server = null;
Socket socket = null;
```

데이터 송수신을 위한 input/output 스트림 선언
```
BufferedReader in = null;
BufferedWriter out = null;
```

Port 번호로 서버 생성 및 클라이언트 연결
```
server = new ServerSocket(5555);
socket = server.accept();
```

input/output 스트림 생성
```
in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
```

Client가 보낸 Request 수신
```
String msg = in.readLine();
```

GET/HEAD/POST/PUT Request에 따른 Response를 Client로 전송
```
switch(inMsg){
    case "GET-응답 200":
        out.write("HTTP/1.1 200 OK" + "\n");
        break;
    case "GET-응답 404":
        out.write("HTTP/1.1 404 Not Found" + "\n");
        break;
    case "HEAD-응답 100":
        out.write("HTTP/1.1 100 Continue" + "\n");
        break;
    case "HEAD-응답 101":
        out.write("HTTP/1.1 101 Switching Protocols" + "\n");
        break;
    case "POST-응답 500":
        out.write("HTTP/1.1 500 Internal Server Error" + "\n");
        break;
    case "POST-응답 400":
        out.write("HTTP/1.1 400 Bad Request" + "\n");
        break;
    case "PUT-응답 301":
        out.write("HTTP/1.1 301 Moved Permanently" + "\n");
        break;
    case "PUT-응답 503":
        out.write("HTTP/1.1 503 Service Unavailable" + "\n");
        break;
    default:
        out.write("잘못된 요청입니다." + "\n");
        break;
}

out.flush();
```

소켓, 서버 및 input/output stream 종료
```
in.close();
out.close();
socket.close();
server.close();
```

## 4. 클라이언트 소켓 구현

소켓 선언 (클라이언트 소켓은 서버소켓이 필요없다)
```
Socket socket = null;
```

데이터 송수신을 위한 input/output 스트림 선언
```
BufferedReader in = null;
BufferedWriter out = null;
```

Port 번호로 소켓 생성
```
socket = new Socket("localhost", 5555);
```

input/output 스트림 생성
```
in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
```

Client의 Request 메시지 입력 및 Server로 전송
```
System.out.print("입력 (BYE 입력시 종료) >> ");
String outMsg = sc.nextLine();
out.write(outMsg + "\n");
out.flush();
```

Server의 Response 메시지 수신 및 출력
```
String inMsg = in.readLine();
System.out.println("서버 >> : " + inMsg);
```

소켓및 input/output stream 종료
```
in.close();
out.close();
socket.close();
```

## 5. 클라이언트 GET/HEAD/POST/PUT Request 메시지 목록
<br>
Client request: GET-응답 200

Server response: HTTP/1.1 200 OK

<br>
Client request: GET-응답 404

Server response: HTTP/1.1 404 Not Found

<br>
Client request: HEAD-응답 100

Server response: HTTP/1.1 100 Continue

<br>
Client request: HEAD-응답 101

Server response: HTTP/1.1 101 Switching Protocols

<br>
Client request: POST-응답 500

Server response: HTTP/1.1 500 Internal Server Error

<br>
Client request: POST-응답 400

Server response: HTTP/1.1 400 Bad Request

<br>
Client request: PUT-응답 301

Server response: HTTP/1.1 301 Moved Permanently

<br>
Client request: GET-응답 200

Server response: HTTP/1.1 503 Service Unavailable
