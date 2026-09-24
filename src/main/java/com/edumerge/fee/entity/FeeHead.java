package com.edumerge.fee.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fee_heads")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeeHead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @Column(nullable = false)
    private Boolean active = true;
}