package com.baidu.jinbao.innerapi.rpc;

import org.junit.Test;

import com.baidu.bjf.remoting.protobuf.ProtobufIDLGenerator;
import com.baidu.jinbao.innerapi.rpc.spu.dto.CspuInfoDto;
import com.baidu.jinbao.mall.rpc.item.dto.BcsImageCondition;
import com.baidu.jinbao.mall.rpc.item.dto.MallSkuPpsDtoListResponse;
import com.baidu.jinbao.mall.rpc.item.dto.PageItemCondition;
import com.baidu.jinbao.mall.rpc.item.dto.PageItemTotalInfoListResponse;

/**
 * RpcTest
 * 
 * @author cgd
 * @date 2015年6月15日 下午9:50:06
 */
public class RpcTest {

    @Test
    public void testRpcServcie() {
        /*
         * RpcClient rpcClient = new RpcClient();
         * 
         * ProtobufRpcProxy<BaseCmPropertyRpcService> pbrpcProxy = new
         * ProtobufRpcProxy<BaseCmPropertyRpcService>(rpcClient, BaseCmPropertyRpcService.class);
         * pbrpcProxy.setPort(8999); BaseCmPropertyRpcService proxy = pbrpcProxy.proxy();
         * 
         * BaseCmPropertySearchResponse rpcServiceMetaInfo = proxy.getRecords(condition). Assert.assertEquals(6,
         * rpcServiceMetaInfo.getRpcServiceMetas().size());
         * 
         * List<RpcServiceMeta> rpcServiceMetas = rpcServiceMetaInfo.getRpcServiceMetas(); for (RpcServiceMeta
         * rpcServiceMeta : rpcServiceMetas) {
         * System.out.println("-----------------------RPC service meta info------------------");
         * System.out.println("serviceName:" + rpcServiceMeta.getServiceName()); System.out.println("methodName:" +
         * rpcServiceMeta.getMethodName()); System.out.println("inputProto:" + rpcServiceMeta.getInputProto());
         * System.out.println("outputProto:" + rpcServiceMeta.getOutputProto()); }
         */

        // 返回的内容即为 Protobuf的IDL描述文件
        String code = ProtobufIDLGenerator.getIDL(PageItemCondition.class);
        System.out.println(code);

    }

}
