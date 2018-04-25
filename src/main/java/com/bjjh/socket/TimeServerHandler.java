package com.bjjh.socket;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.logging.LoggingFilter;

import java.util.Date;

public class TimeServerHandler extends IoHandlerAdapter {

    private Logger logger = Logger.getLogger(TimeServerHandler.class);

    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
    }

    public void messageReceived(IoSession session, Object message) throws Exception {
        String mess = message.toString();

        System.out.println("message ----------->>"+mess);
        Date date = new Date();
        session.write( date.toString() );
    }

    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        System.out.println( "IDLE " + session.getIdleCount( status ));
    }

}
