package io.test.demo.demo;


import lombok.Data;

@Data
public class School implements ISchool {

    Klass class1;

    Student student100;

    public School(Klass class1, Student student100) {
        this.class1 = class1;
        this.student100 = student100;
    }

    @Override
    public void ding() {

        System.out.println("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student100);

    }

}
