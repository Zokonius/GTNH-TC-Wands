package com.gtnewhorizons.wands;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregtech.api.util.GT_ModHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.StaffRod;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;

@Mod(modid="gtnhtcwands", name="GTNH-TC-Wands", version="1.0.0", dependencies=ThaumcraftWands.dependencies)
public class ThaumcraftWands {

	final static String dependencies=
			                  "required-after:Thaumcraft;"
	                         +"required-after:dreamcraft;"
			                 +"required-after:gregtech;"
	                         +"required-after:TwilightForest;"
	                         +"after:ForbiddenMagic;"
	                         +"after:TaintedMagic;"
	                         +"after:BloodArsenal;"
	                         +"after:thaumicbases;"
	                         +"after:ThaumicExploration;"
	                         +"after:ThaumicTinkerer;";
	@Instance
	public static ThaumcraftWands instance = new ThaumcraftWands();

	@EventHandler
	public void postinit(FMLPostInitializationEvent e) {
		setConstants();
		makeWands();
	}

	private void setConstants() {
		conductor = new ItemStack[]{
				GT_ModHandler.getModItem("TwilightForest", "item.nagaScale", 1),
				GT_ModHandler.getModItem("dreamcraft", "item.LichBone", 1),
				GT_ModHandler.getModItem("TwilightForest", "item.fieryBlood", 1),
				GT_ModHandler.getModItem("TwilightForest", "item.fieryTears", 1),
				GT_ModHandler.getModItem("TwilightForest", "item.carminite", 1),
				GT_ModHandler.getModItem("dreamcraft", "item.SnowQueenBlood", 1)
			};

		screw = new ItemStack[]{
				   OreDictionary.getOres("screwAluminium").get(0),
				   OreDictionary.getOres("screwStainlessSteel").get(0),
				   OreDictionary.getOres("screwTitanium").get(0),
				   OreDictionary.getOres("screwTungstenSteel").get(0),
				   OreDictionary.getOres("screwChrome").get(0),
				   OreDictionary.getOres("screwIridium").get(0),
				   OreDictionary.getOres("screwOsmium").get(0),
				};

	}

	final static String[] cores = new String[]{
			"wood",
			"greatwood",
			"reed",
			"blaze",
			"obsidian",
			"ice",
			"quartz",
			"bone",
			"silverwood",
			"greatwood_staff",
			"reed_staff",
			"blaze_staff",
			"obsidian_staff",
			"ice_staff",
			"quartz_staff",
			"bone_staff",
			"silverwood_staff",
			"ICHORCLOTH",
			"primal_staff",
			"blood_wood",
			"blood_wood_staff",
			"blood",
			"blood_staff",
			"infernal",
			"tbthaumium",
			"tbvoid",
			"livingwood",
			"dreamwood",
			"dreamwood_staff",
			"witchwood",
			"witchwood_staff",
			"AMBER",
			"AMBER_staff",
			"TRANSMUTATION",
			"TRANSMUTATION_staff",
			"warpwood",
			"warpwood_staff"
	};

	final static String[] caps = new String[]{
			"iron",
			"copper",
			"gold",
			"silver",
			"thaumium",
			"void",
			"ICHOR",
			"alchemical",
			"blood_iron",
			"cloth",
			"crimsoncloth",
			"shadowcloth",
			"shadowmetal",
			"thauminite",
			"manasteel",
			"terrasteel",
			"elementium",
			"vinteum",
			"SOJOURNER",
			"MECHANIST"
	};

	static ItemStack[] conductor = null;

    static	ItemStack[] screw = null;



	public static void makeWands(){
		for(String rod:cores)
		 for(String cap:caps)
		  if((WandCap.caps.get(cap)!=null) && (getWandRod(rod)!=null)){
				int meta = WandCap.caps.get(cap).getCraftCost()*getWandRod(rod).getCraftCost();
				System.out.println(cap+ "-"+ rod);
				ItemStack wand = GT_ModHandler.getModItem("Thaumcraft", "WandCasting", 1, meta);
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setString("rod", rod);
				nbt.setString("cap", cap);
				wand.setTagCompound(nbt);

				AspectList list = new AspectList();
				for(Aspect a:Aspect.getPrimalAspects())
				list.add(a, getVisCost(rod, cap, false));

				ThaumcraftApi.addArcaneCraftingRecipe("ROD_"+rod, wand, list, getRecipe(rod, cap, false));

				list = new AspectList();
				for(Aspect a:Aspect.getPrimalAspects())
				list.add(a, getVisCost(rod, cap, true));
                wand.setItemDamage((int) (wand.getItemDamage()*1.5F));
				nbt.setByte("sceptre", (byte) 1);
				ThaumcraftApi.addArcaneCraftingRecipe("SCEPTRE", wand, list, getRecipe(rod, cap, true));
			}
	}

	private static int getVisCost(String rod, String cap, boolean sceptre) {
		int cost = 0;
		int capcost = WandCap.caps.get(cap).getCraftCost();
		int rodcost = getWandRod(rod).getCraftCost();
		int rodmod = 5;
		float sceptremod = 2F;

		 if(cap=="cloth")capcost=4;
		 if(cap=="shadowcloth")capcost=6;
		 if(cap=="bloodiron"||cap=="crimsoncloth"||cap=="thauminite")capcost=7;
		 if(rod=="livingwood"||rod=="dreamwood"||rod=="witchwood")rodcost=9;

		if(rodcost<=3||rod=="AMBER") {
			 cost= rod=="wood" ? 0 : 20;
		}

		if(rodcost==6||rod=="AMBER_staff") {
			 cost= rod=="tbthaumium" ? 60 : 50;
			 rodmod=10;
			 sceptremod=1.5F;
		}

		if(rodcost==8||rodcost==9){
			cost=75;
			rodmod=15;
			sceptremod=1.33F;
		}

		if(rodcost==10) {
			cost=250;
			rodmod=25;
			sceptremod=1.2F;
		}

		if(rodcost==12||rodcost==14||rodcost==15) {
			cost=125;
			rodmod=15;
			sceptremod=1.5F;
		}

		if(rod=="bloodwood"||rodcost==24||rodcost==25) {
			cost=150;
		    rodmod= rod=="bloodwood" ? 20 : 15;
			sceptremod=1.2F;
		}

		if(rodcost==16) {
			cost= rod=="warpwood" ? 135 : 130;
			rodmod=15;
			sceptremod=7/6;
		}

		if(rodcost==20) {
			cost=200;
			rodmod=25;
			sceptremod=1.25F;
		}

		if(rodcost>=27) {
			cost=175;
			rodmod=20;
			sceptremod=1.66F;
		}

		for(int i=1;!(i==10);i++) {
			if(i==capcost)break;
			else {
				if(i!=8)cost+=rodmod;
			}
		}
		if(rod=="wood"&&cap=="iron"&&sceptre)return 10;
		if(cap=="terrasteel")return getVisCost(rod, "void", sceptre)/6;
        if(sceptre) return Math.round(cost*sceptremod);
		return cost;

	}

	private static Object[] getRecipe(String rod, String cap, boolean sceptre) {
		ItemStack core;
		if(rod.contains("_staff"))core = StaffRod.rods.get(rod).getItem();
		else core = WandRod.rods.get(rod).getItem();
		if(!sceptre)
			return new Object[] {
				"MSC","SRS","CSM",
				Character.valueOf('M'), getConductorfromName(rod), Character.valueOf('C'), getGTWandCap(cap),
				Character.valueOf('S'), getScrewfromName(rod), Character.valueOf('R'),core
		};

		else
			return new Object[] {
				"MCP","SRC","CSM",
				Character.valueOf('M'), getConductorfromName(rod), Character.valueOf('C'), getGTWandCap(cap),
				Character.valueOf('P'), GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 1, 15),
				Character.valueOf('S'), getScrewfromName(rod), Character.valueOf('R'),core
		};
	}

	private static ItemStack getScrewfromName(String s){
		int capacity;
		if(s=="wood")return screw[0];

		if(s.contains("_staff")) {
			capacity = getWandRod(s).getCapacity();
			if(capacity<=25)return screw[1];
			if(capacity==125) return screw[2];
			if(capacity==175||capacity==200||capacity==500) return screw[3];
			if(s=="primal_staff")return screw[5];
			return screw[4];
		}

		else {
			capacity = getWandRod(s).getCapacity();
			if(capacity<=50)return screw[1];
			if(capacity<=80)return screw[2];
			if(capacity==1000)return screw[6];
			return screw[3];
		}

	}

	private static ItemStack getConductorfromName(String s){
			int capacity;
			if(s=="wood")return conductor[0];

			if(s.contains("_staff")) {
				capacity = getWandRod(s).getCapacity();
				if(capacity<=25)return conductor[1];
				if(capacity==125) return conductor[2];
				if(capacity==175||capacity==200||capacity==500) return conductor[3];
				return conductor[4];
			}

			else {
				capacity = getWandRod(s).getCapacity();
				if(capacity<=50)return conductor[1];
				if(capacity<=80)return conductor[2];
				if(capacity==1000)return conductor[5];
				return conductor[3];
			}

		}

	private static WandRod getWandRod(String s) {
		if(s.contains("_staff"))return StaffRod.rods.get(s);
		else return WandRod.rods.get(s);
	}

	private static ItemStack getGTWandCap(String s) {
		String n=null;
		switch(s){
		default:
		case "iron":n="item.IronWandCap";break;
		case "copper":n="item.CopperWandCap";break;
		case "gold":n="item.GoldWandCap";break;
		case "silver":n="item.ChargedSilverWandCap";break;
		case "thaumium":n="item.ChargedThaumiumWandCap";break;
		case "void":n="item.ChargedVoidWandCap";break;
		case "ICHOR":n="item.IchoriumCap";break;
		case "alchemical":n="item.WandCapAlchemical";break;
		case "blood_iron":n="item.WandCapBloodIron";break;
		case "cloth":n="item.EnchantedClothCap";break;
		case "crimsoncloth":n="item.CrimsonStainedClothCap";break;
		case "shadowcloth":n="item.ShadowImbuedClothCap";break;
		case "shadowmetal":n="item.ShadowmetalCap";break;
		case "thauminite":n="item.WandCapThauminite";break;
		case "manasteel":n="item.WandCapManaSteel";break;
		case "terrasteel":n="item.WandCapTerraSteel";break;
		case "elementium":n="item.WandCapElementium";break;
		case "vinteum":n="item.WandCapVinteum";break;
		case "SOJOURNER":n="item.ChargedSojournerWandCap";break;
		case "MECHANIST":n="item.ChargedMechanistWandCap";break;
		}
		return GT_ModHandler.getModItem("dreamcraft", n, 1);
	}


}
