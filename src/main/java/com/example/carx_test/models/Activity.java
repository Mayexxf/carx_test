package com.example.carx_test.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "activity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "user_id")
    private UUID user_id;

    @Column(name = "activity")
    private int activity;

    @Column(name = "activity_date")
    private ZonedDateTime activity_date;

}
