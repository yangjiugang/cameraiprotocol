package com.shenzhen.teamway.cameraiprotocol.model.response;

import com.shenzhen.teamway.cameraiprotocol.model.IMessageResponse;
import com.shenzhen.teamway.cameraiprotocol.util.ByteUtil;

/**
 * @author : Gaven
 * @version V1.0
 * @Project: cameraiprotocol
 * @Package com.shenzhen.teamway.cameraiprotocol.model.response
 * @Description: 封装 人脸识别数据上传反馈报文
 * @date Date : 2019年01月21日 17:33
 */
public class FaceDataUploadFeedbackMessageResponse implements IMessageResponse {
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
     * 会话id ASCII码
     * 长度 10字节
     */
    protected byte[] sessionId;
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

    @Override public short getPackageLength() {
        return (2 + 1 + 1 + 10 + 1 + 4 + 2);
    }

    @Override public byte[] getPackage() {
        return ByteUtil
            .merge(this.packHeader, this.protocal, this.functionID, this.sessionId, this.feedBackStatus, this.length,
                this.packEnd);
    }
}