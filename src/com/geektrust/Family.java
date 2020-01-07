package com.geektrust;

import java.util.HashSet;
import java.util.Set;

class Family {
    private Person person, person1;
    private RelationshipStatus status;
    private Set<Family> families;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson1() {
        return person1;
    }

    public void setPerson1(Person person1) {
        this.person1 = person1;
    }

    RelationshipStatus getStatus() {
        return status;
    }

    public void setStatus(RelationshipStatus status) {
        this.status = status;
    }

    Set<Family> getFamilies() {
        return families;
    }

    void setFamilies(Set<Family> families) {
        this.families = families;
    }

    private Family(Person person, RelationshipStatus status) {
        this.person = person;
        this.status = status;
        this.families = null;
    }

    Family(Person person, Person person1, RelationshipStatus status) {
        this.person = person;
        this.person1 = person1;
        this.status = status;
        this.families = new HashSet<>();
    }

    Family makeFamily(Person person, Person person1){
        return new Family(person, person1, RelationshipStatus.MARRIED);
    }

    Family makeChild(Person person){
        return new Family(person, RelationshipStatus.SINGLE);
    }

    String getMother(){
        Person p = person.getGender() == Gender.Female ? person : person1;
        return p.getName();
    }

}
