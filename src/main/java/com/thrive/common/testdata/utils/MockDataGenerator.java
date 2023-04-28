package com.thrive.common.testdata.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.thrive.common.testdata.regex.Generex;


public class MockDataGenerator {
	
	static String emailDomain = "@yopmail.com";
        
    public static List<String> generateRandomEmailAddresses(int count){
        List<String> emails = new ArrayList<String>();
        String email = "[a-z]{5,10}\\@[a-z]{5,20}\\.[a-z]{2,3}";
        for (int i=0; i<count; i++){            
            emails.add(i, new Generex(email).random());
        }
        return emails;
    }
    
    public static String generateRandomEmailAddress(){      
        String email = "[a-z]{5,10}\\@[a-z]{5,20}\\.[a-z]{2,3}";                
        return new Generex(email).random();     
    }
    
    public static String generateRandomMailDropAddress(int length){      
        return generateRandomWord(6) + emailDomain;             
    }
    
    public static String generateRandomClientMailDropAddress(){      
        return "autoclient" + generateRandomWord(6) + emailDomain;             
    }
    
    public static String generateGlobalCoachEmailAddress(){      
        return "auto.globcoach." + generateRandomWord(9).toLowerCase() + emailDomain;             
    }
    
    public static String generateClientEmailAddress(){      
        return "auto.client." + generateRandomWord(9) + emailDomain;             
    }
    
    public static String generateAccountManagerEmailAddress(){      
        return "auto.acntMgr." + generateRandomWord(9) + emailDomain;             
    }
    
    public static String generateInternalCoachMailDropAddress(){      
        return "auto.intcoach." + generateRandomWord(9) + emailDomain;             
    }
    
    public static String generateEAMailDropAddress(){      
        return "auto.ea." + generateRandomWord(6) + emailDomain;             
    }
       
    public static String generateRandomZipCode(){       
        //String zip = "[0-9]{5}";
        String zip = "[1-9]{1}[0-9]{4}";
        return new Generex(zip).random();       
    }
    
    public static String generateRandomFutureDate(){        
        String MM = "(0[1-9])|(1[0-2])"; 
        String DD = "(0[1-9])|(1[0-9])|(2[0-6])"; //1 to 26
        String YYYY = "(20[1-2][8-9])|(20[1-2][0-2])"; //2018 2019 2020 2021 to 2022
        String MMDDYYYY = "(" + MM + ")/(" + DD + ")/(" + YYYY + ")";               
        return new Generex(MMDDYYYY).random();      
    }
    
    public static String generateRandomPastDateUpToYear2000(){      
        String MM = "(0[1-9])|(1[0-2])"; 
        String DD = "(0[1-9])|(1[0-9])|(2[0-6])"; //1 to 26
        String YYYY = "(19[5-9][0-9])|(2000)"; //1950 to 2000
        String MMDDYYYY = "(" + MM + ")/(" + DD + ")/(" + YYYY + ")";               
        return new Generex(MMDDYYYY).random();      
    }
    
    public static String generateRandomPastDate(){      
        String MM = "(0[1-9])|(1[0-2])"; 
        String DD = "(0[1-9])|(1[0-9])|(2[0-6])"; //1 to 26
        String YYYY = "(19[5-9][0-9])|(20[0-1][0-7])"; //up to 2017
        String MMDDYYYY = "(" + MM + ")/(" + DD + ")/(" + YYYY + ")";               
        return new Generex(MMDDYYYY).random();      
    }
    
    public static String generateRandomPhoneNumberWithDashes(){     
        String phone = "[2-9]{3}-[0-9]{3}-[0-9]{4}";                
        return new Generex(phone).random();     
    }
    
    public static String generateRandomPhoneNumberWithoutDashes(){      
        String phone = "[7-9]{3}[0-9]{3}[0-9]{4}";              
        return new Generex(phone).random();     
    }
    
    public static String generateRandomIndiaMobileNumber(){      
    	return "+91" + generateRandomPhoneNumberWithoutDashes();   
    }
    
    public static String generateRandomSsnNumber(){     
        String ssn = "[0-9]{3}-[0-9]{2}-[0-9]{4}";              
        return new Generex(ssn).random();       
    }
    
    public static String generateNumberString(int minLen, int maxLen){
        String numberString =  wordRegexInt(minLen, maxLen);
        return new Generex(numberString).random();
    }
    
    public static String generateParagraph(int i, int j){
        String paragraph = generateSentence() + " " + generateSentence() + " " + generateSentence() + " " + generateSentence() + " " + generateSentence();
        return paragraph;       
    }
    
    public static String generateSentence(){
        String sentence = generateWord(8, 10) + " " + generateWord(5, 7) + " " + generateWord(5, 7) + " " + generateWord(5, 7) + ".";
        return sentence;
    }
    
    public static String generateSentence(int length){
       String sentence = "";
        while (sentence.length() < length) {
           sentence = sentence.concat(generateRandomWord(8)).concat(" ");
       }
        sentence = sentence.substring(0, length);
       return sentence;
    }
    
    public static String generateFullName(int minLen, int maxLen){
        String first = generateWord(minLen, maxLen); 
        String middle = generateWord(minLen, maxLen);
        String last = generateWord(minLen, maxLen);
        return last + " ," + first + " " + middle;
    }
    
    public static String generateRandomStreetAddress(){     
        String streetAddress = wordRegexInt(3, 6) + " " + wordRegex(3, 6) + " " + wordRegex(3, 6);          
        return new Generex(streetAddress).random();     
    }
    
    private static String wordRegex(int minLen, int maxLen){
        return String.format("[A-Z][a-z]{%s,%s}", minLen, maxLen); 
    }
    private static String wordRegex(int Len){
        return String.format("[A-Z][a-z]{%s}", Len-1); 
    }
    
    private static String wordRegexInt(int minLen, int maxLen){
        return String.format("[1-9][1-9]{%s,%s}", minLen, maxLen); 
    }
    
    public static String generateWord(int min, int max){        
        String word = wordRegex(min, max);          
        return new Generex(word).random();      
    }
    public static String generateRandomWord(int size){        
        String word = wordRegex(size);          
        return new Generex(word).random();      
    }

    public static String createUniqueNumber(Integer length) {
        String randomNumber = "[1-9]{" + length + "}";
        return new Generex(randomNumber).random();
    }
    
    public static String replaceSpecialCharacters(String actualText){
        return actualText.replaceAll("[^a-zA-Z0-9]+","");
    }
    
    public static String generateClientFirstName(){
        return "auto-first-client" + generateWord(5, 9);
    }
    
    public static String generateClientMiddleName(){
        return "auto-middle-client" + generateWord(5, 9);
    }
    
    public static String generateClientLastName(){
        return "auto-last-client" + generateWord(5, 9);
    }
    
    public static String generateGlobalCoachFirstName(){
        return "auto-global-coach" + generateWord(3, 8);
    }
    
    public static String generateGlobalCoachMiddleName(){
        return "auto-glob-coach" + generateWord(3, 8);
    }
    
    public static String generateGlobalCoachLastName(){
        return  "auto-global-coach" + generateWord(1, 4);
    }
    
    public static String generateInternalCoachFirstName(){
        return "auto-int-coach" + generateWord(5, 9);
    }
    
    public static String generateInternalCoachMiddleName(){
        return "auto-int-coach" + generateWord(5, 9);
    }
    
    public static String generateInternalCoachLastName(){
        return "auto-int-coach" + generateWord(5, 9);
    }
    
    public static String generateEAFirstName(){
        return "auto-first-ent" + generateWord(5, 9);
    }
    
    public static String generateEAMiddleName(){
        return "auto-middle-ent" + generateWord(5, 9);
    }
    
    public static String generateEALastName(){
        return "auto-last-ent" + generateWord(5, 9);
    }
    
    public static String generateEnterpriseName(){
        return "auto-ent-" + generateWord(6, 8);
    }
    
    public static String generateAccountManagerFirstName(){
        return "auto-first-AcntMgr" + generateWord(7, 9);
    }
    
    
    public static String generateAccountManagerLastName(){
        return "auto-last-AcntMgr" + generateWord(7, 9);
    }
    
    
    public static String generateRandomCity(){
        return "auto-city-" + generateWord(5, 6);
    }
    
    public static String generateRandomState(){
        return "auto-state-" + generateWord(5, 6);
    }
    
    public static String generateRandomCounty(){
        return "auto-county-" + generateWord(5, 6);
    }
    
    public static String generateRandomWebsite(){
        return "http://autorandomwebsite" + generateWord(5, 6).toLowerCase() + ".com";
    }
    
    public static String generateRandomPostalCode(){
        return generateNumberString(5, 6);
    }
    
    public static String generateRandomSSO(){
        return "auto-SSO-" + generateNumberString(5, 6);
    }
    
    public static String generateCompany(){
        return "auto-company-" + generateNumberString(5, 6);
    }
    
    public static String generateRandomCategory(){
        return "auto-cat-" + generateNumberString(6, 7);
    }
    
    public static String generateRandomCategoryFr(){
        return "auto-cat-" + generateNumberString(6, 7);
    }
    
    public static String generateRandomTopic(){
        return "auto-topic-" + generateNumberString(6, 7);
    }
    
    public static String generateRandomTopicFr(){
        return "auto-topic-" + generateNumberString(6, 7);
    }
    
    public static String generateRandomExpertise(){
        return "auto-expertise-ex" + generateNumberString(6, 7);
    }
    
    public static String generateRandomExpertiseFr(){
        return "auto-expertise-ex" + generateNumberString(6, 7);
    }
    
    public static String generateRandomCategoryEdit(){
        return "auto-cat-" + generateNumberString(4, 7);
    }
    
    public static String generateRandomCategoryFrEdit() {
    	return "auto-cat-" + generateNumberString(4, 7);
    }
    
    public static String generateRandomTopicEdit(){
        return "auto-topic-" + generateNumberString(4, 7);
    }
    
    public static String generateRandomTopicFrEdit(){
        return "sujet automatique-" + generateNumberString(4, 7);
    }
    
    public static String generateRandomExpertiseEdit(){
        return "auto-expertise-" + generateNumberString(4, 7);
    }
    
    public static String generateRandomExpertiseFrEdit(){
        return "auto-expertise-" + generateNumberString(4, 7);
    }
    
    public static String generateRandomInternalCategory(){
        return "auto-cat-int" + generateNumberString(6, 7);
    }
    
    public static String generateRandomInternalTopic(){
        return "auto-topic-int" + generateNumberString(5, 6);
    }
    
    public static String generateRandomInternalExpertise(){
        return "auto-exepertise-int" + generateNumberString(5, 6);
    }
    
    public static String createRandomDeaNumber() {
        String finalDeaNumber = null;
        
        String sixDigit = MockDataGenerator.createUniqueNumber(6);
        String d1st = sixDigit.substring(0, 1);
        String d2st = sixDigit.substring(1, 2);
        String d3st = sixDigit.substring(2, 3);
        String d4st = sixDigit.substring(3, 4);
        String d5st = sixDigit.substring(4, 5);
        String d6st = sixDigit.substring(5, 6);
        int sumOf1st3rd5th = Integer.parseInt(d1st) + Integer.parseInt(d3st) + Integer.parseInt(d5st);      
        int sumOf2st4rd6th = Integer.parseInt(d2st) + Integer.parseInt(d4st) + Integer.parseInt(d6st);
        int doubleOfSecondSeries = sumOf2st4rd6th*2;
        int total = sumOf1st3rd5th + doubleOfSecondSeries;
        String lastDigit = String.valueOf(total).substring(1, 2);
        finalDeaNumber = MockDataGenerator.generateRandomWord(2).toUpperCase() + String.valueOf(d1st) + String.valueOf(d2st) + String.valueOf(d3st) + String.valueOf(d4st) + String.valueOf(d5st) + String.valueOf(d6st) + lastDigit;
        
        return finalDeaNumber;
    }
    
    public static int generateRandomInt(int min, int max){
       return new Random().nextInt((max - min) + 1) + min;
    }

    public static String[] generateArrayWords(int sizeArray, int sizeWord) {
        String[] array = new String[sizeArray];
        for (int i = 0; i < sizeArray; i++) {
            String word = wordRegex(sizeWord);
            array[i] = new Generex(word).random();
        }

        return array;
    }
    
    public static String generateSpecialCharacters(int size){        
        String word = specialCharRegex(size);          
        return new Generex(word).random();      
    }
    
    private static String specialCharRegex(int Len){
        return String.format("[^a-zA-Z0-9]{%s}", Len-1); 
    }
    
           
}