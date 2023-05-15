//package Socket;
//
//import java.net.Inet4Address;
//import java.net.InetSocketAddress;
//import java.net.ServerSocket;
//import java.util.ArrayList;
//import java.io.*;
//import java.net.Socket;
//import java.util.List;
//
////import net.sf.json.JSONObject;
//
//import controller.*;
//import chessComponent.*;
//import view.Chessboard;
//
//import javax.xml.crypto.Data;
//
//public class Client {
//
//    static Socket socket;
//
//    Chessboard chessboard;
//
//    public Client(Chessboard chessboard) {
//        this.chessboard = chessboard;
//        try {
//            socket = new Socket("10.27.48.120",7686);
//
//
//            //socket.connect(new InetSocketAddress("172.20.10.5",7686));
//
//            System.out.println("已发起服务器连接，并进入后续流程~");
//            System.out.println("客户端信息： " + socket.getLocalAddress() + " P:" + socket.getLocalPort());
//            System.out.println("服务器信息： " + socket.getInetAddress() + " P:" + socket.getPort());
//
//            UserThread userThread = new UserThread();
//            userThread.start();
//
//        } catch(IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
////    public static void sendInfo(JSONObject info) {
////        try {
////            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
////            outputStream.writeUTF(info.toString());
////            System.out.println("Finish");
////        } catch (IOException e) {
////            System.out.println(info.toString());
////        }
////    }
//
//    class UserThread extends Thread{
//
//        @Override
//        public void run() {
//            try {
//                while(true) {
//                    DataInputStream inputStream = new DataInputStream(socket.getInputStream());
//                    String infoRead = inputStream.readUTF();
////                    JSONObject info = JSONObject.fromObject(infoRead);
////                    int op = info.getInt("op");
//                    System.out.println(op);
//                    if(op == 0) {
//                        List<String> line = new ArrayList<>();
//                        String s = info.getString("ini");
//                        s = s.replace(" ", "");
//                        s = s.replace("[", "");
//                        s = s.replace("]", "");
//                        s = s.replace("\"","");
//                        String[] data = s.split(",");
//                        int cnt = 0;
//                        for (int m = 0; m < 8; m++) {
//                            StringBuilder sb = new StringBuilder();
//                            for (int n = 0; n < 4; n++) {
//                                sb.append(data[cnt]).append(",");
//                                cnt++;
//                            }
//                            sb.setLength(sb.length() - 1);
//                            line.add(sb.toString());
//                        }
//                        line.add(data[32]);
//                        line.add(data[33]);
//                        line.add(data[34]);
//                        line.add(data[35]);
//                        line.add(data[36]);
//                        line.add(data[37]);
//                        line.add(data[38]);
//                        line.add(data[39]);
//                        line.add(data[40]);
//                        line.add(data[41]);
//                        line.add(data[42]);
//                        line.add(data[43]);
//                        line.add(data[44]);
//                        line.add(data[45]);
//                        line.add(data[46]);
//                        line.add(data[47]);
//                        line.add(data[48]);
//                        line.add(data[49]);
//                        line.add(data[50]);
//                        line.add(data[51]);
//                        chessboard.loadGame(line);
//                    }
//                    else {
//                        int x = info.getInt("x");
//                        int y = info.getInt("y");
//                        SquareComponent chess = chessboard.getChessComponents()[x][y];
//                        System.out.printf("%d %d\n", x, y);
//                        chessboard.clickController.onClick(chess);
//                    }
//                }
//            } catch(IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//}
