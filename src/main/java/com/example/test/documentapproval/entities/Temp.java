package com.example.test.documentapproval.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "temp")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Temp {

    @Id
    @Column(name = "temp")
    private String temp;

    @ManyToOne(fetch = FetchType.EAGER)	// default fetch type: EAGER
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member member;

}
