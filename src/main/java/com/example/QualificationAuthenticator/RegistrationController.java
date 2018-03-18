package com.example.QualificationAuthenticator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.core.DefaultBlockParameterName;

import static com.example.QualificationAuthenticator.QualificationAuthenticatorApplication.web3;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.*;




@Controller
public class RegistrationController {

    private static ArrayList<University> universityArrayList = new ArrayList<University>();
    private ArrayList<University> unverifiedUniversityArrayList = new ArrayList<University>();

    public ArrayList<University> getUniversityArrayList() {
        return universityArrayList;
    }
    private Credentials creds;

    @Autowired
    EmailService regServ;

    @PostMapping("/emailSubmission")
    public String emailSubmission(@ModelAttribute University university, BindingResult result)
    {
        unverifiedUniversityArrayList.add(university);
       // regServ.sendSimpleMessage(university.getEmail(), "Registration in Progress", "Hi " + university.getAdminName() + ",\n\nThis email is to confirm that we are checking your registration as an Admin for " + university.getUniName() +  " and we will get back to you as soon as possible.\n\nMany thanks,\n\nAuthenti-Q" );
       // regServ.sendSimpleMessage("authentiq.register@gmail.com", "New registration", "Please investigate the following registration:\n \nAdministrator name: " + university.getAdminName() + "\nUniversity name: " + university.getUniName() + "\nAdmin email: " + university.getEmail() + "\n\nIf authentic, please login to admin system and add this university." );
        return "index";
    }

  //  @PostMapping("/addUniversity")
  //  public String addUniversity(@ModelAttribute University university, BindingResult result)
  //  {
   //     university.generateKey();
   //     university.setVerified(true);
        //regServ.sendSimpleMessage(university.getEmail(), "Registration Success!!", "Hi " + university.getAdminName() + ",\n\nThis email is to confirm that " + university.getUniName() +  " has successfully been added to the Authenti-Q service.\n\nMany thanks,\n\nAuthenti-Q" );
    //    universityArrayList.add(university);


    //    return "admin";
   // }

    @GetMapping(value = "/listUniversities")
    public ResponseEntity<List<University>> listAllUsers() {
        List<University> unis = universityArrayList;

        if (unis.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<University>>(unis, HttpStatus.OK);


    }

    @GetMapping(value = "/listUnverifiedUniversities")
    public ResponseEntity<List<University>> listUnverifiedUni() {

        List<University> unis = new ArrayList<University>();

        for (int i =0; i<unverifiedUniversityArrayList.size(); i++){

            if(unverifiedUniversityArrayList.get(i).isVerified() == false){
                unis.add(unverifiedUniversityArrayList.get(i));
            }
        }

        if (unis.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<University>>(unis, HttpStatus.OK);


    }

    @RequestMapping(value = "/deleteUni", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String Submit(@RequestParam("id") String email) {

        for(int i=0; i<universityArrayList.size(); i++){

            if(universityArrayList.get(i).getEmail().equals(email)){
                universityArrayList.remove(i);
            }

        }
        return "admin";
    }

    @RequestMapping(value = "/verifyUni", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String addUni(@RequestParam("id") String email) {

        for(int i=0; i<unverifiedUniversityArrayList.size(); i++){

            if(unverifiedUniversityArrayList.get(i).getEmail().equals(email)){
                unverifiedUniversityArrayList.get(i).setVerified(true);
                unverifiedUniversityArrayList.get(i).generateKey();
                universityArrayList.add(unverifiedUniversityArrayList.get(i));
                //regServ.sendSimpleMessage(unverifiedUniversityArrayList.get(i).getEmail(), "Registration Success!!", "Hi " + university.getAdminName() + ",\n\nThis email is to confirm that " + university.getUniName() +  " has successfully been added to the Authenti-Q service.\n\nMany thanks,\n\nAuthenti-Q" );

                try {
                    WalletUtils.generateFullNewWalletFile(universityArrayList.get(i).getKey(), new File("C:/Users/Kieran/documents/EthereumProjectChain/data/keystore"));
                    creds = WalletUtils.loadCredentials(universityArrayList.get(i).getKey(), "C:/Users/Kieran/documents/EthereumProjectChain/data/keystore");
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
            }

        }
        return "admin";
    }

    @RequestMapping(value = "/rejectUni", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String rejectUni(@RequestParam("id") String email) {

        for(int i=0; i<unverifiedUniversityArrayList.size(); i++){

            if(unverifiedUniversityArrayList.get(i).getEmail().equals(email)){
                unverifiedUniversityArrayList.remove(i);
                //regServ.sendSimpleMessage(unverifiedUniversityArrayList.get(i).getEmail(), "Publishing privileges removed", "Hi " + unverifiedUniversityArrayList.get(i).getAdminName() + ",\n\nThis email is to confirm that " + unverifiedUniversityArrayList.get(i).getUniName() +  " has been removed from the Authenti-Q service.\n\nMany thanks,\n\nAuthenti-Q" );

            }

        }
        return "admin";
    }

}

