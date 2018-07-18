package com.example.HuiWang_SummerProject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple7;
import org.web3j.tx.Contract;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
public class verifyController {

    private static String UPLOAD_FOLDER = "C:\\Users\\Kieran\\IdeaProjects\\HuiWang_SummerProject\\src\\main\\resources\\temp\\";

    @PostMapping("/verifySubmission")
    public String verify(@RequestParam String applicantKey, Model model)
    {

        try {

            //CREDENTIALS HERE SHOULD BE A RANDOM GENERATED ACCOUNT, NOT CONTRACT OWNER
            VerifiedData aData = VerifiedData.load("0x838da3f4789261f2889d9d0d66ffaf90f13b9285", HuiWang_SummerProject.web3j, HuiWang_SummerProject.credentials, Contract.GAS_PRICE, Contract.GAS_LIMIT);
            if(aData.searchRecords(applicantKey).send()){
                model.addAttribute("result", "Fingerprint exists");

                Tuple3<String, String, byte[]> returnedData = aData.getRecord(applicantKey).send();

                model.addAttribute("fingerprint", returnedData.getValue1());
                model.addAttribute("Producer", returnedData.getValue2());
                Path path = Paths.get(UPLOAD_FOLDER + returnedData.getValue1() + ".mp4");
                Files.write(path, returnedData.getValue3());

                model.addAttribute("video", returnedData.getValue3().toString());
            }else{
                model.addAttribute("result", "Unable to find fingerprint");
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        model.addAttribute("record", new Fingerprint());
        model.addAttribute("producer", new Producer());
        return "verify";

    }

    @GetMapping("/outputProducers")
    public String outputProducers(Model model) throws Exception {

        AddressStorage aStore = AddressStorage.load("0x2f392014df740a506616f6854a14bdaff923df13", HuiWang_SummerProject.web3j, HuiWang_SummerProject.credentials,Contract.GAS_PRICE, Contract.GAS_LIMIT);
        BigInteger arraySize = aStore.getCount().send();

        int count = arraySize.intValue();
        Tuple2<String, String> values = null;
        String file = "C:\\Users\\Kieran\\IdeaProjects\\HuiWang_SummerProject\\src\\main\\resources\\temp\\VerifiedProducers.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for(int i =0; i < count; i++){

            values = aStore.outputAddress(BigInteger.valueOf(i)).send();

            writer.append("Producer Name: " + values.getValue1() +" Address: "  + values.getValue2());
            writer.newLine();
            writer.newLine();
        }
        writer.close();
        model.addAttribute("producer", new Producer());
        return "admin";
    }

}
