package com.advantage.ghoul;

import com.apps.util.Console;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;

class Character {
    private String name;
    private String description;
    private int hp;
    private List<String> inventory = new ArrayList<>();
    private final int MAXHP = 12;
    private int xp = 1;
    private int attackPoint;
    private String location;
    private String dialogue;
    private ObjectMapper objectMapper = new ObjectMapper();
    private FileReading file = new FileReading();
    private List<Character> listCharacter;

    void setHp(int hp) {
        this.hp = hp;
    }

    Character() {
        super();
    }

    Character(String name, String description, int hp, int attackPoint,String location) {
        this.name = name;
        this.description = description;
        this.hp = hp;
        this.attackPoint = attackPoint;
        this.location=location;
    }

    //business methods
    List<Character> dataReader() {
        try {
            String characterData = file.dataReader("Character.txt");
            listCharacter = objectMapper.readValue(characterData, new TypeReference<>() {
            });
        } catch (IOException e) {
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

    LinkedList<String> npcNameList() {
        LinkedList<String> nameList = new LinkedList<>();
        List<Character> characters = dataReader();
        for (int i = 0; i < dataReader().size() - 1; i++) {
            nameList.add(characters.get(i + 1).getName());
        }
        return nameList;
    }

    Character createCharacter(Character character) {
        String name = character.getName();
        Character npcInfor = character.getCharacterByName(name);
        String npcName = npcInfor.getName();
        String npcDescription = npcInfor.getDescription();
        String npcLocation=npcInfor.getLocation();
        int npcMaxHP = npcInfor.getHp();
        int npcAttackPoint = npcInfor.getAttackPoint();
        Character npcCharacter = new Character(npcName, npcDescription, npcMaxHP, npcAttackPoint,npcLocation);
        return npcCharacter;
    }

    int attackEngine(int attackPoint) {
        double chance = Math.random();
        if (chance < 0.2) {
            return 0;
        } else if (chance < 0.8) {
            return attackPoint;
        } else {
            return 2 * attackPoint;
        }
    }

    void attack(Character player, Character enemy, LinkedList<String> monsterList,Character playerAbility) {
        Console.pause(500);
        int attackPoint = player.getAttackPoint();
        int fightPower = player.attackEngine(attackPoint);
        if (player.getName().contains("player")&&playerAbility.getInventory().contains("divine sword")){
            fightPower=fightPower+10;
        }
        int enemyCurrentHP = enemy.getHp() - fightPower;
        if (enemyCurrentHP < 0) {
            enemyCurrentHP = 0;
        }
        if (fightPower == 0) {
            System.out.println(player.getName() + " missed. No damage caused.");
        } else if (fightPower > attackPoint) {
            System.out.println(Color.RED + player.getName() + " caused " + fightPower + " Critical damages. " + enemy.getName()
                    + " current HP is " + enemyCurrentHP + Color.RESET);
        } else {
            System.out.println(player.getName() + " caused " + attackPoint + " damages. " + enemy.getName()
                    + " current HP is " + enemyCurrentHP);
        }
        monsterHP(fightPower, enemy, player, monsterList,playerAbility);
    }

    void monsterHP(int damage, Character enemy, Character player, LinkedList<String> monsterList,Character playerAbility) {
        int monsterCurrentHp = enemy.getHp();
        monsterCurrentHp -= damage;
        enemy.setHp(monsterCurrentHp);
        if (monsterCurrentHp < 1) {
            System.out.println(player.getName() + " win");
            if (enemy.getName().equals("monster")) {
                System.out.println("Monster dropped the library key");
                monsterList.remove("monster");
            } else if (enemy.getName().equals("ghoul")) {
                System.out.println("Ghoul dropped the Life Stone");
                monsterList.remove("ghoul");
            } else if (enemy.getName().equals("king")) {
                System.out.println("You win the game");
                monsterList.remove("king");
            } else if(enemy.getName().equals("player")){
                GameControl.loseGame();
            }
        } else {
            enemy.attack(enemy, player, monsterList,playerAbility);
        }
    }

    void addItem(String roomName, String itemName, ItemMenu items, List<Location> rooms) {
        int roomNumber = -1;
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getCurrent().equals(roomName) && rooms.get(i).getItem().equals(itemName)) {
                roomNumber = i;
            }
        }
        ItemMenu itemInRoom = items.getItemByName(itemName);
        if (roomNumber == -1) {
            System.out.println("There is no " + itemName + " in this area");
        } else {
            inventory.add(itemName);
            rooms.get(roomNumber).setItem("no item");
            items.setLocation("inventory");
            System.out.println("You get " + itemName + " in your bag!");
        }
    }

    void dropItem(String itemName, Character player, ItemMenu items, List<Location> rooms) {
        for (int i = 0; i < player.getInventory().size(); i++) {
            if (player.getInventory().get(i).equals(itemName)) {
                inventory.remove(itemName);
                System.out.println("You drop " + itemName + " from your bag!");
                items.setLocation(Location.currentRoom);
            } else {
                System.out.println("There is no " + itemName + " to drop from your bag");
            }
        }
    }

    void checkInventory() {
        getInventory();
        if (getInventory().size() > 0) {
            System.out.println("You have following items: "+Color.YELLOW+ getInventory() + "."+Color.RESET);
        } else {
            System.out.println("You have nothing in your bag");
        }
    }

    void useHealingPotion(Character player) {
        int formerHp = player.getHp();
        if (inventory.contains("healing potion")) {
            if (player.getHp() == MAXHP) {
                System.out.println("You are healthy. You do not need the healing potion.");
            } else if (player.getHp() < MAXHP - 5) {
                player.setHp(player.getHp() + 5);
                System.out.println("Your Hp went from " + formerHp + " to " + player.getHp());
            } else {
                setHp(MAXHP);
                System.out.println("You have been restore to complete health. (Your current HP is " + MAXHP + " ).");
            }
        }
        getInventory().remove("healing potion");
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

    public List<String> getInventory() {
        return inventory;
    }

    public String getDialogue() {
        return dialogue;
    }

    @Override
    public String toString() {
        return "Character: Name= " + getName() + " Description= " + getDescription() + " hp= " + getHp() +
                " xp= " + getXp()+ " location= "+getLocation();
    }
}
