package com.gtnewhorizons.wands;

import gregtech.api.enums.Materials;
import gregtech.api.enums.Tier;
import net.minecraft.item.ItemStack;
import thaumcraft.api.wands.StaffRod;
import thaumcraft.api.wands.WandRod;

public class WandCore {

	boolean isStaff;
	String name;
	Materials material;
	ItemStack rod, conductor;
	int baseCost, capCost;
	float sceptreCost;


	public WandCore(String name, int tier, ItemStack conductor, int baseCost, int capCost, float sceptreCost) {
      new WandCore(name, Tier.ELECTRIC[tier].getMaterial(), conductor, baseCost, capCost, sceptreCost);
	}

	public WandCore(String name, ItemStack rod, int tier, ItemStack conductor, int baseCost, int capCost, float sceptreCost) {
	      new WandCore(name, rod, Tier.ELECTRIC[tier].getMaterial(), conductor, baseCost, capCost, sceptreCost);
		}

	public WandCore(String name, Materials material, ItemStack conductor, int baseCost, int capCost, float sceptreCost) {
	 new WandCore(name, name.contains("_staff") ? StaffRod.rods.get(name).getItem():WandRod.rods.get(name).getItem(), material, conductor, baseCost, capCost, sceptreCost);
	}

	public WandCore(String name, ItemStack rod, Materials material, ItemStack conductor, int baseCost, int capCost, float sceptreCost) {
		   this.name = name;
		   this.isStaff = name.contains("_staff");
		   this.rod = rod;
		   this.material = material;
		   this.conductor = conductor;
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

	public ItemStack getConductor() {
		return conductor;
	}

	public String getScrew(){
		return "screw"+material.mName;
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
