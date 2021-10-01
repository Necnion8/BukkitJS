package com.gmail.necnionch.myplugin.bukkitjs.bukkit;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


/**
 * https://gist.github.com/Lauriichan/e2e1150cd3e3e2c2d398bdaff41c90c1
 */
public class MyEventListener implements Listener {
    private final EventManager hook;

    public MyEventListener(EventManager manager) {
        this.hook = manager;
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.SheepRegrowWoolEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerTakeLecternBookEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.CreatureSpawnEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.inventory.InventoryEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.BlockRedstoneEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.MoistureChangeEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.server.MapInitializeEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityToggleGlideEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.world.WorldUnloadEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityDamageByEntityEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.vehicle.VehicleDestroyEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.AsyncPlayerChatEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.raid.RaidStopEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.SheepDyeWoolEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.AreaEffectCloudApplyEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerLocaleChangeEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.BlockShearEntityEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.BlockPistonExtendEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityPortalEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.BlockBurnEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.BlockFromToEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.AsyncPlayerPreLoginEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.server.RemoteServerCommandEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.BlockFormEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerRecipeDiscoverEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.spigotmc.event.entity.EntityDismountEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityTeleportEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerAnimationEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerBedEnterEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.inventory.InventoryCreativeEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.weather.LightningStrikeEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.inventory.PrepareSmithingEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerUnleashEntityEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerChangedMainHandEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.vehicle.VehicleUpdateEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.BlockFadeEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerToggleFlightEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.inventory.InventoryMoveItemEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerAdvancementDoneEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerToggleSprintEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerGameModeChangeEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.BlockSpreadEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.StriderTemperatureChangeEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityDropItemEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.BlockDispenseEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerBucketEmptyEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityTransformEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.weather.ThunderChangeEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.BlockDispenseArmorEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.inventory.InventoryPickupItemEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.SpawnerSpawnEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.world.ChunkUnloadEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.PlayerLeashEntityEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.vehicle.VehicleEntityCollisionEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityEnterBlockEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.server.PluginEnableEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerEggThrowEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.inventory.InventoryClickEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.ProjectileLaunchEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.FoodLevelChangeEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.server.ServerLoadEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.vehicle.VehicleExitEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.PigZombieAngerEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.server.ServiceRegisterEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.world.LootGenerateEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.BlockPlaceEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerKickEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.ArrowBodyCountChangeEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.weather.WeatherChangeEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.BlockFertilizeEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerUnregisterChannelEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.world.PortalCreateEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerInteractEntityEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.spigotmc.event.player.PlayerSpawnLocationEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.enchantment.PrepareItemEnchantEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.HorseJumpEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.VillagerAcquireTradeEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.hanging.HangingBreakByEntityEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.inventory.FurnaceBurnEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerLevelChangeEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityExplodeEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.VillagerCareerChangeEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.ExpBottleEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.ExplosionPrimeEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.NotePlayEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.spigotmc.event.entity.EntityMountEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.BlockDropItemEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.inventory.InventoryCloseEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityPickupItemEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityDamageByBlockEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerArmorStandManipulateEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.server.ServiceUnregisterEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.inventory.TradeSelectEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityCombustByBlockEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerInteractAtEntityEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityRegainHealthEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityAirChangeEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.vehicle.VehicleDamageEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.inventory.BrewingStandFuelEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityBreedEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityPortalEnterEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerHarvestBlockEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerSwapHandItemsEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.server.ServerCommandEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.SlimeSplitEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.world.WorldInitEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerCommandPreprocessEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.hanging.HangingPlaceEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerVelocityEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityInteractEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerRegisterChannelEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityTargetLivingEntityEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.PotionSplashEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.BlockCookEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.BlockMultiPlaceEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerItemConsumeEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityPotionEffectEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.inventory.BrewEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.raid.RaidTriggerEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.BlockIgniteEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerTeleportEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.enchantment.EnchantItemEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerChangedWorldEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.BlockGrowEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerCommandSendEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.inventory.InventoryDragEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.inventory.FurnaceSmeltEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerDropItemEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.VillagerReplenishTradeEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerItemMendEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityTameEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.server.BroadcastMessageEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.BlockExplodeEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.vehicle.VehicleCreateEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.world.ChunkLoadEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.raid.RaidSpawnWaveEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.BlockPhysicsEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.server.TabCompleteEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.BlockPistonRetractEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.FireworkExplodeEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerMoveEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.world.ChunkPopulateEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerStatisticIncrementEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerFishEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.server.ServerListPingEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.CreeperPowerEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.CauldronLevelChangeEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityCombustEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityDamageEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.ItemDespawnEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.SignChangeEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.hanging.HangingBreakEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityResurrectEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.ProjectileHitEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityEnterLoveModeEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerResourcePackStatusEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerShearEntityEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerRiptideEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityToggleSwimEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerBedLeaveEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.BlockExpEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.inventory.PrepareItemCraftEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerBucketFillEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerQuitEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityBreakDoorEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.raid.RaidFinishEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EnderDragonChangePhaseEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityPoseChangeEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerInteractEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.BlockCanBuildEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerJoinEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.world.WorldLoadEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.world.StructureGrowEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.world.SpawnChangeEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.BlockDamageEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.PlayerDeathEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.vehicle.VehicleBlockCollisionEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerItemBreakEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityDeathEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.PigZapEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityTargetEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityPortalExitEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerToggleSneakEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.server.PluginDisableEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerItemDamageEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.FluidLevelChangeEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.ItemSpawnEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.SpongeAbsorbEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntitySpawnEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerItemHeldEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerExpChangeEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityChangeBlockEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityUnleashEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.world.TimeSkipEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.vehicle.VehicleMoveEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.inventory.FurnaceExtractEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntitySpellCastEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.inventory.InventoryOpenEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.BatToggleSleepEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityShootBowEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.LingeringPotionSplashEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.BlockBreakEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.world.WorldSaveEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerRespawnEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.EntityBlockFormEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerEditBookEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.ItemMergeEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.vehicle.VehicleEnterEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.entity.EntityCombustByEntityEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerPortalEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.block.LeavesDecayEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.inventory.CraftItemEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.player.PlayerLoginEvent event) {
        hook.handleEvent(event);
    }

    @EventHandler
    public void onEvent(org.bukkit.event.inventory.PrepareAnvilEvent event) {
        hook.handleEvent(event);
    }

}
