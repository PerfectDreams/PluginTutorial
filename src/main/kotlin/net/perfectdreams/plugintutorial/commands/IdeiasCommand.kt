package net.perfectdreams.plugintutorial.commands

import net.perfectdreams.dreamcore.utils.commands.AbstractCommand
import net.perfectdreams.dreamcore.utils.commands.annotation.Subcommand
import net.perfectdreams.dreamcore.utils.commands.annotation.SubcommandPermission
import net.perfectdreams.plugintutorial.PluginTutorial
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

// Isto é um comando!
// label      = nome do comando
// aliases    = nomes "alternativos" do comando
// permission = permissão necessária para usar este comando
//
// Neste caso, estamos registrando um comando chamado "ideias", que tambvém pode ser utilizado usando "idéias"
// Para usar o comando, o usuário precisa ter a permissão de "plugintutorial.ideias"
//
// Para iniciar a nossa classe, nós precisamos passar uma instância do PluginTutorial!
// Por exemplo, utilizar "val comando = IdeiasCommand()" irá apenas dar um belo erro, pois precisamos passar a instância!
//
// val comando = IdeiasCommand()      - ERRADO!
// val comando = IdeiasCommand("olá") - ERRADO!
// val comando = IdeiasCommand(1337)  - ERRADO!
//
// Mas... e se a gente passar a instância do "PluginTutorial" para o comando?
// Por exemplo, dentro do "softEnable" do PluginTutorial, nós podemos pegar a instância do objeto atual utilizando "this"!
//
// val comando = IdeiasCommand(this)  - FUNCIONA! Mas apenas dentro do escopo do PluginTutorial!
//
// Tanto que a gente pode até iniciar uma instância do IdeiasCommand dentro do escopo do nosso IdeiasCommand!
// val ocmando = IdeiasCommand(m)     - FUNCIONA! Mas apenas dentro do escopo do IdeiasCommand!
class IdeiasCommand(val m: PluginTutorial) : AbstractCommand(label = "ideias", aliases = listOf("idéias"), permission = "plugintutorial.ideias") {
	@Subcommand // Isto significa que isto é um subcomando, como não tem nenhum argumento no Subcomando, isto será executado quando o usuário utilizar "/ideias"
	fun root(sender: CommandSender) { // Você deve estar se perguntando... "mas porque CommandSender?"
		// Você pode usar "sender: Player", também irá funcionar normalmente...
		// MAS CONTUDO TODAVIA! Se você apenas restringir este subcomando para "Player", você não poderá usar o comando pelo console!!
		// Então evite apenas restringir um subcomando para apenas players sendo que ele pode funcionar normalmente no console!

		// Lembra do nosso "config.yml", então, vamos acessar ele!
		val config = m.config

		// Vamos pegar a lista da nossa configuração (que está guardada na parte de "ideias" dentro da "config.yml")
		val lista = config.getStringList("ideias")

		val ideia = lista.random() // Vamos pegar uma ideia aleatória...
		sender.sendMessage("${PluginTutorial.PREFIX} $ideia") // e mandar para o jogador!
	}

	// Mas isso é meio paia, vamos fazer que o usuário possa pegar uma ideia específica!
	// Por exemplo, "/ideias ideia 2" irá retornar a terceira ideia na configuração!
	// ...sim, eu não falei errado! Normalmente em linguagens de programação tudo é "zero indexed", ou seja, o primeiro resultado é 0, o segundo é 1, etc etc etc.
	//
	// Claro, dá para arrumar isto, mas isso é uma lição de casa para você resolver!
	// Dica: Só diminuir o valor de "idx" em -1 ;)
	@Subcommand(["ideia"])
	fun ideia(sender: CommandSender, idx: Int) {
		val config = m.config
		val lista = config.getStringList("ideias")

		// Mas espera! Antes de pegar uma ideia aleatória, vamos verificar se existem elementos suficientes em nossa lista!
		// Se a gente não verificar, se o usuário usar "/ideias ideia 999", vai dar um belo erro :(
		if (idx !in 0 until lista.size) { // Ou seja, caso idx não esteja entre 0 e *insira tamanho da lista de ideias aqui*...
			sender.sendMessage("${PluginTutorial.PREFIX}§c Nosso livro de ideias não possui tantas ideias assim!")
			return
		}

		// Mas caso esteja, vamos pegar a ideia em nossa lista!
		val ideia = lista[idx] // "nosa mas o que isso faz"
		// Isto pega a string que está na posição em idx!
		// Ou seja, usando a nossa configuração padrão...
		// lista[0] = Fazer uma casa de três andares
		// lista[1] = Fazer um templo de adoração a Loritta Morenitta
		// lista[2] = Fazer um Burger King
		// Legal, né?
		//
		// Vamos agora mandar para o jogador a incrível ideia!
		sender.sendMessage("${PluginTutorial.PREFIX} $ideia") // e mandar para o jogador!
	}

	// E para finalizar, vamos criar um comando para carregar a configuração, para caso a gente queira colocar mais ideias no nosso livrinho de ideias sem
	// precisar reiniciar o servidor!
	@Subcommand(["reload"])
	@SubcommandPermission("plugintutorial.reload") // Wow, permissões para restringir que apenas algumas pessoas possam usar este comando!
	fun reload(sender: CommandSender) {
		m.reloadConfig()
		sender.sendMessage("${PluginTutorial.PREFIX}§a Configuração recarregada com sucesso!")
	}

}
