package com.shenzhen.teamway.cameraiprotocol.netty.server;

import com.shenzhen.teamway.cameraiprotocol.common.SpringContextHolder;
import com.shenzhen.teamway.cameraiprotocol.service.Handler;
import com.shenzhen.teamway.cameraiprotocol.util.ByteUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(NettyServerHandler.class);

    private String service;

    private ExecutorService threadPool;

    public NettyServerHandler(String service, ExecutorService threadPool) {
        this.service = service;
        this.threadPool = threadPool;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object object) throws Exception {
        threadPool.execute(new HandlerRunnable(ctx, object, this));
    }

    class HandlerRunnable implements Runnable {
        private ChannelHandlerContext ctx;
        private Object imessage;
        private NettyServerHandler hsh;

        public HandlerRunnable(ChannelHandlerContext ctx, Object message, NettyServerHandler hsh) {
            this.ctx = ctx;
            this.imessage = message;
            this.hsh = hsh;

        }

        public void run() {
            byte[] message = (byte[]) imessage;
            // 业务处理
            Handler bh = SpringContextHolder.getBean(service);
            byte[] returnByte = new byte[0];
            if(service.endsWith("handlerImpl")){
                bh.messageReceived(message);
            }
           /* if (service.endsWith("businessHandler")) {
                returnByte = this.hsh.businessHandler(bh, message);
            } else if (service.equals("countBusinessHandler")) {
                returnByte = bh.messageReceived(message);
            } else if (service.equals("queryOrderBusinessHandler")) {
                returnByte = bh.messageReceived(message);
            }*/

            if (Arrays.equals(ArrayUtils.subarray(returnByte, 30, 34), ByteUtil.codeBCD("00010001"))) {
                LOG.debug("ip:[" + this.ctx.channel().remoteAddress().toString());
                this.ctx.channel().close();
            }

            if (returnByte != null) {
                ByteBuf byteBuf = ctx.alloc().heapBuffer(returnByte.length);
                byteBuf.writeBytes(returnByte);
                ChannelFuture wf = ctx.channel().writeAndFlush(byteBuf);
                wf.addListener(new ChannelFutureListener() {
                    public void operationComplete(ChannelFuture future) {
                        if (!future.isSuccess()) {
                            LOG.error("constant handle write back message: ");
                        } else {
                            LOG.debug("write back sucess {}", future.channel().remoteAddress());
                        }
                    }
                });
            }
        }
    }

}
