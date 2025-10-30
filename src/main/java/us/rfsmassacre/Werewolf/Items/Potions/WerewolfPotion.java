package us.rfsmassacre.Werewolf.Items.Potions;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffectType;
import us.rfsmassacre.Werewolf.Items.WerewolfItem;

@SuppressWarnings("deprecation")
public abstract class WerewolfPotion extends WerewolfItem
{	
	private boolean splash;
	private PotionEffectType effectType;
	
	//Constructs the Werewolf Potion based on its type
	public WerewolfPotion(String name, boolean splash, Color color, PotionEffectType effectType)
	{	
		super(Material.POTION, name);
		
		setSplash(splash);
		Material material = this.splash ? Material.SPLASH_POTION : Material.POTION;
		this.item.setType(material);

		/*
		try
		{
			Material material = this.splash ? Material.SPLASH_POTION : Material.POTION;
			this.item.setType(material);
		}
		catch (NoSuchFieldError exception)
		{
			//This means it's running 1.8 and requires the use of potion objects
			//and convert it to an item stack in order to keep it all consistent
			Potion potion = new Potion(this.potionType, 1, this.splash);
			potion.setType(this.potionType);
			ItemStack itemStack = potion.toItemStack(1);
			itemStack.setItemMeta(itemStack.getItemMeta());
			this.item = itemStack;

			this.setDisplayName(data.getItemName(name));
			this.setItemLore(data.getItemLore(name));
		}
		 */
		
		try
		{
			setPotionColor(color);
		}
		catch (NoSuchMethodError exception)
		{
			//This means it's running 1.10 or lower and requires the old methods to change colors.
			setMainEffect(effectType);
		}
		
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
	
	/*
	 * Used for Spigot 1.9-1.10
	 */
	public void setMainEffect(PotionEffectType effect)
	{
		PotionMeta meta = (PotionMeta)getItemStack().getItemMeta();
		meta.setMainEffect(effect);
		this.item.setItemMeta(meta);
	}
	
	public boolean isSplash() 
	{
		return splash;
	}

	private void setSplash(boolean splash) 
	{
		this.splash = splash;
	}
}
