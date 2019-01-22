package com.shenzhen.teamway.cameraiprotocol.model.response;

import com.shenzhen.teamway.cameraiprotocol.model.IMessageResponse;
import com.shenzhen.teamway.cameraiprotocol.util.ByteUtil;

/**
 * @author : Gaven
 * @version V1.0
 * @Project: cameraiprotocol
 * @Package com.shenzhen.teamway.cameraiprotocol.model.response
 * @Description: 封装LED 发布下发报文
 * @date Date : 2019年01月21日 17:12
 */
public class LedPublishMessageResponse implements IMessageResponse {

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
     * 参数域根据功能码的不同定义 长度不固定
     * 长度 n
     *   protected byte[] parameter;
     */

    /**
     * Led 控制卡目标ip
     * 长度4字节
     */
    protected byte[] LedCardTargetIp;
    /**
     * 通信端口
     * 长度 2字节
     */
    protected byte[] communicationPort;
    /**
     * 转发方式
     * 长度2字节
     */
    protected byte[] forwardPattern;
    /* -------------------参数域结束--------------------------------*/
    /**
     * 数据域长度 无数据域时 length 为 0x00000000
     * 长度4
     */
    protected byte[] length;
    /**
     * 数据域
     * 长度n字节
     */
    protected byte[] descriptor;
    /**
     * 包尾 规定：0X1616
     * 长度2
     */
    protected byte[] packEnd;

    @Override public short getPackageLength() {
        return (2 + 1 + 1 + 4 + 2 + 2 + 4 + 2);//需要确定转发数据域内容
    }

    @Override public byte[] getPackage() {
        return ByteUtil
            .merge(this.packHeader, this.protocal, this.functionID, this.LedCardTargetIp, this.communicationPort,
                this.forwardPattern, this.length, this.descriptor, this.packEnd);
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

    public byte[] getLedCardTargetIp() {
        return LedCardTargetIp;
    }

    public void setLedCardTargetIp(byte[] ledCardTargetIp) {
        LedCardTargetIp = ledCardTargetIp;
    }

    public byte[] getCommunicationPort() {
        return communicationPort;
    }

    public void setCommunicationPort(byte[] communicationPort) {
        this.communicationPort = communicationPort;
    }

    public byte[] getForwardPattern() {
        return forwardPattern;
    }

    public void setForwardPattern(byte[] forwardPattern) {
        this.forwardPattern = forwardPattern;
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