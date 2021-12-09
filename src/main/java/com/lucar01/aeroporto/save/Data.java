package com.lucar01.aeroporto.save;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public final class Data {

    private static final String SETTINGS_PATH = "data/settings/settings.dat";

    public static final String THEME_STRING = "Theme";
    public static final String LANG_STRING = "Language";

    /**
     * private constructor because I don't want the class to be instantiated.
     */
    private Data(){}

    /**
     *
     * @param theme: the theme that has been set and that we want to save in the settings file.
     * @param language: the language that has been set and that we want to save in the settings file.
     */
    public static void saveSettings(final String theme, final String language){
        JSONParser jsonParser = new JSONParser();

        JSONObject jsonObject = new JSONObject();

        File file = new File(Data.SETTINGS_PATH);

        try {
            if(file.length() == 0){

                jsonObject.put(Data.THEME_STRING, theme);
                jsonObject.put(Data.LANG_STRING, language);

                byte[] encryptedMessage = SecureData.encrypt(jsonObject.toJSONString().getBytes(), SecureData.PASSWORD);

                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(encryptedMessage);

                fileOutputStream.close();
            } else {
                byte[] decryptedMessage = SecureData.decryptFile(Data.SETTINGS_PATH, SecureData.PASSWORD);
                String decryptedMessageString = new String(decryptedMessage, StandardCharsets.UTF_8);
                Object obj = jsonParser.parse(decryptedMessageString);

                jsonObject = (JSONObject) obj;

                jsonObject.replace(Data.THEME_STRING, theme);
                jsonObject.replace(Data.LANG_STRING, language);

                byte[] encryptedMessage = SecureData.encrypt(jsonObject.toJSONString().getBytes(), SecureData.PASSWORD);

                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(encryptedMessage);

                fileOutputStream.close();
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("nel catch");
        }
    }

    /**
     *
     * @return an HashMap<String, String> with the Theme and the Language in the settings file.
     */
    public static HashMap<String, String> loadSettings(){
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        JSONParser jsonParser = new JSONParser();

        try {
            byte[] decryptMessage = SecureData.decryptFile(Data.SETTINGS_PATH, SecureData.PASSWORD);

            String clearMessage = new String(decryptMessage, StandardCharsets.UTF_8);

            JSONObject jsonObject = (JSONObject) jsonParser.parse(clearMessage);

            for(int i = 0; i < jsonObject.size(); i++){

                String theme = jsonObject.get(Data.THEME_STRING).toString();

                String language = jsonObject.get(Data.LANG_STRING).toString();

                map.put(Data.THEME_STRING, theme);
                map.put(Data.LANG_STRING, language);

            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return map;
    }

    /**
     * It will delete all the data stored in the save_file.txt, but it will keep the file.
     */
    public static void resetSettingsFile(){

        try {

            FileWriter fileWriter = new FileWriter(Data.SETTINGS_PATH, false); // mettendo false ricrea il file, cancellando quello che c'era prima.
            fileWriter.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
