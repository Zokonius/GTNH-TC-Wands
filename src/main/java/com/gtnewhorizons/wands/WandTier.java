package com.gtnewhorizons.wands;

import gregtech.api.enums.Materials;
import gregtech.api.util.GT_ModHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public enum WandTier {

	//	LV( 1 , null),
		MV( 2 , GT_ModHandler.getModItem("TwilightForest", "item.nagaScale", 1, 0, new ItemStack(Items.wheat))),
		HV( 3 , GT_ModHandler.getModItem("dreamcraft", "item.LichBone", 1, 0, new ItemStack(Items.carrot))),
		EV( 4 , GT_ModHandler.getModItem("TwilightForest", "item.fieryBlood", 1, 0, new ItemStack(Items.potato))),
		IV( 5 , GT_ModHandler.getModItem("TwilightForest", "item.fieryTears", 1, 0, new ItemStack(Items.poisonous_potato))),
		LUV(6 , GT_ModHandler.getModItem("TwilightForest", "item.carminite", 1, 0, new ItemStack(Items.apple))),
		ZPM(7 , GT_ModHandler.getModItem("TwilightForest", "item.carminite", 1, 0, new ItemStack(Items.apple))),
		UV( 8 , GT_ModHandler.getModItem("dreamcraft", "item.SnowQueenBlood", 1,0, new ItemStack(Items.cake)));
	//	UHV(9 , null),
	//	UEV(10, null);

		 private int tier;
		 private ItemStack cat;

		 private WandTier(int tier, ItemStack cat) {
			 this.tier = tier;
			 this.cat = cat;
		 }

		 public int getTier() {
			return tier;
		 }

		 public Materials getMaterial() {
			 return gregtech.api.enums.Tier.ELECTRIC[tier].getMaterial();
		 }

		 public ItemStack getCatalyst() {
			 return cat;
		 }
}

