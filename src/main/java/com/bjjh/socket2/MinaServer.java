package com.bjjh.socket2;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MinaServer {

    public static Logger log = Logger.getLogger(MinaServer.class);

    public static void main(String[] args) throws Exception {
        log.info("------------------------");
        NioSocketAcceptor acceptor = new NioSocketAcceptor();
        acceptor.setHandler(new ServerHandler());
        acceptor.getFilterChain().addLast("mina", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("utf-8"))));
        acceptor.bind(new InetSocketAddress(9999));
    }
}
