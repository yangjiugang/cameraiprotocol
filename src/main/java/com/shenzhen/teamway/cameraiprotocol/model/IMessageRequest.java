package com.shenzhen.teamway.cameraiprotocol.model;

import java.io.Serializable;

/**
 * @author : Gaven
 * @version V1.0
 * @Project: cameraiprotocol
 * @Package com.shenzhen.teamway.cameraiprotocol.model
 * @Description: 请求报文接口
 * @date Date : 2019年01月21日 11:57
 */
public interface IMessageRequest extends Serializable {
    /**
     * 解包
     * @param bytes
     * @return
     */
    void toPackage(byte[] bytes);
}