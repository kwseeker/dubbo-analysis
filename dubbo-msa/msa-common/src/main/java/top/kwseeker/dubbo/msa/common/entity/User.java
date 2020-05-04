package top.kwseeker.dubbo.msa.common.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private Integer id;
    private String name;
    private int age;
}
