package com.bjjh.socket;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class MinaTimeServer {

    private static final int PORT = 5209;

    public static void main(String[] args) {
        try {
            //socket接收器
            IoAcceptor acceptor = new NioSocketAcceptor(1);
            //添加日志记录
            acceptor.getFilterChain().addLast("logger", new LoggingFilter());
            //添加编码解码器
            acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
            //添加处理器(用于接收数据后处理处理数据逻辑)
            acceptor.setHandler(new TimeServerHandler());
            //设置读取数据缓存单位byte
            acceptor.getSessionConfig().setReadBufferSize(2048);
            //设置多长时间后接收器开始空闲
            acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

            acceptor.bind(new InetSocketAddress(PORT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
