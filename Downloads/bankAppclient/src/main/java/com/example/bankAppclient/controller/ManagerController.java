package com.example.bankAppclient.controller;

import com.example.bankAppclient.TCustomerService;
import com.example.bankAppclient.TManagerService;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransportException;

public class ManagerController {
    THttpClient httpClient;
    {
        try {
            httpClient = new THttpClient("http://localhost:8081/banking/manager/");
        } catch (TTransportException ex) {
            ex.printStackTrace();
        }
    }

    TProtocol protocol = new TJSONProtocol(httpClient);

    TManagerService.Client client = new TManagerService.Client(protocol);
}
