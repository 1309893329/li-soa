package com.li.soa.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SoaRmi extends Remote {
    public String invoke(String param) throws RemoteException;
}
