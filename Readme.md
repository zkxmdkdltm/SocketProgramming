# SocketProgramming

# ����
0. ���� �ڷ�
1. ����(Socket)�� ��������̶�
2. ������ Ŭ���̾�Ʈ
3. ���� ���� ����
4. Ŭ���̾�Ʈ ���� ����
5. Ŭ���̾�Ʈ GET/HEAD/POST/PUT Request ���

## 0. �����ڷ�
- https://javaplant.tistory.com/18
- https://www.youtube.com/watch?v=dX82Wuc18wk
- https://kadosholy.tistory.com/125

## 1. ����(Socket)�̶�
�����̶� ���μ����� ��Ʈ��ũ ����� �����͸� �������ų� Ȥ�� �����͸� �ޱ� ���� �������� â���������� ��������, IP�ּ�, ��Ʈ �ѹ��� ���ǵǸ� ���ҿ� ���� ���� ����, Ŭ���̾�Ʈ �������� ���еȴ�.

���ϰ� ����� ���� ��Ʈ��ũ�󿡼� Ŭ���̾�Ʈ�� ������ �ش�Ǵ� ��ǻ�͸� �ĺ��ϱ� ���� **IP�ּ�**�� �������α׷��� �ĺ��ϱ� ���� **��Ʈ��ȣ**�� ���ȴ�.

## 2. ������ Ŭ���̾�Ʈ
- **����** : Ŭ���̾�Ʈ�� ���� ���� ��û�� ����ϰ�, �����û�� ���� Ŭ���̾�Ʈ ������ �����Ͽ� ����� �����ϰ� �Ѵ�.
- **Ŭ���̾�Ʈ** : ������ ������ �ۼ����� �Ͼ�� �����̴�.

## 3. ���� ���� ����
����, ���� ����
```
ServerSocket server = null;
Socket socket = null;
```

������ �ۼ����� ���� input/output ��Ʈ�� ����
```
BufferedReader in = null;
BufferedWriter out = null;
```

Port ��ȣ�� ���� ���� �� Ŭ���̾�Ʈ ����
```
server = new ServerSocket(5555);
socket = server.accept();
```

input/output ��Ʈ�� ����
```
in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
```

Client�� ���� Request ����
```
String msg = in.readLine();
```

GET/HEAD/POST/PUT Request�� ���� Response�� Client�� ����
```
if(inMsg.equals("GET-���� 200")){
    out.write("HTTP/1.1 200 OK" + "\n");
} else if(inMsg.equals("GET-���� 404")){
    out.write("HTTP/1.1 404 Not Found" + "\n");
} else if(inMsg.equals("HEAD-���� 100")){
    out.write("HTTP/1.1 100 Continue" + "\n");
} else if(inMsg.equals("HEAD-���� 101")){
    out.write("HTTP/1.1 101 Switching Protocols" + "\n");
}
.
.
.
out.flush()
```

����, ���� �� input/output stream ����
```
in.close();
out.close();
socket.close();
server.close();
```

## 4. Ŭ���̾�Ʈ ���� ����

���� ���� (Ŭ���̾�Ʈ ������ ���������� �ʿ����)
```
Socket socket = null;
```

������ �ۼ����� ���� input/output ��Ʈ�� ����
```
BufferedReader in = null;
BufferedWriter out = null;
```

Port ��ȣ�� ���� ����
```
socket = new Socket("localhost", 5555);
```

input/output ��Ʈ�� ����
```
in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
```

Client�� Request �޽��� �Է� �� Server�� ����
```
System.out.print("�Է� (BYE �Է½� ����) >> ");
String outMsg = sc.nextLine();
out.write(outMsg + "\n");
out.flush();
```

Server�� Response �޽��� ���� �� ���
```
String inMsg = in.readLine();
System.out.println("���� >> : " + inMsg);
```

���Ϲ� input/output stream ����
```
in.close();
out.close();
socket.close();
```

## 5. Ŭ���̾�Ʈ GET/HEAD/POST/PUT Request �޽��� ���
<br>
Client request: GET-���� 200

Server response: HTTP/1.1 200 OK

<br>
Client request: GET-���� 404

Server response: HTTP/1.1 404 Not Found

<br>
Client request: HEAD-���� 100

Server response: HTTP/1.1 100 Continue

<br>
Client request: HEAD-���� 101

Server response: HTTP/1.1 101 Switching Protocols

<br>
Client request: POST-���� 500

Server response: HTTP/1.1 500 Internal Server Error

<br>
Client request: POST-���� 400

Server response: HTTP/1.1 400 Bad Request

<br>
Client request: PUT-���� 301

Server response: HTTP/1.1 301 Moved Permanently

<br>
Client request: GET-���� 200

Server response: HTTP/1.1 503 Service Unavailable
