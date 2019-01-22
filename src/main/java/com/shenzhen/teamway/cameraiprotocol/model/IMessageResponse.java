package com.shenzhen.teamway.cameraiprotocol.model;

import java.io.Serializable;

/**
 * @author : Gaven
 * @version V1.0
 * @Project: cameraiprotocol
 * @Package com.shenzhen.teamway.cameraiprotocol.model
 * @Description: 封装响应报文接口
 * @date Date : 2019年01月21日 11:58
 */
public interface IMessageResponse extends Serializable {
    /**
     * 取得包的长度
     *
     * @return
     */
    short getPackageLength();

    /**
     * 组包
     *
     * @return
     */
    byte[] getPackage();
}