package com.shenzhen.teamway.cameraiprotocol.model;

import com.shenzhen.teamway.cameraiprotocol.util.ByteUtil;

/**
 * @author gaven
 * @version 1.0
 * @Package com.shenzhen.teamway.controller
 * @Description:包协议实体类 字节传输方式 小端模式
 * @date 2019-01-10
 * @Copyright: 2018 teamway EC Inc. All rights reserved.
 * @since 1.0
 */
public class PackageData {
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
     * 参数域根据功能码的不同定义 长度不固定
     * 长度 n
     */
    protected byte[] parameter;
    /**
     * 数据域长度 无数据域时 length 为 0x00000000
     * 长度4
     */
    protected byte[] length;
    /**
     * 数据域 DATA
     * 长度n
     */
    protected byte[] descriptor;
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
        return (short) getPackage().length;
    }

    /**
     * 组包
     *
     * @return
     */
    public byte[] getPackage() {

        return ByteUtil.merge(
                this.packHeader,
                this.protocal,
                this.functionID,
                this.parameter,
                this.length,
                this.descriptor,
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

    public byte[] getParameter() {
        return parameter;
    }

    public void setParameter(byte[] parameter) {
        this.parameter = parameter;
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