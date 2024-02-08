package com.kv.backpack.mapper;

import com.kv.backpack.Constants;
import com.kv.backpack.domain.entity.Backpack;
import com.kv.backpack.domain.entity.common.enumeration.BackpackSize;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BackpackMapperTest {

    @Test
    void testToBackpackDto() {

        var backpack = Instancio.of(Backpack.class)
                .set(Select.field("id"), 1L)
                .set(Select.field("size"), BackpackSize.MEDIUM)
                .create();
        backpack.setWeight(5.0);

        var backpackDto = BackpackMapper.toBackPackDto(backpack);

        Assertions.assertNotNull(backpackDto);
        Assertions.assertEquals(1L, backpackDto.getId());
        Assertions.assertEquals(String.format(Constants.BACKPACK_SIZE, BackpackSize.MEDIUM.name(), Double.valueOf(BackpackSize.MEDIUM.getValue())), backpackDto.getSize());
    }
}
