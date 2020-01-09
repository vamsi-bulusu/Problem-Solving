package com.geektrust;

import java.io.*;
import java.util.List;
import java.util.Set;

public class Geektrust {

    private Family familyHead;
    private static Boolean exist;

    private Geektrust(Person person, Person person1) {
        this.familyHead = new Family(person,person1,RelationshipStatus.MARRIED);
    }

    //Automating Generation of Family Tree
    private void generateFamilyTree() throws IOException {
        File file = new File("C:\\Users\\vamsi\\Desktop\\ProblemSolving\\src\\com\\geektrust\\generateTree.txt");
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

    //Adding Child's Based on Mother's Name
    private Boolean addChild(Family familyHead, String[] query) {
        String motherName = query[1], childName = query[2], childGender = query[3];
        String mother = familyHead.getStatus() == RelationshipStatus.SINGLE ? familyHead.getPerson().getName() : familyHead.getMother();
        if(mother.equals(motherName)){
            exist = false;
        }
        if(familyHead.getStatus() == RelationshipStatus.MARRIED){
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

    //Creating Family for a Child Node
    private Boolean addFamily(Family familyHead,String name,Person newPerson){
        String personName = familyHead.getPerson().getName();
        if(familyHead.getStatus() == RelationshipStatus.SINGLE){
            if(name.equals(personName)) {
                familyHead.setPerson1(newPerson);
                familyHead.setStatus(RelationshipStatus.MARRIED);
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

    private List<String> sonOrDaughter(Family familyHead, String[] query){
        String name = query[1], relation = query[2];
        if(familyHead == null){
            return null;
        }
        if(familyHead.getStatus() == RelationshipStatus.SINGLE && name.equals(familyHead.getPerson().getName())){
                exist = false;
                return familyHead.getSonOrDaughter(relation);
        }
        else if(familyHead.getStatus() == RelationshipStatus.MARRIED && name.equals(familyHead.getPerson().getName()) || name.equals(familyHead.getPerson1().getName())){
                exist = false;
                return familyHead.getSonOrDaughter(relation);
        }
        else{
            Set<Family> families = familyHead.getFamilies();
            for (Family family:families) {
                return sonOrDaughter(family,query);
            }
        }
        return null;
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
            exist = true;
            if(query[0].equals("ADD_CHILD")){
                Boolean success = geektrust.addChild(geektrust.familyHead, query);
                System.out.println(exist ? "PERSON_NOT_FOUND" : (success ? "CHILD_ADDITION_SUCCEEDED" : "CHILD_ADDITION_FAILED"));
            }
            else{
                if(query[2].equals("Son") || query[2].equals("Daughter")){
                   List<String> temp = geektrust.sonOrDaughter(geektrust.familyHead, query);
                   if(temp != null && temp.size() != 0){
                       for (String s:temp){
                           System.out.print(s + " ");
                       }
                   }
                   else if(!exist && temp == null){
                       System.out.print("None");
                   }
                   else{
                       System.out.print("PERSON_NOT_FOUND");
                   }
                }
            }
        }
    }

}
