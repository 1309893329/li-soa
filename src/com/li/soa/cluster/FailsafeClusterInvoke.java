package com.li.soa.cluster;

import com.li.soa.invoke.Invocation;
import com.li.soa.invoke.Invoke;

/** 
 * @Description 调用节点失败，直接忽略 
 * @ClassName   FailsafeClusterInvoke 
 * @Date        2017年11月18日 下午9:55:49 
 * @Author      Li 
 */

public class FailsafeClusterInvoke implements Cluster {
    
    public String invoke(Invocation invocation) throws Exception {
        Invoke invoke = invocation.getInvoke();
        
        try {
            return invoke.invoke(invocation);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "忽略";
        }
    }
    
}
