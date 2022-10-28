package com.advantage.ghoul;

import com.apps.util.Console;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class FileReading {
    private JSONParser parser = new JSONParser();
    private ObjectMapper objectMapper = new ObjectMapper();
    private List<Character> listData;
    private List<String> nameList = new ArrayList<>();

    static InputStream getFileFromResourceAsStreamFortxt(String fileName) {
        ClassLoader classLoader = FileReading.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("Can not read the file! " + fileName);
        } else {
            return inputStream;
        }
    }

    static void printInputStream(InputStream content, boolean delay, String color) {

        System.out.println(color);
        try (InputStreamReader streamReader = new InputStreamReader(content, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Color.RESET);
        }
    }

    public List dataReader(String fileName) {
        try {
            InputStream locationFile = FileReading.getFileFromResourceAsStreamFortxt(fileName);
            String result = new BufferedReader(new InputStreamReader(locationFile))
                    .lines().collect(Collectors.joining("\n"));
            Object obj = parser.parse(result);
            String data = obj.toString();
            listData = objectMapper.readValue(data, new TypeReference<>() {
            });
            for (int i = 0; i < listData.size(); i++) {
                //create the list of the room name in the with array list
                nameList.add(listData.get(i).getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listData;
    }
}

