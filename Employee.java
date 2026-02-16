package net.javaguides.ems.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;           // add automatically
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter                                     // we have to write these anotation
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity    // jpa entity
@Table(name = "employees")          // hibernate gonna create this table in Database
public class Employee {

    @Id     // primary key for this table from jakarta
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // auto incremment
    private Long id ;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email_id" , nullable=false , unique = true)
    private String email;
}
