package org.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor(staticName = "create")
@NoArgsConstructor
public class User implements Serializable {

    private int id ;
    private String fullName ;
    private String country ;


}
