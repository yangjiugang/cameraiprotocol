package com.shenzhen.teamway.cameraiprotocol.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;

public class NettyServerChannelInitializer extends
        ChannelInitializer<SocketChannel> {

    private Logger LOG = LoggerFactory.getLogger(NettyServerChannelInitializer.class);

    private String service;
    private ExecutorService threadPool;

    public NettyServerChannelInitializer(String service,ExecutorService threadPool) {

        this.service = service;
        this.threadPool=threadPool;
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        InetSocketAddress i = ch.remoteAddress();
        LOG.debug("[客户端IP:" + i.getAddress().getHostAddress() + "][端口:" + i.getPort() + "]");

        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("decoder", new LengthDecoder());
        pipeline.addLast("handler", new NettyServerHandler(service, threadPool));
    }
}