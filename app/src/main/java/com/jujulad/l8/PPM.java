package com.jujulad.l8;

/**
 * Created by Juju on 15.12.2018.
 */

public class PPM {
    //Pola klasy
    private String gender;
    private double metabolism;
    private Double weight;
    private Double age;
    private Double height;
    //Gettery i settery
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getMetabolism() {
        return metabolism;
    }

    public void setMetabolism(double metabolism) {
        this.metabolism = metabolism;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
    //Konstruktor
    public PPM(String gender, double weight, double age, double height) {
        this.gender = gender;
        this.weight = weight;
        this.age = age;
        this.height = height;
    }

    //Metoda do obliczania PPM metodą Mifflina//Metoda do obliczania PPM metodą Mifflina
    //Na początku sprawdzana jest wysokość i jest ewentualnie przeliczana
    //A następnie na podstawie płci dobierana jest odpowiednie przeliczenie metabolizmu
    public void calculateMifflin(){
        if (height<2.5) height=height*100;
        if(gender.equals("Female")){
            metabolism=(10*weight)+(6.25*height)-(5*age)-161;
        }else{
            metabolism=(10*weight)+(6.25*height)-(5*age)+5;
        }
    }
    //Metoda do obliczania PPM metodą Harrisa
    //Na początku sprawdzana jest wysokość i jest ewentualnie przeliczana
    //A następnie na podstawie płci dobierana jest odpowiednie przeliczenie metabolizmu
    public void calculateHarris(){
        if (height<2.5) height=height*100;
        if(gender.equals("Female")){
            metabolism=655.1+(9.563*weight)+(1.85*height)-(4.676*age);
        }else{
            metabolism=66.5+(13.75*weight)+(5.003*height)-(6.775*age);
        }
    }
}
