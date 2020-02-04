package com.gtnewhorizons.wands;

import net.minecraft.item.ItemStack;

public class WandCap {

	int capCost;
	String name;
    ItemStack itemStack;

	public WandCap(String name, int capCost) {
	  this(name, thaumcraft.api.wands.WandCap.caps.get(name).getItem(), capCost);
      this.name = name;
      this.capCost = capCost;
	}

	public WandCap(String name, ItemStack itemStack, int capCost) {
	     this.name = name;
	     this.capCost = capCost;
	     this.itemStack = itemStack;
	}

	public String getName() {
		return name;
	}

	public int getCapCost() {
		return capCost;
	}

	public ItemStack getItem() {
		return itemStack;
	}

}
