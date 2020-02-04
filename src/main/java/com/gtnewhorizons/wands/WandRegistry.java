package com.gtnewhorizons.wands;

import java.util.ArrayList;

import gregtech.api.enums.Materials;
import gregtech.api.util.GT_ModHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class WandRegistry {

	public static ArrayList<WandCore> cores = new ArrayList<>();
	public static ArrayList<WandCap> caps = new ArrayList<>();

	public static void registerWandCore(WandCore core){
		cores.add(core);
	}

	public static void registerWandCap(WandCap cap) {
		caps.add(cap);
	}


}
