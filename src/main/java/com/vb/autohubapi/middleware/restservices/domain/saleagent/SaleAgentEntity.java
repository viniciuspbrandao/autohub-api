package com.vb.autohubapi.middleware.restservices.domain.saleagent;

import com.vb.autohubapi.middleware.restservices.domain.AgentAccessLevel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity()
@Table(name= "tb_sales_users_v2")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class SaleAgentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password")
    private String password;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "hire_date")
    private LocalDateTime hireDate;

    @Column(name = "access_level")
    @Enumerated(EnumType.STRING)
    private AgentAccessLevel accessLevel;

    @Column(name = "salary")
    private String  salary;

    @Column(name = "commission")
    private String  commission;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime  updatedAt;

    @Column(name = "status")
    private Integer status;

}
