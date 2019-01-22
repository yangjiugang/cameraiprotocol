package com.shenzhen.teamway.cameraiprotocol.model.request;

import com.shenzhen.teamway.cameraiprotocol.model.IMessageRequest;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author : Gaven
 * @version V1.0
 * @Project: cameraiprotocol
 * @Package com.shenzhen.teamway.cameraiprotocol.model.request
 * @Description: 心跳检测请求报文
 * @date Date : 2019年01月21日 15:52
 */
public class HeartBeatMessageRequest implements IMessageRequest {
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
     * 包尾 规定：0X1616
     * 长度2
     */
    protected byte[] packEnd;

    /**
     * 解包
     *
     * @return
     */
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
        end = start + 4;
        this.length = ArrayUtils.subarray(bytes, start, end);

        start = end;
        end = start + 2;
        this.packEnd = ArrayUtils.subarray(bytes, start, end);
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
}