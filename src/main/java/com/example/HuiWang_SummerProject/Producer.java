package com.example.HuiWang_SummerProject;


import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;
import org.web3j.crypto.WalletUtils;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.UUID;

public class Producer {



    private String email;
    private String producerName;
    private String walletFile;
    private String producerAddress;
    private boolean verified;
    private String password;





    public String getEmail() { return email; }

    public void setEmail(String email){
        this.email = email;
    }

    public String getProducerName(){
        return producerName;
    }

    public void setProducerName(String uniName){
        this.producerName = uniName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getProducerAddress() {
        return producerAddress;
    }

    public void setProducerAddress(String producerAddress) {
        this.producerAddress = producerAddress;
    }

    public void setWalletFile(String walletFile) {
        this.walletFile = walletFile;
    }

    public String getWalletFile() {
        return walletFile;
    }
}
