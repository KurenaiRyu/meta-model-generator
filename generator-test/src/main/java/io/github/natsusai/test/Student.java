package io.github.natsusai.test;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Student {
    @Id
    private String id;
    private String stuName;
    private String stuId;
    private int    stuAge;
}