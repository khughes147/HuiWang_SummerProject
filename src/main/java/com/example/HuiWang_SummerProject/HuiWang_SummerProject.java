package com.example.HuiWang_SummerProject;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;


import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;


@SpringBootApplication
@Controller
@EnableAutoConfiguration
@ComponentScan


public class HuiWang_SummerProject {


    public static Web3j web3j = Web3j.build(new HttpService());
    public static Credentials credentials;

    static {
        try {
            credentials = WalletUtils.loadCredentials("Ethereum", "C:\\Users\\khugh\\Documents\\EthereumProjectChain\\data\\keystore\\UTC--2018-06-20T16-20-55.193747000Z--83e08a5e77901126a16721590a8e1379fb7b1b6b");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CipherException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
		SpringApplication.run(HuiWang_SummerProject.class, args);
        }


    }

