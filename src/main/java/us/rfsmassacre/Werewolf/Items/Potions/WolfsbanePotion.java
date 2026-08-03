package us.rfsmassacre.Werewolf.Items.Potions;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapelessRecipe;

import us.rfsmassacre.Werewolf.Managers.ItemManager;

@SuppressWarnings("deprecation")
public class WolfsbanePotion extends WerewolfPotion
{
	public WolfsbanePotion() 
	{
		super("WOLFSBANE_POTION", true, Color.RED);
	}
	
	@Override
	protected Recipe createRecipe() 
	{
		ShapelessRecipe recipe = new ShapelessRecipe(key, getItemStack());
		
		recipe.addIngredient(Material.GLASS_BOTTLE);
		recipe.addIngredient(Material.MILK_BUCKET);
		recipe.addIngredient(Material.CARROT);
		recipe.addIngredient(Material.GUNPOWDER); //Enum was changed in 1.13
		recipe.addIngredient(Material.CACTUS);
		
		return recipe;
	}
}
