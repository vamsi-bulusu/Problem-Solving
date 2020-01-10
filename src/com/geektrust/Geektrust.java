package com.geektrust;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
            exist = true;
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

    private List<String> siblings(Family familyHead,String[] query){
        String name = query[1];
        Queue<Family> queue = new LinkedList<>();
        queue.add(familyHead);
        List<String> siblings = new LinkedList<>();
        if(name.equals(familyHead.getPerson().getName()) || name.equals(familyHead.getPerson1().getName())){
            exist = true;
            return null;
        }
        while (!queue.isEmpty()){
            Family family = queue.poll();
            Set<Family> families = family.getFamilies();
            for(Family family1:families){
                siblings.add(family1.getPerson().getName());
                queue.add(family1);
            }
            if(!siblings.isEmpty())
                if(siblings.contains(name)){
                    exist = true;
                siblings.remove(name);
                return siblings;
            }
            siblings.clear();
        }
        return siblings;
    }

    private List<String> sonOrDaughter(Family familyHead, String[] query){
        String name = query[1], relation = query[2];
        Queue<Family> families = new LinkedList<>();
        families.add(familyHead);
        while (!families.isEmpty()){
              Family family = families.poll();
              if(family.getStatus() == RelationshipStatus.SINGLE && name.equals(family.getPerson().getName()) ){
                  exist = true;
              }
              if(family.getStatus() == RelationshipStatus.MARRIED){
                  if(name.equals(family.getPerson().getName()) || name.equals(family.getPerson1().getName())){
                      exist = true;
                      return family.getSonOrDaughter(relation);
                  }
              }
              Set<Family> set = family.getFamilies();
              families.addAll(set);
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
            exist = false;
            if(query[0].equals("ADD_CHILD")){
                Boolean success = geektrust.addChild(geektrust.familyHead, query);
                System.out.println(!exist ? "PERSON_NOT_FOUND" : (success ? "CHILD_ADDITION_SUCCEEDED" : "CHILD_ADDITION_FAILED"));
            }
            else{
                if(query[2].equals("Son") || query[2].equals("Daughter")){
                   List<String> temp = geektrust.sonOrDaughter(geektrust.familyHead, query);
                   if(exist && temp == null){
                       System.out.println("NONE");
                   }
                   else if(temp == null){
                       System.out.println("PERSON_NOT_FOUND");
                   }
                   else{
                       for (String s:temp) {
                           System.out.print(s + " ");
                       }
                   }
                }
                else if(query[2].equals("Siblings")){
                    List<String> list = geektrust.siblings(geektrust.familyHead, query);
                    assert list != null;
                    if(exist && list.isEmpty()){
                        System.out.println("NONE");
                    }
                    if(!exist){
                        System.out.println("PERSON_NOT_FOUND");
                    }
                    else {
                        for (String s:list) {
                            System.out.print(s + " ");
                        }
                    }
                }
            }
        }
    }
}
