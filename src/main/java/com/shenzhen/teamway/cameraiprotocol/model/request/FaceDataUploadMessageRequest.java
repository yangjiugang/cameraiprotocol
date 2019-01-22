package com.shenzhen.teamway.cameraiprotocol.model.request;

import com.shenzhen.teamway.cameraiprotocol.model.IMessageRequest;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author : Gaven
 * @version V1.0
 * @Project: cameraiprotocol
 * @Package com.shenzhen.teamway.cameraiprotocol.model.request
 * @Description: 封装人脸设别数据上传请求报文
 * @date Date : 2019年01月21日 17:31
 */
public class FaceDataUploadMessageRequest implements IMessageRequest {
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
     * 标志位
     * 其中：
     * resverd 长度12位 值：0
     * 数据来源 长度2位  值：00未知 01 超高频设备 10道闸
     * 进门/出门 长度1位   值: 1进门 2出门 默认取0
     * 实时数据标志 长度1位  值：默认取0
     * 长度 2字节
     */
    protected byte[] flag;

    /**
     * 设备id
     * 长度2字节
     */
    protected byte[] deviceId;

    /**
     * 记录时间
     * 长度4字节
     */
    protected byte[] recordTime;

    /**
     * 卡号
     * 长度20字节
     */
    protected byte[] cardNo;

    /**
     * 识别信息
     * 长度50字节
     */
    protected byte[] discernInfo;

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
        end = start + 10;
        this.sessionId = ArrayUtils.subarray(bytes, start, end);

        start = end;
        end = start + 2;
        this.flag = ArrayUtils.subarray(bytes, start, end);

        start = end;
        end = start + 2;
        this.deviceId = ArrayUtils.subarray(bytes, start, end);

        start = end;
        end = start + 4;
        this.recordTime = ArrayUtils.subarray(bytes, start, end);

        start = end;
        end = start + 20;
        this.cardNo = ArrayUtils.subarray(bytes, start, end);

        start = end;
        end = start + 50;
        this.discernInfo = ArrayUtils.subarray(bytes, start, end);

        start = end;
        end = start + 4;
        this.length = ArrayUtils.subarray(bytes, start, end);

        start = end;
        end = start + 2;
        this.packEnd = ArrayUtils.subarray(bytes, start, end);
    }
}