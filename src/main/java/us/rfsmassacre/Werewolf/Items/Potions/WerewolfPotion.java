package us.rfsmassacre.Werewolf.Items.Potions;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffectType;
import us.rfsmassacre.Werewolf.Items.WerewolfItem;

public abstract class WerewolfPotion extends WerewolfItem
{	
	private boolean splash;
	
	//Constructs the Werewolf Potion based on its type
	public WerewolfPotion(String name, boolean splash, Color color)
	{	
		super(Material.POTION, name);
		
		this.splash = splash;
		Material material = this.splash ? Material.SPLASH_POTION : Material.POTION;
		this.item = item.withType(material);
		setPotionColor(color);
		
		//Running it on this level should make the color kick in before the recipe is created.
		this.recipe = createRecipe();
	}
	
	/*
	 * Used for Spigot 1.11+
	 */
	public void setPotionColor(Color color)
	{
		PotionMeta meta = (PotionMeta) getItemStack().getItemMeta();
		meta.setColor(color);
		//meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		this.item.setItemMeta(meta);
	}
	public Color getPotionColor()
	{
		return ((PotionMeta) getItemStack().getItemMeta()).getColor();
	}
	
	public boolean isSplash() 
	{
		return splash;
	}
}
