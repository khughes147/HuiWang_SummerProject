package com.example.HuiWang_SummerProject;
//import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Hash;
import org.web3j.crypto.WalletUtils;
import org.web3j.tx.Contract;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//import static HuiWang_SummerProject.web3;


@Controller
public class recordController {


    public static Credentials creds;
    private String walletName;
    private String address = "empty";
    private byte [] bytes = "Hello".getBytes();
    private static String UPLOAD_FOLDER = "C:\\Users\\khugh\\IdeaProjects\\HuiWang_SummerProject\\src\\main\\resources\\temp\\";

    @PostMapping("/publishForm")
    public String publish(@ModelAttribute Fingerprint record, BindingResult result, Model model) throws IOException {
        byte[] HexMessage = Hash.sha3(record.getFingerprint().getBytes());
        System.out.println(record.getPassword());
        System.out.println(record.getWallet());
        System.out.println(record.getVideoFile());
        Path path = Paths.get(UPLOAD_FOLDER + record.getVideoFile().getOriginalFilename());
        try {
        Files.write(path, record.getVideoFile().getBytes());
        } catch (IOException e) {

            model.addAttribute("errorMessage", "Ensure all fields are completed correctly");
            model.addAttribute("Fingerprint", new Fingerprint());
            model.addAttribute("producer", new Producer());

            e.printStackTrace();
            return "publish";

        }
        try {
            creds = WalletUtils.loadCredentials(record.getPassword(), "C:\\Users\\khugh\\IdeaProjects\\HuiWang_SummerProject\\src\\main\\resources\\temp\\" + record.getWallet());
        } catch (IOException e) {
            model.addAttribute("errorMessage", "Unable to load wallet");
            model.addAttribute("Fingerprint", new Fingerprint());
            model.addAttribute("producer", new Producer());

            e.printStackTrace();
            return "publish";
        } catch (CipherException e) {
            model.addAttribute("errorMessage", "Unable to load wallet");
            model.addAttribute("Fingerprint", new Fingerprint());
            model.addAttribute("producer", new Producer());

            e.printStackTrace();
            return "publish";
        }
        Sign.SignatureData signature = Sign.signMessage(HexMessage, creds.getEcKeyPair());
        VerificationSmartContract aVerifier = VerificationSmartContract.load("0xef20a0a7719cdfae62b93aaa60cae8eb133a15e8", HuiWang_SummerProject.web3j, HuiWang_SummerProject.credentials,Contract.GAS_PRICE, Contract.GAS_LIMIT);
        try {
            aVerifier.verify(record.getFingerprint(), record.getProducerName(), (UPLOAD_FOLDER + record.getVideoFile().getOriginalFilename()).getBytes(),  HexMessage, BigInteger.valueOf(signature.getV()) , signature.getR() , signature.getS()).send();
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to upload record");
            model.addAttribute("Fingerprint", new Fingerprint());
            model.addAttribute("producer", new Producer());
            e.printStackTrace();
            return "publish";
        }

        model.addAttribute("successMessage", "Successfully uploaded record!");

        model.addAttribute("Fingerprint", new Fingerprint());
        model.addAttribute("producer", new Producer());
        return "publish";

    }




}





