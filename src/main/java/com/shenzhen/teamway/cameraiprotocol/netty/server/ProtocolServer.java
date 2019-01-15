package com.shenzhen.teamway.cameraiprotocol.netty.server;

import com.shenzhen.teamway.cameraiprotocol.common.NamedThreadFactory;
import com.shenzhen.teamway.cameraiprotocol.server.Server;
import com.shenzhen.teamway.cameraiprotocol.util.ConfigInfo;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;

public class ProtocolServer implements Server {
    private static final Logger LOG = LoggerFactory.getLogger(ProtocolServer.class);

    private Channel channel = null;
    private EventLoopGroup bossGroup = null;
    private EventLoopGroup workerGroup = null;
    private AtomicBoolean startFlag = new AtomicBoolean(false);


    public static void main(String[] args) {

        try {
            System.setProperty("config", (new File(System.getProperty("user.dir"), "../config")).getCanonicalPath());
        } catch (IOException e) {
            System.exit(0);
            LOG.error("load properties key err", e);
        }
        Properties appPpt = ConfigInfo.getConfig();
        //启动spring环境
        new ClassPathXmlApplicationContext("applicationContext.xml");

        ProtocolServer ns = new ProtocolServer();

        LOG.debug("businessThreadNum:" + appPpt.getProperty("businessThreadNum"));
        try {
            ns.start(Integer.valueOf(appPpt.getProperty("port")), Integer.valueOf(appPpt.getProperty("bossThreadNum").trim()), Integer.valueOf(appPpt.getProperty("workerThreadNum").trim()), Integer.valueOf(appPpt.getProperty("businessThreadNum").trim()), "businessHandler");
//            ns.start(Integer.valueOf(appPpt.getProperty("port_count")), Integer.valueOf(appPpt.getProperty("bossThreadNum").trim()), Integer.valueOf(appPpt.getProperty("workerThreadNum").trim()), Integer.valueOf(appPpt.getProperty("businessThreadNum").trim()), "countBusinessHandler");
//            ns.start(Integer.valueOf(appPpt.getProperty("port_query")), Integer.valueOf(appPpt.getProperty("bossThreadNum").trim()), Integer.valueOf(appPpt.getProperty("workerThreadNum").trim()), Integer.valueOf(appPpt.getProperty("businessThreadNum").trim()), "queryOrderBusinessHandler");
        } catch (Exception e) {
            LOG.error("", e);
            if (ns != null)
                ns.stop();
        }
    }

    public void start(int listenPort, final int bossThreadNum, final int workerThreadNum, final int businessThreadNum, String service) throws Exception {

        ThreadFactory bossFactory = new NamedThreadFactory("服务boss端口" +listenPort+"线程数"+ bossThreadNum + "-");
        bossGroup = new NioEventLoopGroup(bossThreadNum,bossFactory);
        ThreadFactory workFactory = new NamedThreadFactory("服务work端口" +listenPort+"线程数"+ workerThreadNum + "-");
        workerGroup = new NioEventLoopGroup(workerThreadNum,workFactory);
        final ThreadFactory serverBusinessTF = new NamedThreadFactory("NETTYSERVER-BUSINESS-"+businessThreadNum+"-");

        ServerBootstrap bootstrap = new ServerBootstrap();
        // businessThread池在NettyServerChannelInitializer中开启

        bootstrap.group(bossGroup, workerGroup)
                .option(ChannelOption.SO_BACKLOG, 1000)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_SNDBUF, 1048576 * 20)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        bootstrap.childHandler(new NettyServerChannelInitializer(service, Executors.newFixedThreadPool(businessThreadNum, serverBusinessTF)));
        channel = bootstrap.bind(new InetSocketAddress(listenPort)).sync().channel();
        LOG.info("Server started,listen at: " + listenPort);
    }

    public void stop(){
        LOG.warn("Server stop!");
        channel.close();
        if(bossGroup != null && !bossGroup.isShutdown()){
            bossGroup.shutdownGracefully();
        }
        if (workerGroup != null && !workerGroup.isShutdown()) {
            workerGroup.shutdownGracefully();
        }
        startFlag.set(false);
    }
}