package com.geektrust;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

public class Geektrust {

    private Family familyHead;

    private Geektrust(Person person, Person person1) {
        this.familyHead = new Family(person,person1,RelationshipStatus.MARRIED);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(reader.readLine().trim());
        Person person = new Person("King-Shan", Gender.MALE);
        Person person1 = new Person("Queen-Anga", Gender.FEMALE);
        Geektrust geektrust = new Geektrust(person, person1);
        while (testCases-- != 0){
            String[] query = reader.readLine().split("\\s+");
            if(query[0].equals("ADD_CHILD")){
                Boolean success = geektrust.addChild(geektrust.familyHead, query);
                System.out.println(success ? "CHILD_ADDITION_SUCCEEDED" : "CHILD_ADDITION_FAILED");
            }
        }
    }

    private Boolean addChild(Family familyHead, String[] query) {
        String motherName = query[1], childName = query[2], childGender = query[3];
        if(familyHead == null){
            return false;
        }
        else if(familyHead.getStatus() == RelationshipStatus.MARRIED){
            String mother = familyHead.getMother();
            if(mother.equals(motherName)){
                familyHead.getFamilies().add(familyHead.makeChild(new Person(childName, Gender.valueOf(childGender))));
                return true;
            }
            else{
                Set<Family> families = familyHead.getFamilies();
                for (Family family:families) {
                    addChild(family,query);
                }
            }
        }
        return false;
    }

}
