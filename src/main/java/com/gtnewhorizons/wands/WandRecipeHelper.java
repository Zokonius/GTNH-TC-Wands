package com.gtnewhorizons.wands;

import gregtech.api.util.GT_ModHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class WandRecipeHelper {

	public static Object[] getWandRecipe(WandCore rod, WandCap cap) {
		return new Object[] {
				"MCP",
				"SRC",
				"CSM",
				Character.valueOf('R'), rod.getItem(),
				Character.valueOf('M'), rod.getTier().getCatalyst(),
				Character.valueOf('S'), "screw"+rod.getTier().getMaterial().mName,
				Character.valueOf('C'), cap.getItem(),
				Character.valueOf('P'), GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 1, 15, new ItemStack(Items.sugar))
		};
	}

	public static Object[] getSceptreRecipe(WandCore rod, WandCap cap) {
		return new Object[] {
				"MSC",
				"SRS",
				"CSM",
				Character.valueOf('R'), rod.getItem(),
				Character.valueOf('M'), rod.getTier().getCatalyst(),
				Character.valueOf('S'), "screw"+rod.getTier().getMaterial().mName,
				Character.valueOf('C'), cap.getItem()
		};
	}



}
