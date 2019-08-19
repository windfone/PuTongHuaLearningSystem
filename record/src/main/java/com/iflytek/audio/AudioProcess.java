package com.iflytek.audio;

public class AudioProcess {
    
    static {
        System.loadLibrary("AudioProcessEngine");
    }

    /*
     * 创建处理的实例，可以同时创建多个处理实例，通过返回值进行区分
     * type: 处理的类型，在ProcessType中定义
     * return: 实例的句柄，该句柄用于处理的实际过程，<0 表示创建失败
     */
    public static native int createInstance(int type);
    
    
    /*
     * 查找参数 
     * handle : 句柄
     * paramName : 参数名称
     * return : 是否存在该参数，找到为true
     */
    public static native boolean findParam(int handle , String paramName);
    
    /*
     * 设置参数 （某些处理类型，必须在调用init前设置相关参数，例如编码必须设置sampleRate，channels等）
     * handle : 句柄
     * paramName : 参数名称
     * param : 参数值
     * return : 设置是否成功
     */
    public static native int setParam(int handle , String paramName , int param);
    
    /*
     * 获取参数
     * handle : 句柄
     * paramName : 参数名
     * 返回值： 参数值，（注：获取前先调用findParam进行测试，否则返回的值可能不正确）
     */
    public static native int getParam(int handle ,String paramName );
    
    /*
     * 初始化
     * handle : 句柄
     * rentur: =0，成功，其它：失败
     */
    
    public static native int init(int handle);
    /*
     * 处理音频流
     * handle: 句柄
     * srcData : 源数据
     * srcLen: 源长度
     * outData : 输出数据区（注：对于解码类型的操作，输出缓冲区建议设置成(15 * srcLen))
     * outLen : 输出长度
     * eof : 当前数据是否是数据结尾
     * return : =0 成功，其它失败
     */
    public static native int processStream(int handle , byte[] srcData ,int inLen , byte[] outData  , int length , boolean eof);
    
    /*
     * 处理文件
     * handle : 句柄
     * inFilePath: 输入文件
     * outFilePath : 输出文件
     * return : =0 成功，其它失败
     */
    public static native int procssFile(int handle , String inFilePath , String outFilePath);
    
    /*
     * 逆初始化句柄
     * handle:句柄
     * return:=0成功，其它失败
     */
    public static native int unInit(int handle);
    
    /*
     * 销毁句柄
     * handle : 句柄
     */
    public static native void destroyInstance(int handle);
    
    /*
     * 底层测试函数入口
     */
    public static native void test(); 
}
