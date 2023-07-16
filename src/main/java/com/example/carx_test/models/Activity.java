package com.example.carx_test.models;

import javax.persistence.*;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "activity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "user_id", columnDefinition = "uuid")
    private UUID user;

    @Column(name = "activity")
    private int activity;

    @Column(name = "activity_date")
    private ZonedDateTime activityDate;

}
