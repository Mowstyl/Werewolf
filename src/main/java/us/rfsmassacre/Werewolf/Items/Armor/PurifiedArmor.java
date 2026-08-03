package us.rfsmassacre.Werewolf.Items.Armor;

import org.bukkit.Material;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.Recipe;

public class PurifiedArmor extends WerewolfArmor
{
	public PurifiedArmor(Material material)
	{	
		super(material, "PURIFIED_" + material.toString().replace("DIAMOND_", ""));
	}

	@Override
	protected Recipe createRecipe() 
	{
		return new FurnaceRecipe(key, getItemStack(), getItemStack().getType(), 10, 200);
	}

	@Override
	public int getPurity() 
	{
		return getValue("purity.purified." + getItemStack().getType().name().toLowerCase().replace("_", "-"));
	}
	
	@Override
	public int getDefense()
	{
		return getValue("bonus.purified." + getItemStack().getType().name().toLowerCase().replace("_", "-"));
	}
}
