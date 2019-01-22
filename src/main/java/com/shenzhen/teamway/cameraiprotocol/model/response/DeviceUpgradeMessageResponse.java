package com.shenzhen.teamway.cameraiprotocol.model.response;

import com.shenzhen.teamway.cameraiprotocol.model.IMessageResponse;
import com.shenzhen.teamway.cameraiprotocol.util.ByteUtil;
import com.shenzhen.teamway.cameraiprotocol.util.ConfigInfo;

/**
 * @author : Gaven
 * @version V1.0
 * @Project: cameraiprotocol
 * @Package com.shenzhen.teamway.cameraiprotocol.model.response
 * @Description: 封装设备升级响应报文
 * @date Date : 2019年01月21日 17:07
 */
public class DeviceUpgradeMessageResponse implements IMessageResponse {
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

    /**
     * 数据域长度 无数据域时 length 为 0x00000000
     * 长度4
     */
    protected byte[] length;
    /**
     * 数据域
     */
    protected byte[] descriptor = ConfigInfo.getConfig().getProperty("deviceUpgrade").getBytes();
    /**
     * 包尾 规定：0X1616
     * 长度2
     */
    protected byte[] packEnd;

    /**
     * 获取包的长度
     *
     * @return
     */
    @Override public short getPackageLength() {
        return (short)(2 + 1 + 1 + 4 + descriptor.length + 2);
    }

    @Override public byte[] getPackage() {
        return ByteUtil.merge(this.packHeader, this.protocal, this.functionID, this.length, this.descriptor, this.packEnd);
    }

    public byte[] getPackHeader() {
        return packHeader;
    }

    public void setPackHeader(byte[] packHeader) {
        this.packHeader = packHeader;
    }

    public byte[] getProtocal() {
        return protocal;
    }

    public void setProtocal(byte[] protocal) {
        this.protocal = protocal;
    }

    public byte[] getFunctionID() {
        return functionID;
    }

    public void setFunctionID(byte[] functionID) {
        this.functionID = functionID;
    }

    public byte[] getLength() {
        return length;
    }

    public void setLength(byte[] length) {
        this.length = length;
    }

    public byte[] getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(byte[] descriptor) {
        this.descriptor = descriptor;
    }

    public byte[] getPackEnd() {
        return packEnd;
    }

    public void setPackEnd(byte[] packEnd) {
        this.packEnd = packEnd;
    }
}