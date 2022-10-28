package com.advantage.ghoul;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Character {

    private String name;
    private String description;
    private int hp;
    private int xp = 1;
    private int attackPoint;
    private String location;
    private String dialogue;
    private JSONParser parser=new JSONParser();
    private ObjectMapper objectMapper = new ObjectMapper();
    private List<Character> listCharacter;
    private List<String> characterNameList = new ArrayList<>();

    public void setHp(int hp) {
        this.hp = hp;
    }
    Character(){
        super();
    }

    Character(String name, String description, int hp, int attackPoint){
        this.name = name;
        this.description = description;
        this.hp = hp;
        this.attackPoint = attackPoint;
    }

    //business methods

    public List<Character> dataReader() {
        try {
            InputStream characterFile = FileReading.getFileFromResourceAsStreamFortxt("Character.txt");
            String result = new BufferedReader(new InputStreamReader(characterFile))
                    .lines().collect(Collectors.joining("\n"));
            Object obj = parser.parse(result);
            String data = obj.toString();
            listCharacter = objectMapper.readValue(data, new TypeReference<>() {
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listCharacter;
    }

    Character getCharacterByName(String name) {
        Character character = null;

        List<Character> characters = dataReader();
        for (Character npc : characters) {
            if (npc.getName().equals(name)) {
                return character = npc;
            }
        }
        return character;
    }



    void attack(Character opponent){

            int attackPoint = (int)(Math.random()* getAttackPoint());
            if (attackPoint==0){
                System.out.println(getName()+" missed. No damage caused.");
            }else{
                System.out.println(getName()+" caused " + attackPoint + " damages" );
            }

            monsterHP(attackPoint, opponent);



    }

    void monsterHP(int damage, Character monster){
        monster.hp-=damage;
        if(monster.hp <1){
            System.out.println(this.getName() + " win");
            if(monster.getName().equals("monster")){
                System.out.println("Monster dropped the library key");
            }
        }else{
            monster.attack(this);
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getHp() {
        return hp;
    }

    public int getXp() {
        return xp;
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    public String getLocation() {
        return location;
    }

    public String getDialogue() {
        return dialogue;
    }

    @Override
    public String toString(){
        return "Character: Name= " + getName() + " Description= " + getDescription() + " hp= " + getHp() +
        " hp= " + getXp();
    }

    public static void main(String[] args) {
        Character characters = new Character();
        FileReading file = new FileReading();
        System.out.println(characters.getCharacterByName("monster"));



        Character player = new Character("Vanessa","Princess", 100, 10);

        Character enemy = new Character("Ghoul", " Evil", 10, 2);
        player.attack(enemy);



    }
}
