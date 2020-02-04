package com.gtnewhorizons.wands;


import net.minecraft.item.ItemStack;
import thaumcraft.api.wands.StaffRod;
import thaumcraft.api.wands.WandRod;

public class WandCore {

	boolean isStaff;
	String name;
	ItemStack rod;
	int baseCost, capCost;
	float sceptreCost;
	WandTier tier;


	public WandCore(String name, WandTier tier, int baseCost, int capCost, float sceptreCost) {
		this(name, name.contains("_staff") ? StaffRod.rods.get(name).getItem():WandRod.rods.get(name).getItem(), tier, baseCost, capCost, sceptreCost);
	}

	public WandCore(String name, ItemStack rod, WandTier tier, int baseCost, int capCost, float sceptreCost) {
		   this.name = name;
		   this.isStaff = name.contains("_staff");
		   this.rod = rod;
		   this.tier = tier;
		   this.baseCost = baseCost;
		   this.capCost = capCost;
		   this.sceptreCost = sceptreCost;

	}

	public ItemStack getItem(){
		if(isStaff) return StaffRod.rods.get(name).getItem();
		else return WandRod.rods.get(name).getItem();
	}

	public String getName() {
		return name;
	}

	public WandTier getTier() {
		return tier;
	}

	public int getBaseCost() {
		return baseCost;
	}

	public int getCapCost() {
		return capCost;
	}

	public float getSceptreCost() {
		return sceptreCost;
	}

}
