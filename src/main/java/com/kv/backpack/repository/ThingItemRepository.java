package com.kv.backpack.repository;

import com.kv.backpack.domain.entity.things.ThingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThingItemRepository extends JpaRepository<ThingItem, Long> {
}
