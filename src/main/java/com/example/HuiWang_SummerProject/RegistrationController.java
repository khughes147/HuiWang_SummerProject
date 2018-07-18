package com.example.HuiWang_SummerProject;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;


import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.*;

import static com.example.HuiWang_SummerProject.HuiWang_SummerProject.credentials;


@Controller
public class RegistrationController {

    private static ArrayList<Producer> producerArrayList = new ArrayList<Producer>();
    private ArrayList<Producer> unverifiedProducerArrayList = new ArrayList<Producer>();

    public ArrayList<Producer> getUniversityArrayList() {
        return producerArrayList;
    }

    @Autowired
    EmailService regServ;

    @PostMapping("/emailSubmission")
    public ResponseEntity emailSubmission(@ModelAttribute Producer producer, BindingResult result, Model model) throws CipherException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, IOException {

        for(int o = 0; o< unverifiedProducerArrayList.size(); o++){
            if(unverifiedProducerArrayList.get(o).getEmail().equals(producer.getEmail())){
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }

        }
        producer.setWalletFile(WalletUtils.generateFullNewWalletFile(producer.getPassword(), new File("C:\\Users\\Kieran\\IdeaProjects\\HuiWang_SummerProject\\src\\main\\resources\\temp")));
        Credentials cred = WalletUtils.loadCredentials(producer.getPassword(),"C:\\Users\\Kieran\\IdeaProjects\\HuiWang_SummerProject\\src\\main\\resources\\temp\\" + producer.getWalletFile());
        producer.setProducerAddress(cred.getAddress());
        producer.setPassword("");
        unverifiedProducerArrayList.add(producer);
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping(value = "/listUniversities")
    public ResponseEntity<List<Producer>> listAllUsers() {
        List<Producer> unis = producerArrayList;

        if (unis.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Producer>>(unis, HttpStatus.OK);


    }

    @GetMapping(value = "/listUnverifiedUniversities")
    public ResponseEntity<List<Producer>> listUnverifiedUni() {

        List<Producer> unis = new ArrayList<Producer>();

        for (int i = 0; i< unverifiedProducerArrayList.size(); i++){

            if(unverifiedProducerArrayList.get(i).isVerified() == false){
                unis.add(unverifiedProducerArrayList.get(i));
            }
        }

        if (unis.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Producer>>(unis, HttpStatus.OK);


    }

    @RequestMapping(value = "/deleteUni", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String Submit(@RequestParam("id") String email) throws Exception {

        for(int i = 0; i< producerArrayList.size(); i++){

            if(producerArrayList.get(i).getEmail().equals(email)){
                producerArrayList.remove(i);
                AddressStorage aStore = AddressStorage.load("0x2f392014df740a506616f6854a14bdaff923df13", HuiWang_SummerProject.web3j, credentials, Contract.GAS_PRICE, Contract.GAS_LIMIT);
                aStore.remove(producerArrayList.get(i).getProducerAddress()).send();
            }

        }
        return "admin";
    }

    @RequestMapping(value = "/verifyUni", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String addUni(@RequestParam("id") String email) throws Exception {
    String key;
    String walletName = "";
        for(int i = 0; i< unverifiedProducerArrayList.size(); i++){

            if(unverifiedProducerArrayList.get(i).getEmail().equals(email)){
                unverifiedProducerArrayList.get(i).setVerified(true);

                try {

                    AddressStorage aStore = AddressStorage.load("0x2f392014df740a506616f6854a14bdaff923df13", HuiWang_SummerProject.web3j, credentials,Contract.GAS_PRICE, Contract.GAS_LIMIT);
                    aStore.addProducer(unverifiedProducerArrayList.get(i).getProducerName(), unverifiedProducerArrayList.get(i).getProducerAddress()).send();
                    TransactionReceipt transactionReceipt = Transfer.sendFunds(
                            HuiWang_SummerProject.web3j, credentials, unverifiedProducerArrayList.get(i).getProducerAddress() ,
                            BigDecimal.valueOf(3), Convert.Unit.ETHER).send();

                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (NoSuchProviderException e) {
                    e.printStackTrace();
                } catch (InvalidAlgorithmParameterException e) {
                    e.printStackTrace();
                } catch (CipherException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                regServ.sendSimpleMessage(unverifiedProducerArrayList.get(i).getEmail(), "Registration Success!!", "Hi " + unverifiedProducerArrayList.get(i).getProducerName() + ",\n\nThis email is to confirm that you have successfully been added to the service.\n Your wallet has been attached. Please keep this secure as you will need it to upload records. \n\nMany thanks", "C:\\Users\\Kieran\\IdeaProjects\\HuiWang_SummerProject\\src\\main\\resources\\temp\\" + unverifiedProducerArrayList.get(i).getWalletFile());

                producerArrayList.add(unverifiedProducerArrayList.get(i));

            }

        }

        return "admin";
    }

    @RequestMapping(value = "/rejectUni", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String rejectUni(@RequestParam("id") String email) throws Exception {

        for(int i = 0; i< unverifiedProducerArrayList.size(); i++){

            if(unverifiedProducerArrayList.get(i).getEmail().equals(email)){

                Files.deleteIfExists(Paths.get("C:\\Users\\Kieran\\IdeaProjects\\HuiWang_SummerProject\\src\\main\\resources\\temp\\" + unverifiedProducerArrayList.get(i).getWalletFile()));
                unverifiedProducerArrayList.remove(i);
            }

        }
        return "admin";
    }



}

