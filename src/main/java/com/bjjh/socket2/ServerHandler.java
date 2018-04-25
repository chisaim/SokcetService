package com.bjjh.socket2;

import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.session.IoSessionConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerHandler extends IoHandlerAdapter {

    public static Logger log = LoggerFactory.getLogger(ServerHandler.class);

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        log.error("", cause);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String clientIP = (String) session.getAttribute("KEY_SESSION_CLIENT_IP");
        log.info("" + clientIP + " - " + message);
//    	String retStr = (String) message;
//    	session.write(retStr.toUpperCase());
        session.closeNow();
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
//        System.out.println("MinaServerHandler---messageSent:"+message.toString());  
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        String clientIP = ((InetSocketAddress) session.getRemoteAddress()).getAddress().getHostAddress();
        session.setAttribute("KEY_SESSION_CLIENT_IP", clientIP);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
//        session.closeNow();
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        IoSessionConfig sessionConfig = session.getConfig();
        sessionConfig.setIdleTime(IdleStatus.READER_IDLE, 10);
    }
}
