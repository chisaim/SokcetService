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

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(5209);
        Socket client = server.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

        while (true) {
            PrintWriter out = new PrintWriter(client.getOutputStream());
            String str = in.readLine();
            System.out.println(str);
            out.println("has receive....");
            out.flush();
            if ("end".equals(str)) break;
        }
        in.close();
        client.close();
        server.close();
    }
}
