package com.example.HuiWang_SummerProject;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple7;
import org.web3j.tx.Contract;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
public class verifyController {

    private static String UPLOAD_FOLDER = "C:\\Users\\khugh\\IdeaProjects\\HuiWang_SummerProject\\src\\main\\resources\\temp\\";
    private byte [] videoFile;
    @PostMapping("/verifySubmission")
    public String verify(@RequestParam String applicantKey, Model model)
    {

        try {

            //CREDENTIALS HERE SHOULD BE A RANDOM GENERATED ACCOUNT, NOT CONTRACT OWNER
            VerifiedData aData = VerifiedData.load("0x838da3f4789261f2889d9d0d66ffaf90f13b9285", HuiWang_SummerProject.web3j, HuiWang_SummerProject.credentials, Contract.GAS_PRICE, Contract.GAS_LIMIT);
            if(aData.searchRecords(applicantKey).send()){
                model.addAttribute("result", "Fingerprint exists");

                Tuple3<String, String, byte[]> returnedData = aData.getRecord(applicantKey).send();
                videoFile = returnedData.getValue3();
                model.addAttribute("fingerprint", returnedData.getValue1());
                model.addAttribute("Producer", returnedData.getValue2());
                Path path = Paths.get(UPLOAD_FOLDER + returnedData.getValue1() + ".mp4");
                Files.write(path, returnedData.getValue3());

                model.addAttribute("download", "Download");
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
        String file = "C:\\Users\\khugh\\IdeaProjects\\HuiWang_SummerProject\\src\\main\\resources\\temp\\VerifiedProducers.txt";
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

    @GetMapping("/outputFingerprintData")
    public String outputFingerprintData(Model model) throws Exception {

        VerifiedData aData = VerifiedData.load("0x838da3f4789261f2889d9d0d66ffaf90f13b9285", HuiWang_SummerProject.web3j, HuiWang_SummerProject.credentials, Contract.GAS_PRICE, Contract.GAS_LIMIT);
        BigInteger arraySize = aData.getCount().send();

        int count = arraySize.intValue();
        Tuple3<String, String, byte[]> values = null;
        String file = "C:\\Users\\khugh\\IdeaProjects\\HuiWang_SummerProject\\src\\main\\resources\\temp\\VerifiedFingerprintData.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for(int i =0; i < count; i++){

            values = aData.outputAll(BigInteger.valueOf(i)).send();

            writer.append("Fingerprint: " + values.getValue1() +" Producer: "  + values.getValue2());
            writer.newLine();
            writer.newLine();
        }
        writer.close();
        model.addAttribute("producer", new Producer());
        return "admin";
    }


    @RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
    public void getFile( HttpServletResponse response) {
        try {
            // get your file as InputStream
            InputStream is = new ByteArrayInputStream(videoFile);
            // copy it to response's OutputStream
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {

            throw new RuntimeException("IOError writing file to output stream");
        }

    }}
