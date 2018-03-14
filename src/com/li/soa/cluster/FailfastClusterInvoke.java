package com.li.soa.cluster;

import com.li.soa.invoke.Invocation;
import com.li.soa.invoke.Invoke;

/** 
 * @Description 这个如果调用节点异常，直接失败 
 * @ClassName   FailfastClusterInvoke 
 * @Date        2017年11月18日 下午9:55:23 
 * @Author      Li 
 */

public class FailfastClusterInvoke implements Cluster {
    
    public String invoke(Invocation invocation) throws Exception {
        Invoke invoke = invocation.getInvoke();
        
        try {
            return invoke.invoke(invocation);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
}
