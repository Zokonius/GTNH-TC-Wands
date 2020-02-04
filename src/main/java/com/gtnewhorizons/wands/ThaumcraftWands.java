package com.gtnewhorizons.wands;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregtech.api.util.GT_ModHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.IWandRodOnUpdate;
import thaumcraft.api.wands.StaffRod;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.lib.crafting.ArcaneSceptreRecipe;
import thaumcraft.common.lib.crafting.ArcaneWandRecipe;

@Mod(modid="gtnhtcwands", name="GTNH-TC-Wands", version="1.0.2", dependencies=ThaumcraftWands.dependencies)
public class ThaumcraftWands {

	final static String dependencies="required-after:Thaumcraft;"
									+"required-after:dreamcraft;"
									+"required-after:gregtech;"
									+"required-after:TwilightForest;"
									+"after:ForbiddenMagic;"
									+"after:TaintedMagic;"
									+"after:BloodArsenal;"
									+"after:thaumicbases;"
									+"after:ThaumicExploration;"
									+"after:ThaumicTinkerer;";

	static final int LV  = 1,
			         MV  = 2,
			         HV  = 3,
			         EV  = 4,
			         IV  = 5,
			         LUV = 6,
			         ZPM = 7,
			         UV  = 8;



	public static ArrayList<WandCore> cores = new ArrayList<>();
	public static ArrayList<WandCap> caps = new ArrayList<>();


	@EventHandler
	public void postinit(FMLPostInitializationEvent e) {
		removeTCWands();
		addWandParts();
		setupWandParts();
		makeWands();
	}

	public static void setupWandParts() {

	   ItemStack NAGA = GT_ModHandler.getModItem("TwilightForest", "item.nagaScale", 1, 0, new ItemStack(Items.wheat)),
			     LICH = GT_ModHandler.getModItem("dreamcraft", "item.LichBone", 1, 0, new ItemStack(Items.carrot)),
			     HYDRA = GT_ModHandler.getModItem("TwilightForest", "item.fieryBlood", 1, 0, new ItemStack(Items.potato)),
			     GHAST = GT_ModHandler.getModItem("TwilightForest", "item.fieryTears", 1, 0, new ItemStack(Items.poisonous_potato)),
			     CARMINITE = GT_ModHandler.getModItem("TwilightForest", "item.carminite", 1, 0, new ItemStack(Items.apple)),
			     QUEEN = GT_ModHandler.getModItem("dreamcraft", "item.SnowQueenBlood", 1,0, new ItemStack(Items.cake));

		cores.add(new WandCore("wood", GT_ModHandler.getModItem("Forestry", "oakStick", 1, 0, new ItemStack(Items.stick)), MV, NAGA, 0, 5, 2F));
		cores.add(new WandCore("greatwood", HV, LICH, 20, 5, 2F));
		cores.add(new WandCore("reed", EV, HYDRA, 50, 10, 1.5F));
		cores.add(new WandCore("blaze", EV, HYDRA, 50, 10, 1.5F));
		cores.add(new WandCore("obsidian", EV, HYDRA, 50, 10, 1.5F));
		cores.add(new WandCore("ice", EV, HYDRA, 50, 10, 1.5F));
		cores.add(new WandCore("quartz", EV, HYDRA, 50, 10, 1.5F));
		cores.add(new WandCore("silverwood", IV, GHAST, 75, 15, 1.4F));
		cores.add(new WandCore("greatwood_staff", EV, HYDRA, 75, 15, 1.4F));
		cores.add(new WandCore("reed_staff", IV, GHAST, 125, 15, 1.5F));
		cores.add(new WandCore("blaze_staff", IV, GHAST, 125, 15, 1.5F));
		cores.add(new WandCore("obsidian_staff", IV, GHAST, 125, 15, 1.5F));
		cores.add(new WandCore("ice_staff", IV, GHAST, 125, 15, 1.5F));
		cores.add(new WandCore("quartz_staff", IV, GHAST, 125, 15, 1.5F));
		cores.add(new WandCore("bone_staff", IV, GHAST, 125, 15, 1.5F));
		cores.add(new WandCore("silverwood_staff", LUV, CARMINITE, 150, 15 , 1.2F));
		cores.add(new WandCore("primal_staff", ZPM, CARMINITE, 175, 20, 1.6F));

		cores.add(new WandCore("profane", HV, LICH, 25, 5, 2F));
		cores.add(new WandCore("tainted", IV, GHAST, 125, 15, 1.5F));
		cores.add(new WandCore("blood", IV, GHAST, 125, 15, 1.5F));
		cores.add(new WandCore("blood_staff", LUV, CARMINITE, 150, 15, 1.2F));
		cores.add(new WandCore("infernal", IV, GHAST, 125, 15, 1.5F));
		cores.add(new WandCore("livingwood", IV, GHAST, 75, 15, 1.4F));
		cores.add(new WandCore("dreamwood", IV, GHAST, 75, 15, 1.4F));
		cores.add(new WandCore("dreamwood_staff", LUV, CARMINITE, 150, 15, 1.2F));
		cores.add(new WandCore("witchwood", IV, GHAST, 75, 15, 1.4F));
		cores.add(new WandCore("witchwood_staff",LUV, CARMINITE, 150, 15, 1.2F));

		cores.add(new WandCore("ICHORCLOTH", UV, QUEEN, 250, 25, 1.2F));

		cores.add(new WandCore("blood_wood",IV, GHAST, 130, 15, 1.2F));
		cores.add(new WandCore("blood_wood_staff", LUV, CARMINITE, 175, 20, 1.6F));

		cores.add(new WandCore("warpwood", IV, GHAST, 135, 15, 1.2F));
		cores.add(new WandCore("warpwood_staff", IV, GHAST, 200, 25, 1.2F));

		cores.add(new WandCore("tbthaumium", EV, HYDRA, 60, 10, 1.5F));
		cores.add(new WandCore("tbvoid", IV, GHAST, 160, 15, 1.2F));

		cores.add(new WandCore("AMBER", HV, LICH, 20, 5, 2F));
		cores.add(new WandCore("AMBER_staff", EV, HYDRA, 75, 15, 1.4F));
		cores.add(new WandCore("TRANSMUTATION", EV, HYDRA, 50, 10, 1.5F));
		cores.add(new WandCore("TRANSMUTATION_staff", IV, GHAST, 125, 15, 1.5F));
	//	cores.add(new WandCore("BREAD",HV, LICH, 20, 5, 2F));
	//	cores.add(new WandCore("ledox", ZPM, CARMINITE, 250, 15, 1.4F));


		caps.add(new WandCap("iron", 0));
		caps.add(new WandCap("copper", 1));
		caps.add(new WandCap("gold", 2));
		caps.add(new WandCap("silver", 3));
		caps.add(new WandCap("thaumium", 5));
		caps.add(new WandCap("void", 7));

		caps.add(new WandCap("manasteel", 5));
		caps.add(new WandCap("terrasteel", 1));
		caps.add(new WandCap("elementium", 7));
		caps.add(new WandCap("vinteum", 5));
		caps.add(new WandCap("alchemical",6));

		caps.add(new WandCap("ICHOR",8));

		caps.add(new WandCap("blood_iron",6));

		caps.add(new WandCap("cloth", 3));
		caps.add(new WandCap("crimsoncloth", 6));
		caps.add(new WandCap("shadowcloth", 5));
		caps.add(new WandCap("shadowmetal", 8));

		caps.add(new WandCap("thauminite", 6));

		caps.add(new WandCap("SOJOURNER", 5));
		caps.add(new WandCap("MECHANIST", 5));

	//  caps.add(new WandCap("enderium", 7));

	}

	public void addWandParts() {

		// Override Tainted Magic Caps
		makeCap("shadowcloth", GT_ModHandler.getModItem("TaintedMagic", "ItemWandCap", 1, 3), 0.85F, 7, new ResourceLocation("taintedmagic", "textures/models/ModelWAND_CAP_SHADOW_CLOTH.png"));
		makeCap("crimsoncloth", GT_ModHandler.getModItem("TaintedMagic", "ItemWandCap", 1, 2), 0.80F, 9, new ResourceLocation("taintedmagic", "textures/models/ModelWAND_CAP_CRIMSON_CLOTH.png"));

		//Example Rods and Caps
	//	makeRod("ledox", 300, new ItemStack(Items.carrot), 10, null, true, new ResourceLocation("gtnh","textures"));
	//	makeCap(new ItemStack(Items.baked_potato), "enderium", 0.99F, 12, new ResourceLocation(""));

 	}


	public static void makeWands(){
		for(WandCore core:cores)
		 for(WandCap cap:caps) {
				ItemStack wand = GT_ModHandler.getModItem("Thaumcraft", "WandCasting", 1, 0);

				int wandCost = getCost(core, cap, false);
				int sceptreCost = getCost(core, cap, true);

				NBTTagCompound wandNbt = new NBTTagCompound();
				wandNbt.setString("rod", core.getName());
				wandNbt.setString("cap", cap.getName());
				wand.setTagCompound(wandNbt);
				wand.setItemDamage(getCost(core, cap, false));

				AspectList wandAspects = new AspectList();
				AspectList sceptreAspects = new AspectList();
				for(Aspect a:Aspect.getPrimalAspects()) wandAspects.add(a, wandCost);
				for(Aspect a:Aspect.getPrimalAspects()) sceptreAspects.add(a, sceptreCost);

				if(core.getName() == "warpwood") ThaumcraftApi.addArcaneCraftingRecipe("RoD_WarpwoodGTNH", wand, wandAspects, getRecipe(core, cap, false));

				else if(core.getName() == "warpwood_staff") ThaumcraftApi.addArcaneCraftingRecipe("RoD_Warpwood_StaffGTNH"+core, wand, wandAspects, getRecipe(core, cap, false));

				else ThaumcraftApi.addArcaneCraftingRecipe("ROD_"+core, wand, wandAspects, getRecipe(core, cap, false));

				NBTTagCompound sceptreNbt = new NBTTagCompound();
				sceptreNbt.setString("rod", core.getName());
				sceptreNbt.setString("cap", cap.getName());
				sceptreNbt.setByte("sceptre", (byte) 1);
				wand.setTagCompound(sceptreNbt);
				wand.setItemDamage(getCost(core, cap, true));

				ThaumcraftApi.addArcaneCraftingRecipe("SCEPTRE", wand, sceptreAspects, getRecipe(core, cap, true));

		}
	}

	public static Object[] getRecipe(WandCore rod, WandCap cap, boolean sceptre) {
		if(sceptre) return new Object[] {
				"MCP",
				"SRC",
				"CSM",
				Character.valueOf('R'), rod.getItem(),
				Character.valueOf('M'), rod.getConductor(),
				Character.valueOf('S'), rod.getScrew(),
				Character.valueOf('C'), cap.getItem(),
				Character.valueOf('P'), GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 1, 15, new ItemStack(Items.sugar))
		};

		else return new Object[] {
				"MSC",
				"SRS",
				"CSM",
				Character.valueOf('R'), rod.getItem(),
				Character.valueOf('M'), rod.getConductor(),
				Character.valueOf('S'), rod.getScrew(),
				Character.valueOf('C'), cap.getItem()
		};
	}

	public static int getCost(WandCore core, WandCap cap, boolean sceptre){
		int cost = core.getBaseCost()+core.getCapCost()*cap.getCapCost();
		return (int) (sceptre ? cost:cost*core.getSceptreCost());
	}

	public static void makeRod(String name,int capacity, ItemStack item, int cost, IWandRodOnUpdate update, boolean glowing, ResourceLocation texture) {
		WandRod r = new WandRod(name, capacity, item, cost, texture);
		r.setGlowing(glowing);
		if(update!=null) r.setOnUpdate(update);
		WandRod.rods.put(name, r);
	}

	public static void makeStaff(String name,int capacity, ItemStack item, int cost, IWandRodOnUpdate update, boolean glowing, ResourceLocation texture) {
		StaffRod r = new StaffRod(name, capacity, item, cost, texture);
		r.setGlowing(glowing);
		if(update!=null) r.setOnUpdate(update);
		StaffRod.rods.put(name, r);
	}

	public static void makeCap(String name, ItemStack stack, float discount, int cost, ResourceLocation res) {
		thaumcraft.api.wands.WandCap c = new thaumcraft.api.wands.WandCap(name, discount, stack, cost);
		c.setTexture(res);
		thaumcraft.api.wands.WandCap.caps.put(name,c);
	}

	public static void makeCap(String name, ItemStack stack, float discount,List<Aspect> list, float discountSpecial, int cost, ResourceLocation res) {
		thaumcraft.api.wands.WandCap c = new thaumcraft.api.wands.WandCap(name, discount, list, discountSpecial, stack, cost);
		c.setTexture(res);
		thaumcraft.api.wands.WandCap.caps.put(name,c);
	}

	public static void removeTCWands() {
		ArrayList<Object> l1 = new ArrayList<Object>();
		try {
          Field f = ThaumcraftApi.class.getDeclaredField("craftingRecipes");
          f.setAccessible(true);
          ArrayList<Object> l2 = (ArrayList) f.get(ArrayList.class);
          for(Object r: l2)
           if(!(r instanceof ArcaneWandRecipe||r instanceof ArcaneSceptreRecipe))
            l1.add(r);
          f.set(ArrayList.class, l1);
		}
		catch(Exception e) {}
		}

}
