package com.internal.service.template.model;

import java.util.Date;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "audit_trail")
public class UserHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userHistoryId;

    @Column(nullable = false)
    @CreationTimestamp
    private Date activityTime;

    @Column(nullable = false,length=50)
    private String username;

    @Column(nullable = false,length=100)
    private String component;

    @Column(nullable = false,length=100)
    private String activity;

    @Lob
    private String description;

}
