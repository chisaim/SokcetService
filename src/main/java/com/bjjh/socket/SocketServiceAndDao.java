package com.bjjh.socket;


import com.sun.jmx.snmp.tasks.Task;
import com.sun.xml.internal.bind.v2.model.annotation.RuntimeAnnotationReader;
import org.apache.log4j.net.SocketServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 */
public class SocketServiceAndDao {


    public static void main(String[] args) {
        SocketServiceAndDao socketServer = new SocketServiceAndDao();
        socketServer.startServer();
    }

    public void startServer() {
        ServerSocket serverSocket = null;
        BufferedReader reader = null;
        Socket socket = null;
        try {
            // 端口号只要不冲突就行
            serverSocket = new ServerSocket(5209);
            System.out.println("server started..");
            // 进入阻塞状态，等待客户端接入
            socket = serverSocket.accept();
            System.out.println("client " + socket.hashCode() + " connected");
            // 从socket中读取数据
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String receiveMsg;
            while ((receiveMsg = reader.readLine()) != null) { // 以"\n"结束
                System.out.println(receiveMsg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
