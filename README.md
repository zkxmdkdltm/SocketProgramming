20212674 김동윤 
선택과제 1. 소켓 통신을 활용한 Java Program

# 목차: 
0)	참고자료
1)	소켓(Socket)과 소켓 통신이란
2)	서버와 클라이언트
3)	서버 소켓 구현
4)	클라이언트 소켓 구현
5)	클라이언트 Request 메시지 목록
6)	코드 실행 결과
7)	Wireshark Protocol 확인 결과

# 0.	참고자료
-	https://javaplant.tistory.com/18
-	https://www.youtube.com/watch?v=dX82Wuc18wk
-	https://kadosholy.tistory.com/125


# 1.	소켓이란
소켓이란 프로세스가 네트워크 세계로 데이터를 내보내거나 혹은 데이터를 받기 위한 실제적인 창구 역할으로 프로토콜, IP주소, 포트 넘버로 정의되며 역할에 따라 서버 소켓, 클라이언트 소켓으로 구분된다.
소켓과 통신을 위해 네트워크상에서 클라이언트와 서버에 해당되는 컴퓨터를 식별하기 위한 IP주소와 응용프로그램을 식별하기 위한 포트번호가 사용된다.
# 2.	서버와 클라이언트
-	서버: 클라이언트의 소켓 연결 요청을 대기하고, 연결요청이 오면 클라이언트 소켓을 생성하여 통신을 가능하게 한다.
-	클라이언트: 실제로 데이터 송수신이 일어나는 소켓이다.

# 3.	서버 소켓 구현
-	서버, 소켓 선언
<img width="213" alt="image" src="https://github.com/zkxmdkdltm/SocketProgramming/assets/102042061/1ebb3f02-48a1-4200-b971-215dd7aa4987">

-	데이터 송수신을 위한 input/output 스트림 선언
 <img width="268" alt="image" src="https://github.com/zkxmdkdltm/SocketProgramming/assets/102042061/ac8fbe5d-822f-4966-abfa-1fae8b0e111b">


-	Port 번호로 서버 생성 및 클라이언트 연결
 <img width="228" alt="image" src="https://github.com/zkxmdkdltm/SocketProgramming/assets/102042061/046a0006-69a3-48a3-a6e1-4b10f5f37d25">
 <br>-> TCP통신의 Three Handshaking: 클라이언트로부터 연결이 올때까지 대기하고 연결이 올 시 accept() 메소드가 실행되어 연결이 완료된다.

-	Input/output 스트림 생성
 <img width="196" alt="image" src="https://github.com/zkxmdkdltm/SocketProgramming/assets/102042061/f91e88be-3aee-49bd-bbf4-386162ed244d">
<br>-> 클라이언트가 보내는 메시지를 ‘in’ stream으로 수신 받고 해당 메시지에 대한 서버의 응답을 ‘out’ stream으로 전송한다.

-	Client가 보낸 Request 수신
 <img width="452" alt="image" src="https://github.com/zkxmdkdltm/SocketProgramming/assets/102042061/38b6d4b0-2de1-4c7c-85d4-b1949e526f1d">

-	Request 메시지 split
 <img width="450" alt="image" src="https://github.com/zkxmdkdltm/SocketProgramming/assets/102042061/4c4edaec-e84c-492f-82eb-f8b3c79fa715">

-	GET/HEAD/POST/PUT Request에 따른 Response를 Client로 전송
 <img width="452" alt="image" src="https://github.com/zkxmdkdltm/SocketProgramming/assets/102042061/25d31b87-1fe1-45aa-a03b-c802f8105667">
<br><img width="446" alt="image" src="https://github.com/zkxmdkdltm/SocketProgramming/assets/102042061/810e1454-5f31-4c92-ab15-5f3a76eb48a9">
<br>-> 클라이언트의 Request에 따른 Response 메시지를 stream에 write() 메소드를 이용하여 작성 후 flush() 메소드를 이용해 전송한다.

-	소켓, 서버 및 input/output stream 종료
 <img width="98" alt="image" src="https://github.com/zkxmdkdltm/SocketProgramming/assets/102042061/2fca805a-bcde-481d-919b-a1275c962f9c">
<br>-> 통신이 끝난 후 close() 메소드를 통해 소켓, 서버, 그리고 input/output stream을 종료해준다

# 4.	클라이언트 소켓 구현
-	소켓 선언 (클라이언트 소켓은 서버 소켓이 필요 없음)
 <img width="173" alt="image" src="https://github.com/zkxmdkdltm/SocketProgramming/assets/102042061/cea3ee93-fe70-49a2-bee9-7669b086e536">

-	데이터 송수신을 위한 input/output 스트림 선언
<img width="151" alt="image" src="https://github.com/zkxmdkdltm/SocketProgramming/assets/102042061/1a2ad7bc-360d-4e56-a29a-ac9a7aa51788">


-	Port 번호로 소켓 생성
 <img width="402" alt="image" src="https://github.com/zkxmdkdltm/SocketProgramming/assets/102042061/8bf55a0b-3f5b-4781-8a5b-67450a5b799f">
<br>-> IP는 localhost의 IP를 사용하며 Port 번호는 서버의 Port 번호와 똑같이 설정 해준다.


-	Input/output 스트림 생성
 <img width="196" alt="image" src="https://github.com/zkxmdkdltm/SocketProgramming/assets/102042061/d54afaea-553f-499b-9499-965b1913851a">
<br>-> 서버가 보내는 메시지를 ‘in’ stream으로 수신 받고 해당 메시지에 대한 클라이언트의 응답을 ‘out’ stream으로 전송한다.

-	Client Request 메시지 입력
 <img width="175" alt="image" src="https://github.com/zkxmdkdltm/SocketProgramming/assets/102042061/23288df2-318b-4fba-b898-1d27a70a5760">


-	Client Request 메시지에 따른 Header 전송
 <img width="438" alt="image" src="https://github.com/zkxmdkdltm/SocketProgramming/assets/102042061/0447a287-743f-40a8-9510-ee20a5ef46bc">
<br>-> write() 메소드로 out stream에 작성하고 flush()를 통해 Server로 메시지를 전송한다.

-	Server의 Response 메시지 수신 및 출력
 <img width="452" alt="image" src="https://github.com/zkxmdkdltm/SocketProgramming/assets/102042061/f4ceb660-6595-4bed-abc4-80ecb307934c">


-	소켓 및 input/output stream 종료
 <img width="96" alt="image" src="https://github.com/zkxmdkdltm/SocketProgramming/assets/102042061/d71f2b28-c0aa-4098-aeb0-745498e2688b">
<br>-> 통신이 끝난 후 close() 메소드를 통해 소켓과 input/output stream을 종료해준다

# 5.	클라이언트 Request 메시지 목록
Client request: GET
Server response: HTTP/1.1 200 OK
Client request: GET
Server response: HTTP/1.1 204 No content
Client request: GET
Server response: HTTP/1.1 200 OK
Client request: GET
Server response: HTTP/1.1 201 Created
Client request: GET
Server response: HTTP/1.1 404 Not found
Client request: 그 외의 메세지
Server response: HTTP/1.1 405 NotAllowed

# 6.	코드 실행 결과
 <img width="192" alt="image" src="https://github.com/zkxmdkdltm/SocketProgramming/assets/102042061/c52f4d85-72a2-47e1-b06f-270e35cb374d">
-> 서버 실행 후 클라이언트가 연결 요청 시 서버와 연결이 된 모습 (3-handshaking)
 
 <img width="452" alt="image" src="https://github.com/zkxmdkdltm/SocketProgramming/assets/102042061/e1b101ba-fdc4-43e5-b4b6-b2a2eb72ffdd">
-	GET: 응답코드 200 OK 
-	POST: 응답코드 204 No content 
-	HEAD: 응답코드 200 OK
-	PUT: 응답코드 201 Created
-	DELETE: 응답코드 404 Not found
-	그 외 메시지: 응답코드 405 NotAllowed

# 7.	Wireshark Protocol 확인 결과
<img width="452" alt="image" src="https://github.com/zkxmdkdltm/SocketProgramming/assets/102042061/b433b2d1-5246-4972-892a-a12fa27c6cbf">
서버와 클라이언트 소켓 통신을 했을 때 각 명령어 별로 HTTP 프로토콜에 따라 Request 메시지가 잘 전달된 것을 Wireshark 로 확인.
![image](https://github.com/zkxmdkdltm/SocketProgramming/assets/102042061/12e9d192-f32a-4956-ba4f-350fcb500e0f)
