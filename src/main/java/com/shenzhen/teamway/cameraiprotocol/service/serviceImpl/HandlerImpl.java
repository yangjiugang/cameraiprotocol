package com.shenzhen.teamway.cameraiprotocol.service.serviceImpl;

import com.shenzhen.teamway.cameraiprotocol.service.Handler;
import com.shenzhen.teamway.cameraiprotocol.util.ByteUtil;
import org.springframework.stereotype.Service;

@Service
public class HandlerImpl implements Handler {

    @Override
    public byte[] messageReceived(byte[] message) {
        byte[] b =  ByteUtil.getBytes("测试。。","utf-8");
        System.out.println(ByteUtil.bytesToHexString(b));
        return b;
    }
}