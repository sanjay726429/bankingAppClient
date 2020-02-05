package com.example.bankAppclient.controller;

import com.example.bankAppclient.TAuthService;
import com.example.bankAppclient.TCustomer;
import com.example.bankAppclient.TCustomerService;
import com.example.bankAppclient.dao.AuthBody;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransportException;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    THttpClient httpClient;
    {
        try {
            httpClient = new THttpClient("http://localhost:8081//banking/auth/");
        } catch (TTransportException ex) {
            ex.printStackTrace();
        }
    }

    TProtocol protocol = new TJSONProtocol(httpClient);

    TAuthService.Client client = new TAuthService.Client(protocol);

    @RequestMapping(method = RequestMethod.GET , value =  "/login")
    public void login(@RequestBody AuthBody authBody) throws TException {
        client.login(authBody.getAccountNumber(),authBody.getPassword());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    public void logout(@RequestParam(name="accountNumber") String accountNumber) throws TException {
        client.logout(accountNumber);
    }
}
