package com.shenzhen.teamway.cameraiprotocol.model.response;

import com.shenzhen.teamway.cameraiprotocol.model.IMessageResponse;
import com.shenzhen.teamway.cameraiprotocol.util.ByteUtil;

/**
 * 注册包响应
 */
public class RegisterMessageResponse implements IMessageResponse {

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
     * 心跳最大时间间隔
     * 长度 2字节
     */
    protected byte[] heartBeatInterval;
    /**
     * 短链接端口号
     * 长度 4字节
     */
    protected byte[] shortLinkPort;
    /**
     * 服务器当前时间
     * 长度 4字节
     */
    protected byte[] currentTime;
    /* -------------------参数域结束--------------------------------*/

    /**
     * 数据域长度 无数据域时 length 为 0x00000000
     * 长度4
     */
    protected byte[] length;
    /**
     * 包尾 规定：0X1616
     * 长度2
     */
    protected byte[] packEnd;

    @Override
    public short getPackageLength() {
        return (2 + 1 + 1 + 2 + 4 + 4 + 4 + 2);
    }

    @Override
    public byte[] getPackage() {
        return ByteUtil.merge(
                this.packHeader,
                this.protocal,
                this.functionID,
                this.heartBeatInterval,
                this.shortLinkPort,
                this.currentTime,
                this.length,
                this.packEnd
        );
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

    public byte[] getHeartBeatInterval() {
        return heartBeatInterval;
    }

    public void setHeartBeatInterval(byte[] heartBeatInterval) {
        this.heartBeatInterval = heartBeatInterval;
    }

    public byte[] getShortLinkPort() {
        return shortLinkPort;
    }

    public void setShortLinkPort(byte[] shortLinkPort) {
        this.shortLinkPort = shortLinkPort;
    }

    public byte[] getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(byte[] currentTime) {
        this.currentTime = currentTime;
    }

    public byte[] getLength() {
        return length;
    }

    public void setLength(byte[] length) {
        this.length = length;
    }

    public byte[] getPackEnd() {
        return packEnd;
    }

    public void setPackEnd(byte[] packEnd) {
        this.packEnd = packEnd;
    }
}