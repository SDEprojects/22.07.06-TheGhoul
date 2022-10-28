//package com.advantage.ghoul;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.List;
//import java.util.stream.Collectors;
//
//class ParseCommand {
//    private JSONParser parser=new JSONParser();
//    private ObjectMapper objectMapper = new ObjectMapper();
//    List<ParseCommand> parseCommands;
//
//
//
//    public List<ParseCommand> dataReader() {
//        try {
//            InputStream itemFile = FileReading.getFileFromResourceAsStreamFortxt("Synonymous.txt");
//            String result = new BufferedReader(new InputStreamReader(itemFile))
//                    .lines().collect(Collectors.joining("\n"));
//            Object obj = parser.parse(result);
//            String data = obj.toString();
//            parseCommands = objectMapper.readValue(data, new TypeReference<>() {
//            });
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return parseCommands;
//    }
//    @Override
//    public String toString(){
//        return "Item: name=" + getName() + ", location=" + getLocation()
//                + ", description=" + getDescription();
//    }
//}
