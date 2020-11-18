package io.github.natsusai.modelgen;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {
    private String stuName;
    private String stuId;
    private int    stuAge;
    @Id
    private String id;

    public String getStuName() {
        return this.stuName;
    }

    public String getStuId() {
        return this.stuId;
    }

    public int getStuAge() {
        return this.stuAge;
    }

    public String getId() {
        return this.id;
    }
}