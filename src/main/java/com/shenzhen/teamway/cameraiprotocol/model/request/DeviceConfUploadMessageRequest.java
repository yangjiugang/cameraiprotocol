package com.shenzhen.teamway.cameraiprotocol.model.request;

import com.shenzhen.teamway.cameraiprotocol.model.IMessageRequest;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author : Gaven
 * @version V1.0
 * @Project: cameraiprotocol
 * @Package com.shenzhen.teamway.cameraiprotocol.model.request
 * @Description: 封装设备参数配置上传请求报文
 * @date Date : 2019年01月21日 17:38
 */
public class DeviceConfUploadMessageRequest implements IMessageRequest {
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

    @Override public void toPackage(byte[] bytes) {
        int start = 0;
        int end = 2;
        this.packHeader = ArrayUtils.subarray(bytes, start, end);

        start = end;
        end = start + 1;
        this.protocal = ArrayUtils.subarray(bytes, start, end);

        start = end;
        end = start + 1;
        this.functionID = ArrayUtils.subarray(bytes, start, end);

        start = end;
        end = start + 1;
        this.connectFlag = ArrayUtils.subarray(bytes, start, end);

        start = end;
        end = start + 2;
        this.connnectAddressDataLength = ArrayUtils.subarray(bytes, start, end);

        start = end;
        end = start + 50;
        this.connectAddress = ArrayUtils.subarray(bytes, start, end);

        start = end;
        end = start + 4;
        this.connectPort = ArrayUtils.subarray(bytes, start, end);

        start = end;
        end = start + 2;
        this.deviceId = ArrayUtils.subarray(bytes, start, end);

        start = end;
        end = start + 4;
        this.length = ArrayUtils.subarray(bytes, start, end);

        start = end;
        end = start + 2;
        this.packEnd = ArrayUtils.subarray(bytes, start, end);
    }
}