package com.shenzhen.teamway.cameraiprotocol.model.response;

import com.shenzhen.teamway.cameraiprotocol.model.IMessageResponse;
import com.shenzhen.teamway.cameraiprotocol.util.ByteUtil;

/**
 * @author : Gaven
 * @version V1.0
 * @Project: cameraiprotocol
 * @Package com.shenzhen.teamway.cameraiprotocol.model.response
 * @Description: 封装配置设备参数响应报文
 * @date Date : 2019年01月21日 18:26
 */
public class ConfDeviceParamMessageReponse implements IMessageResponse {
    /**
     * 帧起始符 规定：0x6868
     * 长度2
     */
    protected byte[] packHeader;
    /**
     * 协议版本号 规定：0x02
     * 长度1
     */
    protected byte[] protocal;
    /**
     * 系统通讯的功能码
     * 长度1
     */
    protected byte[] functionID;

    /* -------------------参数域开始--------------------------------*/
    /**
     * 与服务器的连接标识 1：ip地址连接 2：域名连接
     * 长度 1
     */
    protected byte[] connectFlag;

    /**
     * 服务器通讯地址数据长度
     * 长度 2字节
     */
    protected byte[] connnectAddressDataLength;

    /**
     * 服务器通讯地址 域名或ip
     * 长度50字节
     */
    protected byte[] connectAddress;

    /**
     * 服务器连接端口号
     * 长度4字节
     */
    protected byte[] connectPort;

    /**
     * 设别唯一id
     * 长度2字节
     */
    protected byte[] deviceId;

    /* -------------------参数域结束--------------------------------*/

    /**
     * 数据域长度 无数据域时 length 为 0x00000000
     * 长度4字节
     */
    protected byte[] length;
    /**
     * 包尾 规定：0X1616
     * 长度2字节
     */
    protected byte[] packEnd;

    @Override public short getPackageLength() {
        return (2 + 1 + 1 + 1 + 2 + 50 + 4 + 2 + 4 + 2);
    }

    @Override public byte[] getPackage() {
        return ByteUtil
            .merge(this.packHeader, this.protocal, this.functionID, this.connectFlag, this.connnectAddressDataLength,
                this.connectAddress, this.connectPort, this.deviceId, this.length, this.packEnd);
    }
}