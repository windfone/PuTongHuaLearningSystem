package com.fifedu.record.media.speech;

 
 
public interface SpeechError {
    /** MSP错误码基值 */
    public static final int ERROR_MSP_BASE  = 10100;    
    /** 网络超时 */
    public static final int ERROR_MSP_TIMEOUT = 10114;
    /** MSP没有语音数据 */
    public static final int ERROR_MSP_NO_DATA = 10118;
    
    /** 域名不合法或者DNS 服务器出错 */
    public static final int ERROR_MSP_DNS = 10214;
    /** HTTP错误,服务器不可访问 */
    public static final int ERROR_MSP_HTTP_BASE = 12000;
    /** 用户不存在 */
    public static final int ERROR_MSP_NO_USER = 13000;
    

    
    /** 应用自定义的错误基值 */
    public static final int ERROR_BASE = 800000;
    /** 录音器错误     */
    public static final int ERROR_RECODER = 800001;
    /** SID(会话ID) 不存在 */
    public static final int ERROR_NOT_SID = 800002;
    /** 没有识别结果 */
    public static final int ERROR_NO_MATCH = 800003;
    /** 网络超时    */
    public static final int ERROR_NET_TIMEOUT = 800004;  
    /** 识别初始化中   */
    public static final int ERROR_SPEECH_INIT = 800005;
    /** 网络引擎错误 */
    public static final int ERROR_SERVER_AUTHO = 800006;
    /** 其他状态错误 */
    public static final int ERROR_STATUS = 800007;    
    /** 网络错误  */
    public static final int ERROR_NETWORK = 800008;      
    /** 服务状态错误 */
    public static final int ERROR_SERVER_EXPECTION = 800009;
    /**  没有语音输入 */ 
    public static final int ERROR_NO_DATA = 800010;  
    /**  本地引擎忙 */ 
    public static final int ERROR_AITALK_BUSY = 800011; 
    /**  语音超时 */
    public static final int ERROR_SPEECH_TIMEOUT = 800012;
    /**  反应超时 */
    public static final int ERROR_RESPONSE_TIMEOUT = 800013; 
    /**  本地引擎异常,无效的识别场景 */
    public static final int ERROR_AITALK = 800014;
    
    /**  本地引擎无资源 */
    public static final int ERROR_AITALK_RES = 800016; 
    /**  查询不到联系人 */
    public static final int ERROR_QUERY_CONTACT = 800017;

    /**  无效识别参数  */
    public static final int ERROR_RECO_PARAM = 800018;
    /**  无效网络识别参数  */
    public static final int ERROR_MSC_PARAM = 800019;
    /**  无效本地识别参数  */
    public static final int ERROR_AITALK_PARAM = 800020;
    
    /** 无效的MSC结果 */    
    public static final int ERROR_MSC_RESULT = 800021;
    /** 没有MSC识别结果 */
    public static final int ERROR_MSC_NO_RESULT = 800022;
    /** 网络返回的联系人不存在 */
    public static final int ERROR_CONTACT_NOT_EXIST = 800023;
    /** 网络TTS超时，超时时间由本地控制 */
    public static final int ERROR_MSC_TTS_TIME_OUT = 800024;
    
    /** 没有OSSP返回的UID信息 */
    public static final int ERROR_NO_OSSP_UID = 800025;
    /** 识别过程中取消识别 */
    public static final int ERROR_CANCEL_RECO = 800026;
    /** 识别结果和本地不匹配 */
    public static final int ERROR_NO_FILTER_RESULT = 800027;
    
    /** 唤醒引擎运行中 */
    public static final int ERROR_IVW_BUSY = 800030;
    /** 唤醒引擎未初始化 */
    public static final int ERROR_IVW_UNINIT = 800031;
    /** 唤醒被打断 */
    public static final int ERROR_IVW_INTERRUPT = 800032;
    
    /** 本地合成内部错误 */
    public static final int ERROR_AISOUND = 800040;
    /** 本地合成无资源 */
    public static final int ERROR_AISOUND_NO_RES = 800041;
    /** 本地合成未初始化 */
    public static final int ERROR_AISOUND_NO_INIT = 800042;
    /** 本地合成参数错误 */
    public static final int ERROR_AISOUND_PARAM = 800043;
    
    
    
    /** 与Android输入法保持一致的错误定义*/
    
    /** 语音识别对象为空 */
    public static final int ASRRECOGNIZER_IS_NULL = 801005;
    
    /** 添加处理事件到消息队列失败 */
    public static final int ADD_MESSAGE_FAILE = 801006;
    
    /** 识别对象内部状态错误 */
    public static final int ASRRECOGNIZER_STATES_WRONG = 801007;
    
    /** 处理消息的对象为空 */
    public static final int MESSAGE_PROCESS_NULL = 801008;
    
    /** 网络连接不可用 */
    public static final int NETWORK_NOT_AVAILABLE = 801009;
    
    /** 其他类型错误 */
    public static final int OTHER_TYPE_ERROR = 801999;    
    /** 录音宝专用的错误码 */
    public static final int ERROR_CREATE_RECORD = 824001; //无法创建录音器
    public static final int ERROR_START_RECORD = 824002; //无法启动录音
    public static final int ERROR_WRITE_FILE = 824003; //保存文件失败
    public static final int ERROR_READ_RECORD = 824004; //读取不到录音数据
    public static final int ERROR_BLACKLIST = 824005; //机型黑名单
    
    public static final int ERROR_SETTINGS_OFF = 824006; //用户设置关闭
    public static final int ERROR_INNER_CHECK = 824007; //内测验证失败
    public static final int ERROR_PHONE_STATE_CALLING = 824008; //普通录音，正在通话中
    public static final int ERROR_READ_RECORD_FILE = 824009; //转写读取录音文件错误
    public static final int ERROR_RECORD_INTERRUPT = 824010; //录音被中断
    
}
