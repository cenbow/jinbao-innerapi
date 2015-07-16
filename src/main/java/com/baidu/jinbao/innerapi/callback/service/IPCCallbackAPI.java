package com.baidu.jinbao.innerapi.callback.service;

import com.baidu.adrc.ipc.api_interface.server.dto.IPCCallbackFailedTaskRequest;
import com.baidu.adrc.ipc.api_interface.server.dto.IPCCallbackResponse;
import com.baidu.adrc.ipc.api_interface.server.dto.IPCCallbackSuccessTaskRequest;

/**
 * 提供给图片中心调用的callback接口
 * 
 * @author cgd
 * @date 2015年6月18日 上午11:39:57
 */
public interface IPCCallbackAPI {

    /**
     * 图片中心处理成功对应的callback调用
     * 
     * @param request 处理成功的图片相关信息
     * @return 返回存储失败的图片url list
     * */
    public IPCCallbackResponse callbackSuccessTasks(IPCCallbackSuccessTaskRequest request);

    /**
     * 图片中心处理失败对应的callback调用
     * 
     * @param request 处理失败的图片相关信息
     * @return 返回存储失败的图片url list
     * */
    public IPCCallbackResponse callbackFailedTasks(IPCCallbackFailedTaskRequest request);

}
