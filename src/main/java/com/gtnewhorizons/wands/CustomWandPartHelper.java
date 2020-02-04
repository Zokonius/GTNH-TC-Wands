package com.gtnewhorizons.wands;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.IWandRodOnUpdate;
import thaumcraft.api.wands.StaffRod;
import thaumcraft.api.wands.WandRod;
import thaumcraft.api.wands.WandCap;


public class CustomWandPartHelper {

	public static void makeRod(String name, int capacity, ItemStack item, int cost, IWandRodOnUpdate update, boolean glowing, ResourceLocation texture) {
		WandRod r = new WandRod(name, capacity, item, cost, texture);
		r.setGlowing(glowing);
		if(update!=null) r.setOnUpdate(update);
		WandRod.rods.put(name, r);
	}

	public static void makeStaff(String name, int capacity, ItemStack item, int cost, IWandRodOnUpdate update, boolean glowing, ResourceLocation texture) {
		StaffRod r = new StaffRod(name, capacity, item, cost, texture);
		r.setGlowing(glowing);
		if(update!=null) r.setOnUpdate(update);
		StaffRod.rods.put(name, r);
	}

	public static void makeCap(String name, ItemStack stack, float discount, int cost, ResourceLocation res) {
		WandCap c = new WandCap(name, discount, stack, cost);
		c.setTexture(res);
		WandCap.caps.put(name,c);
	}

	public static void makeCap(String name, ItemStack stack, float discount,List<Aspect> list, float discountSpecial, int cost, ResourceLocation res) {
		WandCap c = new WandCap(name, discount, list, discountSpecial, stack, cost);
		c.setTexture(res);
		WandCap.caps.put(name,c);
	}

}
