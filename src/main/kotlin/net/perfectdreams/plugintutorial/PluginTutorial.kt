package net.perfectdreams.plugintutorial

import net.perfectdreams.dreamcore.utils.KotlinPlugin
import net.perfectdreams.dreamcore.utils.registerEvents
import net.perfectdreams.plugintutorial.commands.IdeiasCommand
import net.perfectdreams.plugintutorial.events.PlayerJoinListener

// Isto é a classe principal do nosso incrível e maravilhoso plugin!!!
// Ela extende (esse ":" significa que ela extende ou implementa outra classe, no caso, estamos extendendo) a classe "KotlinPlugin"
// Quando o servidor iniciar, essa classe será iniciada pelo CraftBukkit.
//
// Você também pode extender a classe "JavaPlugin" em vez da classe "KotlinPlugin", a classe "KotlinPlugin" foi criada para facilitar
// um pouco algumas coisas (por exemplo, poder usar "registerCommand", e outras coisas), mas se você preferir, pode usar "JavaPlugin"
class PluginTutorial : KotlinPlugin() {
	override fun softEnable() { // Isto será executado quando o plugin for INICIADO
		// Você pode estar se perguntando "nosa, pra que serve isto"
		// Quando você usa "super.algumaCoisa", quer dizer que você está usando a implementação original (da classe que você está extendendo!) da função
		// No nosso caso, você pode até remover isto pois a nossa implementação original é um método vazio, mas se você quiser deixar, pode também.
		//
		// Isto é útil para quando iremos fazer vários objetos que implementam e extendem outros!
		super.softEnable()

		// Nós iremos salvar a configuração padrão do plugin, a configuração padrão está salva na pasta "src/main/resources/config.yml"
		saveDefaultConfig() // Ao usar isto, ele irá pegar a configuração que está dentro da nossa JAR e salvar na pasta "plugins/PluginTutorial"

		// Agora iremos registrar o nosso comando!
		val comando = IdeiasCommand(this)
		registerCommand(comando) // Yay!!

		// E agora iremos registar o nosso event listener!
		val listener = PlayerJoinListener(this)
		registerEvents(listener)
	}

	override fun softDisable() { // Isto será executado quando o plugin for DESLIGADO
		super.softDisable()
	}

	companion object { // Isto é um bloco de código ESTÁTICO!
		// Quer dizer que você consegue acessar as variáveis que estão aqui sem precisar de uma instância da classe "PluginTutorial"
		// Eu recomendo colocar aqui apenas coisas que JAMAIS irão mudar durante a execução do plugin, como, por exemplo, prefixos!
		//
		// Não é obrigatório colocar esse bloco de código em todas as suas classes!
		const val PREFIX = "§8[§a§lIdeias§8]§e"
		// Não sabe o que isto significa?
		// const = significa que é uma variável CONSTANTE (nunca irá mudar!)
		// val   = significa que é uma variável IMUTÁVEL (nunca irá mudar!)
		// var   = significa que é uma variável MUTÁVEL  (talvez poderá mudar! você pode mudar ela a qualquer momento!!)
		//
		// const apenas pode ser utilizadas em alguns tipos de dados, como strings (texto), números (int, double, etc) e outras coisinhas.
		// Você não precisa se preocupar com isto! ;)
	}
}