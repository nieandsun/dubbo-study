package com.nrsc.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface OrderService extends Remote {
    String RMI_URL = "rmi://127.0.0.1:9999/OrderService";
    int port = 9999;

    /***
     *
     * @param info
     * @return
     * @throws RemoteException 注意，这里必须抛出异常
     */
    Map<String, String> getOrderInfo(Map<String, String> info) throws RemoteException;
}
