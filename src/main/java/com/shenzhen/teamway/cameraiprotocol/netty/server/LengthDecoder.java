package com.shenzhen.teamway.cameraiprotocol.netty.server;

import io.netty.buffer.*;
import io.netty.channel.*;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import org.slf4j.*;

 

public class LengthDecoder extends LengthFieldBasedFrameDecoder {
	private static final Logger LOG = LoggerFactory
			.getLogger(LengthDecoder.class);

	public LengthDecoder() {
		super(10485760, 0, 2, 0, 0);
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf in)
			throws Exception {

		byte[] bytes = null;

		try {

			ByteBuf frame = (ByteBuf) super.decode(ctx, in);

			if (frame == null) {

				return null;
			}

			bytes = new byte[frame.readableBytes()];
			frame.readBytes(bytes);

		} catch (Exception e) {
			LOG.error("", e);
		}

		return bytes;
	}

	@Override
	protected ByteBuf extractFrame(ChannelHandlerContext ctx, ByteBuf buffer,
			int index, int length) {
		return buffer.slice(index, length);
	}
}
