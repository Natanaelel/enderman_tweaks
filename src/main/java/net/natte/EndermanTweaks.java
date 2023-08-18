package net.natte;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.natte.config.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

import static net.minecraft.server.command.CommandManager.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EndermanTweaks implements ModInitializer {

	private static final Logger LOGGER = LoggerFactory.getLogger("enderman_tweaks");

	@Override
	public void onInitialize() {

		LOGGER.info("Hello Fabric world!");

		Config.init("enderman_tweaks", Config.class);

		registerCommands();

		LOGGER.info("registered commands");

	}

	private static void registerCommands() {
		CommandRegistrationCallback.EVENT.register(
				(dispatcher, registryAccess, environment) -> dispatcher.register(
						literal("enderman_tweaks")
								.then(reloadConfigCommand("reload"))
								.then(showConfigCommand("info"))));

	}

	private static int showConfig(CommandContext<ServerCommandSource> context) {
		ServerCommandSource source = context.getSource();

		List<String> lines = new ArrayList<String>();

		for (Field field : Config.class.getFields()) {
			String value = "(null)";
			try {
				value = field.get(null).toString();
			} catch (Exception e) {
			}
			if(!field.getName().equals("configClass"))
				lines.add(field.getName() + ": " + value);
		}

		source.sendMessage(Text.of(String.join("\n", lines)));

		return Command.SINGLE_SUCCESS;
	}

	private static LiteralArgumentBuilder<ServerCommandSource> reloadConfigCommand(String command) {
		return literal(command).requires(source -> source.hasPermissionLevel(2))
				.executes(context -> {
					Config.init("enderman_tweaks", Config.class);
					context.getSource().sendMessage(Text.of("reloaded config"));
					return Command.SINGLE_SUCCESS;
				});
	}

	private static LiteralArgumentBuilder<ServerCommandSource> showConfigCommand(String command) {
		return literal(command).executes(EndermanTweaks::showConfig);
	}

}