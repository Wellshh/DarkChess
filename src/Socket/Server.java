package Socket;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.io.*;
import java.net.Socket;

import net.sf.json.JSONObject;

public class Server {

    private DataInputStream inputStream;

    ArrayList<User> userList = new ArrayList<>();

    static ServerSocket server;

    public Server() {
        try {
            //开启socket服务器，绑定端口7686
            server = new ServerSocket(7686);

            System.out.println("服务器准备就绪~");
            System.out.println("服务器信息： " + server.getInetAddress() + " P: " + server.getLocalPort());

            while(true) {
                Socket socket = server.accept();
                inputStream = new DataInputStream(socket.getInputStream());

                User user = new User();
                user.setSocket(socket);
                userList.add(user);

                for(User value : userList) {
                    System.out.println(value.getSocket());
                }

                ClientHandler clientHandler = new ClientHandler(socket);
                clientHandler.start();
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            new Server();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    class ClientHandler extends Thread {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            JSONObject info;
            System.out.println("新客户端连接： " + socket.getInetAddress() + " P: " + socket.getPort());

            try {
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                while(true) {
                    String input = inputStream.readUTF();
                    JSONObject in = JSONObject.fromObject(input);
                    info = new JSONObject();
                    int op = in.getInt("op");
                    if(op == 0) {
                        String s = in.getString("ini");
                        System.out.println(s);
                        info.put("op",0);
                        info.put("ini",s);
                        for(User value : userList) {
                            if(!value.getSocket().equals(socket)) {
                                sendInfo(value.getSocket(),info);
                            }
                        }
                    }
                    else {
                        int x = in.getInt("x");
                        int y = in.getInt("y");
                        info.put("op",1);
                        info.put("x",x);
                        info.put("y",y);
                        System.out.printf("%d %d\n",x,y);
                        for(User value : userList) {
                            if(!value.getSocket().equals(socket)) {
                                sendInfo(value.getSocket(),info);
                            }
                        }
                    }

                }
            } catch (IOException e) {
                System.out.println("连接异常断开");
            }
        }
    }

    public static void sendInfo(Socket userSocket, JSONObject info) {
        try {
            DataOutputStream outputStream = new DataOutputStream(userSocket.getOutputStream());
            outputStream.writeUTF(info.toString());
            System.out.println(userSocket);
        } catch(IOException e) {
            System.out.println(info.toString());
        }
    }
}
