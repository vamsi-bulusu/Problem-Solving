package com.geektrust;

import java.io.*;
import java.util.Set;

public class Geektrust {

    private Family familyHead;

    private Geektrust(Person person, Person person1) {
        this.familyHead = new Family(person,person1,RelationshipStatus.MARRIED);
    }
    private void generateFamilyTree() throws IOException {
        File file = new File("C:\\Users\\nbulusu\\IdeaProjects\\JavaProgramming\\src\\com\\geektrust\\generateTree.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null){
            String[] string = line.split("\\s+");
            if(string[0].equals("ADD_CHILD")){
                addChild(this.familyHead, string);
            }
            else{
                addFamily(this.familyHead, string[1], new Person(string[2], Gender.valueOf(string[3])));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(reader.readLine().trim());
        Person person = new Person("King-Shan", Gender.Male);
        Person person1 = new Person("Queen-Anga", Gender.Female);
        Geektrust geektrust = new Geektrust(person, person1);
        geektrust.generateFamilyTree();
        System.out.println("Tree Generated Successfully");
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
        if(familyHead.getStatus() == RelationshipStatus.SINGLE){
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
                    if(addChild(family,query)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private Boolean addFamily(Family familyHead,String name,Person newPerson){
        String personName = familyHead.getPerson().getName();
        if(familyHead.getStatus() == RelationshipStatus.MARRIED){
            return false;
        }
        else if(familyHead.getStatus() == RelationshipStatus.SINGLE){
            if(name.equals(personName)) {
                familyHead.makeFamily(familyHead.getPerson(), newPerson);
                return true;
            }
        }
        else{
            Set<Family> families = familyHead.getFamilies();
            for (Family family:families) {
                if(addFamily(family, name, newPerson)){
                    return true;
                }
            }
        }
        return false;
    }
}
