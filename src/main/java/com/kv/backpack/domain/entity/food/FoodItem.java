package com.kv.backpack.domain.entity.food;

import com.kv.backpack.domain.entity.common.ItemSize;
import com.kv.backpack.domain.entity.common.enumeration.Flavour;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "food")
public class FoodItem extends ItemSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "calories")
    private Long calories;

    @Column(name = "flavour")
    @Enumerated(EnumType.STRING)
    private Flavour flavour;

    @Column(name = "liquid")
    private Boolean liquid;
}
