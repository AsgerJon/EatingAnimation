package ru.tpsd.eatinganimationmod;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import java.util.Collections;
import java.util.List;

public class DrinkingAnimationClientMod implements ClientModInitializer {

    public static final List<Item> VANILLA_POTIONS = Collections.singletonList(Items.POTION);

    @Override
    public void onInitializeClient() {
        for(Item item : VANILLA_POTIONS) {
            ModelPredicateProviderRegistry.register(item, Identifier.of("drink"), (itemStack, clientWorld, livingEntity, i) -> {
                if (livingEntity == null) {
                    return 0.0F;
                }
                return livingEntity.getActiveItem() != itemStack ? 0.0F : (itemStack.getMaxUseTime(livingEntity) - livingEntity.getItemUseTimeLeft()) / 30.0F;
            });

            ModelPredicateProviderRegistry.register(item, Identifier.of("drinking"), (itemStack, clientWorld, livingEntity, i) -> {
                if (livingEntity == null) {
                    return 0.0F;
                }
                return livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F;
            });
        }
    }

}