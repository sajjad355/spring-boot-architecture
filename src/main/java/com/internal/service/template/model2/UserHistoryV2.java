package com.internal.service.template.model2;

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
@Table(name = "il_user_history")
public class UserHistoryV2 {

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

    @Column(nullable = true)
    private Integer vialId;

    @Lob
    private String description;

}
