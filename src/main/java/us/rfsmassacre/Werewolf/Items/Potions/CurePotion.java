package us.rfsmassacre.Werewolf.Items.Potions;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.potion.PotionEffectType;


public class CurePotion extends WerewolfPotion
{
	public CurePotion() 
	{
		super("CURE_POTION", false, Color.WHITE);
	}

	@Override
	protected Recipe createRecipe() 
	{
		ShapelessRecipe recipe = new ShapelessRecipe(key, getItemStack());
		
		recipe.addIngredient(Material.POPPY); //Enum was changed in 1.13
		recipe.addIngredient(Material.GLASS_BOTTLE);
		recipe.addIngredient(Material.MILK_BUCKET);
		
		return recipe;
	}
}
