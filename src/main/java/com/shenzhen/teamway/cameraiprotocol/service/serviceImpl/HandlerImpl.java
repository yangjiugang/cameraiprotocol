package com.shenzhen.teamway.cameraiprotocol.service.serviceImpl;

import com.shenzhen.teamway.cameraiprotocol.model.constant.ControlCodeConstant;
import com.shenzhen.teamway.cameraiprotocol.model.request.RegisterMessageRequest;
import com.shenzhen.teamway.cameraiprotocol.model.response.RegisterMessageResponse;
import com.shenzhen.teamway.cameraiprotocol.service.Handler;
import com.shenzhen.teamway.cameraiprotocol.util.ByteUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service public class HandlerImpl implements Handler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override public byte[] messageReceived(byte[] message) {
        logger.info("收到请求类型为:" + ByteUtil.bytesToHexString(ArrayUtils.subarray(message, 3, 4)) + "报文：" + ByteUtil
            .bytesToHexString(message));
        byte[] resultByte = null;
        //判断报文请求类型
        //1.注册 0x00
        if (Arrays.equals(ControlCodeConstant.REGISTER_REQUEST, ArrayUtils.subarray(message, 3, 4))) {
            RegisterMessageRequest registerMessageRequest = new RegisterMessageRequest();
            registerMessageRequest.toPackage(message);
            RegisterMessageResponse registerMessageResponse = new RegisterMessageResponse();
            registerMessageResponse.setPackHeader(new byte[] {0x68, 0x68});
            registerMessageResponse.setProtocal(new byte[] {0x02});
            registerMessageResponse.setFunctionID(new byte[] {0x00});
            registerMessageResponse.setHeartBeatInterval(new byte[] {0x00, 0x3c});
            registerMessageResponse.setShortLinkPort(new byte[] {});//4个字节端口号
            registerMessageResponse.setCurrentTime(new byte[] {});//服务器当前时间4个字节
            registerMessageResponse.setLength(new byte[] {0x00, 0x00, 0x00, 0x00});
            registerMessageResponse.setPackEnd(new byte[] {0x16, 0x16});
            resultByte = registerMessageResponse.getPackage();
        }

        return resultByte;
    }
}