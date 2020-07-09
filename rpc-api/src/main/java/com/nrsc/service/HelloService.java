package com.nrsc.service;

import java.util.Map;

public interface HelloService {
    String RMI_URL = "rmi://127.0.0.1:8001/HelloService";

    Map<String, String> getHelloInfo(Map<String, String> info);
}
