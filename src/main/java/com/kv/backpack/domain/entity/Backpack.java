package com.kv.backpack.domain.entity;

import com.kv.backpack.domain.entity.common.ItemSize;
import com.kv.backpack.domain.entity.common.enumeration.BackpackSize;
import com.kv.backpack.domain.entity.food.FoodItem;
import com.kv.backpack.domain.entity.things.ThingItem;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "backpack")
public class Backpack extends ItemSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "size")
    @Enumerated(EnumType.STRING)
    private BackpackSize size;

    @ManyToMany
    private List<ThingItem> backpackItems;

    @ManyToMany
    private List<FoodItem> foodItems;
}
