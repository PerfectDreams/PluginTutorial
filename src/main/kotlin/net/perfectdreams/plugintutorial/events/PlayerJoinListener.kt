package net.perfectdreams.plugintutorial.events

import net.perfectdreams.plugintutorial.PluginTutorial
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

// Novamente estamos usando o mágico ":", yay!!
// Desta vez, estamos implementando a interface de "Listener"!
class PlayerJoinListener(val m: PluginTutorial) : Listener {
	// Isto é necessário colocar EM TODA FUNÇÃO que é um evento!
	// Se você esquecer... bem, o evento não irá funcionar
	@EventHandler
	fun onJoin(e: PlayerJoinEvent) {
		// Quando um jogador entrar, vamos mandar uma ideia aleatória para ele!
		val config = m.config

		// Vamos pegar a lista da nossa configuração (que está guardada na parte de "ideias" dentro da "config.yml")
		val lista = config.getStringList("ideias")

		val ideia = lista.random() // Vamos pegar uma ideia aleatória...
		e.player.sendMessage("${PluginTutorial.PREFIX} Ideia do dia: $ideia") // e mandar para o jogador!
	}
}