package com.shenzhen.teamway.cameraiprotocol.model.constant;

public class ControlCodeConstant {
    /**
     * 注册请求
     */
    public static final byte[] REGISTER_REQUEST = new byte[]{0x00};
    /**
     * 注册响应
     */
    public static final byte[] REGISTER_RESPONSE = new byte[]{0x00};
    /**
     * 心跳请求
     */
    public static final byte[] HEART_BEAT_REQUEST = new byte[]{(byte)0x01};
    /**
     * 心跳响应
     */
    public static final byte[] HEART_BEAT_RESPONSE = new byte[]{(byte)0x01};
    /**
     * 设备升级请求
     */
    public static final byte[] DEVICE_UPGRADE = new byte[]{(byte)0x02};
    /**
     * LED 发布
     */
    public static final byte[] LED_PUBLISH = new byte[]{(byte)0x07};
    /**
     * 设备重启
     */
    public static final byte[] DEVICE_REBOOT = new byte[]{(byte)0x0a};
    /**
     * 人脸白名单下发配置
     */
    public static final byte[] FACE_DISPATCHER_CONF = new byte[]{(byte)0x0b};
    /**
     * 人脸白名单下发配置反馈
     */
    public static final byte[] FACE_DISPATCHER_FEEDBACK = new byte[]{(byte)0x0b};
    /**
     * 人脸识别数据上传
     */
    public static final byte[] FACE_DATA_UPLOAD = new byte[]{(byte)0x03};
    /**
     * 人脸识别数据上传反馈
     */
    public static final byte[] FACE_DATA_UPLOAD_FEEDBACK = new byte[]{(byte)0x03};
    /**
     * 读取设备参数配置
     */
    public static final byte[] INIT_DEVICE_CONF = new byte[]{(byte) 0xc0};
    /**
     * 设备参数配置上传
     */
    public static final byte[] DEVICE_CONF_UPLOAD = new byte[]{(byte)0xc0};
    /**
     * 配置设备参数
     */
    public static final byte[] CONF_DEVICE_PARAM = new byte[]{(byte)0xc1};
}