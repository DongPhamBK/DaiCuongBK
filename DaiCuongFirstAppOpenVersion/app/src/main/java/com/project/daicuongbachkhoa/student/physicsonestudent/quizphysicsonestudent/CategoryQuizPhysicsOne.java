package com.project.daicuongbachkhoa.student.physicsonestudent.quizphysicsonestudent;

public class CategoryQuizPhysicsOne {

    // category
    public static final int
            CATEGORY1 = 1;
    public static final int
            CATEGORY2 = 2;
    public static final int
            CATEGORY3 = 3;
    private int
            id;
    private String
            name;

    public CategoryQuizPhysicsOne() {
    }

    public CategoryQuizPhysicsOne(String name) {
        this.name = name;
    }

    public CategoryQuizPhysicsOne(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
