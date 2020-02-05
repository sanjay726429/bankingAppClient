package com.example.bankAppclient.controller;

import com.example.bankAppclient.TCustomer;
import com.example.bankAppclient.TCustomerService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransportException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    THttpClient httpClient;
    {
        try {
            httpClient = new THttpClient("http://localhost:8081/banking/customer/");
        } catch (TTransportException ex) {
            ex.printStackTrace();
        }
    }

    TProtocol protocol = new TJSONProtocol(httpClient);

    TCustomerService.Client client = new TCustomerService.Client(protocol);

    @RequestMapping(method = RequestMethod.POST , value =  "/create")
    public void create(@RequestBody TCustomer tCustomer) throws TException {
        client.addAccountRequest(tCustomer);
    }
}
