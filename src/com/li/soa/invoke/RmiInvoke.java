package com.li.soa.invoke;

import java.rmi.RemoteException;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.li.soa.configBean.Reference;
import com.li.soa.loadbalance.LoadBalance;
import com.li.soa.loadbalance.NodeInfo;
import com.li.soa.rmi.RmiUtil;
import com.li.soa.rmi.SoaRmi;

/** 
 * @Description RMi的通讯协议
 * @ClassName   RmiInvoke 
 * @Date        2017年11月16日 下午9:51:47 
 * @Author      Li 
 */
public class RmiInvoke implements Invoke {
    
    public String invoke(Invocation invocation) throws Exception {
        try {
            List<String> registryInfo = invocation.getReference()
                    .getRegistryInfo();
            //这个是负载均衡算法
            String loadbalance = invocation.getReference().getLoadbalance();
            Reference reference = invocation.getReference();
            LoadBalance loadbalanceBean = reference.getLoadBalances()
                    .get(loadbalance);
            
            NodeInfo nodeinfo = loadbalanceBean.doSelect(registryInfo);
            
            //我们调用远程的生产者是传输的json字符串
            //根据serviceid去对端生产者的spring容器中获取serviceid对应的实例
            //根据methodName和methodType获取实例的method对象
            //然后反射调用method方法
            JSONObject sendparam = new JSONObject();
            sendparam.put("methodName", invocation.getMethod().getName());
            sendparam.put("methodParams", invocation.getObjs());
            sendparam.put("serviceId", reference.getId());
            sendparam.put("paramTypes", invocation.getMethod()
                    .getParameterTypes());
            
            RmiUtil rmi = new RmiUtil();
            SoaRmi soarmi = rmi.startRmiClient(nodeinfo, "jacksoarmi");
            
            return soarmi.invoke(sendparam.toJSONString());
        }
        catch (RemoteException e) {
            throw e;
        }
    }
}
