package com.shenzhen.teamway.cameraiprotocol.model.request;

import com.shenzhen.teamway.cameraiprotocol.model.IMessageRequest;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author : Gaven
 * @version V1.0
 * @Project: cameraiprotocol
 * @Package com.shenzhen.teamway.cameraiprotocol.model.request
 * @Description: 封装 人脸白名单下发设置反馈 请求报文
 * @date Date : 2019年01月21日 17:26
 */
public class FaceDispatcherConfMessageRequest implements IMessageRequest {
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
     * 卡号
     * 长度 20字节
     */
    protected byte[] cardNO;

    /**
     * 反馈状态
     * 长度 1字节
     */
    protected byte[] feedBackStatus;

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
        end = start + 20;
        this.cardNO = ArrayUtils.subarray(bytes, start, end);

        start = end;
        end = start + 1;
        this.feedBackStatus = ArrayUtils.subarray(bytes, start, end);

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

    public byte[] getCardNO() {
        return cardNO;
    }

    public void setCardNO(byte[] cardNO) {
        this.cardNO = cardNO;
    }

    public byte[] getFeedBackStatus() {
        return feedBackStatus;
    }

    public void setFeedBackStatus(byte[] feedBackStatus) {
        this.feedBackStatus = feedBackStatus;
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