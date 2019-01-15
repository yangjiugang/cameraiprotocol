package com.shenzhen.teamway.cameraiprotocol.service;

/**
 *收到报文格式
 */
public interface Handler {
    byte[] messageReceived(byte[] message);
}
