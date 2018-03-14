package com.li.soa.cluster;

import com.li.soa.invoke.Invocation;

public interface Cluster {
    public String invoke(Invocation invocation) throws Exception;
}
