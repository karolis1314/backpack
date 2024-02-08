package com.kv.backpack.domain.entity.things;

import com.kv.backpack.domain.entity.common.ItemSize;
import com.kv.backpack.domain.entity.common.enumeration.Material;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "thing")
public class ThingItem extends ItemSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @Column(name = "fits_to_small_section")
    private Boolean fitsToSmallSection;

    @Column(name = "material")
    @Enumerated(EnumType.STRING)
    private Material material;

    @Column(name = "season")
    private String season;

    @ElementCollection
    @CollectionTable(name = "thing_attributes", joinColumns = @JoinColumn(name = "thing_id"))
    @Column(name = "attribute")
    private List<String> attributes;

}
