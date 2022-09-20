package kr.sul.kotlinloader

import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

class BukkitKotlinLoader : JavaPlugin(), Listener {
    companion object {
        lateinit var plugin: Plugin
    }


//    private var npcRegistry: NPCRegistry? = null
//    @EventHandler
//    fun onCommand(e: PlayerCommandPreprocessEvent) {
//        if (npcRegistry == null) {
//            npcRegistry = run {
//                val dataStore = SimpleNPCDataStore.create(YamlStorage(File(plugin.dataFolder, "Temp file for citizen.yml")))
//                CitizensAPI.createAnonymousNPCRegistry(dataStore)!!
//            }
//        }
//        if (e.message == "/testmob") {
//            e.isCancelled = true
//            val npc = npcRegistry!!.createNPC(EntityType.ZOMBIE, "Test Mob")
//            npc.setUseMinecraftAI(false)
////            npc.setUseMinecraftAI(true) // 테스트?
//            npc.spawn(e.player.location)
//            npc.navigator.localParameters.useNewPathfinder(true)
//            npc.navigator.localParameters.range(100F)  // setTarget 보다 크게 잡아야 함
//            npc.navigator.localParameters.modifiedSpeed(4F)
//            npc.isProtected = false
//            Bukkit.getScheduler().runTask(plugin) {
//                e.player.performCommand("npc sel")
//                e.player.performCommand("trait sentinel")
//                e.player.performCommand("sentinel addtarget player")
//            }
//        }
//    }


    override fun onEnable() {
        plugin = this
        Bukkit.getPluginManager().registerEvents(this, this)
    }
}