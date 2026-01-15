package com.example.makemytrip.booking.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "userdetails")
public class User{
    @Id
    @Column(name = "userid")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer userid;

    @Column(name = "username")
    private String username;

    @Column(name = "phonenumber")
    private Long phonenumber;

    @Column(name = "emailid")
    private String emailid;

    @Column(name = "emailsub")
    private boolean emailsub;

    @Column(name = "smssub")
    private boolean smssub;

    @Column(name = "userage")
    private Integer userage;
}
