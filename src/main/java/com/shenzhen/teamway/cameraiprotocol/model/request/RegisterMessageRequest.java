package com.shenzhen.teamway.cameraiprotocol.model.request;

import com.shenzhen.teamway.cameraiprotocol.model.IMessageRequest;
import com.shenzhen.teamway.cameraiprotocol.util.ByteUtil;
import org.apache.commons.lang3.ArrayUtils;

/**
 * 注册请求报文格式
 */
public class RegisterMessageRequest implements IMessageRequest {
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
     * 设备id
     * 长度2字节
     */
    protected byte[] deviceId;
    /**
     * 硬件版本号
     * 长度 2字节
     */
    protected byte[] deviceVersion;
    /**
     * 软件版本号
     * 长度2字节
     */
    protected byte[] softwareVersion;
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

    /**
     * 获取包的长度
     *
     * @return
     */
    public short getPackageLength() {
        return (short)(2 + 1 + 1 + 2 + 2 + 2 + 4 + 2);
    }

    /**
     * 解包
     *
     * @param bytes
     * @return
     */
    public void toPackage(byte[] bytes) {
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
        end = start + 2;
        this.deviceId = ArrayUtils.subarray(bytes, start, end);

        start = end;
        end = start + 2;
        this.deviceVersion = ArrayUtils.subarray(bytes, start, end);

        start = end;
        end = start + 2;
        this.softwareVersion = ArrayUtils.subarray(bytes, start, end);

        start = end;
        end = start + 4;
        this.length = ArrayUtils.subarray(bytes, start, end);

        start = end;
        end = start + 2;
        this.packEnd = ArrayUtils.subarray(bytes, start, end);

    }

    /**
     * 组包
     *
     * @return
     */
    public byte[] getPackage() {
        return ByteUtil.merge(this.packHeader, this.protocal, this.functionID, this.deviceId, this.deviceVersion,
            this.softwareVersion, this.length, this.packEnd);
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

    public byte[] getPackEnd() {
        return packEnd;
    }

    public void setPackEnd(byte[] packEnd) {
        this.packEnd = packEnd;
    }

    public byte[] getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(byte[] deviceId) {
        this.deviceId = deviceId;
    }

    public byte[] getDeviceVersion() {
        return deviceVersion;
    }

    public void setDeviceVersion(byte[] deviceVersion) {
        this.deviceVersion = deviceVersion;
    }

    public byte[] getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(byte[] softwareVersion) {
        this.softwareVersion = softwareVersion;
    }
}