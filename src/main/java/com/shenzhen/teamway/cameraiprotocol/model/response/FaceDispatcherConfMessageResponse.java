package com.shenzhen.teamway.cameraiprotocol.model.response;

import com.shenzhen.teamway.cameraiprotocol.model.IMessageResponse;
import com.shenzhen.teamway.cameraiprotocol.util.ByteUtil;

/**
 * @author : Gaven
 * @version V1.0
 * @Project: cameraiprotocol
 * @Package com.shenzhen.teamway.cameraiprotocol.model.response
 * @Description: 封装人脸识别白名单下发设置 服务器响应报文
 * @date Date : 2019年01月21日 17:22
 */
public class FaceDispatcherConfMessageResponse implements IMessageResponse {

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
     * 保留扩展用 值0
     * 长度 13字节
     */
    protected byte[] resverd;
    /**
     * 增删改标志
     * 00：新增 01：删除 10：编辑
     * 长度 2字节
     */
    protected byte[] crudFlag;
    /**
     * 黑名单标志 取1
     * 0:黑名单 1：白名单
     * 长度 1字节
     */
    protected byte[] blackListFlag;

    /**
     * 有效期（起始时间）
     * 长度 4字节
     */
    protected byte[] effectiveStartTime;

    /**
     * 有效期（终止时间）
     * 长度4字节
     */
    protected byte[] effectiveEndTime;

    /**
     * 卡号
     * 长度 20字节
     */
    protected byte[] cardNO;

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

    @Override public short getPackageLength() {
        return (2 + 1 + 1 + 13 + 2 + 1 + 4 + 4 + 20 + 4 + 2);
    }

    @Override public byte[] getPackage() {
        return ByteUtil
            .merge(this.packHeader, this.protocal, this.functionID, this.resverd, this.crudFlag, this.blackListFlag,
                this.effectiveStartTime, this.effectiveEndTime, this.cardNO);
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

    public byte[] getResverd() {
        return resverd;
    }

    public void setResverd(byte[] resverd) {
        this.resverd = resverd;
    }

    public byte[] getCrudFlag() {
        return crudFlag;
    }

    public void setCrudFlag(byte[] crudFlag) {
        this.crudFlag = crudFlag;
    }

    public byte[] getBlackListFlag() {
        return blackListFlag;
    }

    public void setBlackListFlag(byte[] blackListFlag) {
        this.blackListFlag = blackListFlag;
    }

    public byte[] getEffectiveStartTime() {
        return effectiveStartTime;
    }

    public void setEffectiveStartTime(byte[] effectiveStartTime) {
        this.effectiveStartTime = effectiveStartTime;
    }

    public byte[] getEffectiveEndTime() {
        return effectiveEndTime;
    }

    public void setEffectiveEndTime(byte[] effectiveEndTime) {
        this.effectiveEndTime = effectiveEndTime;
    }

    public byte[] getCardNO() {
        return cardNO;
    }

    public void setCardNO(byte[] cardNO) {
        this.cardNO = cardNO;
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