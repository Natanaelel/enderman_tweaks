package net.natte;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.natte.config.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;

import static net.minecraft.server.command.CommandManager.*;

import java.lang.reflect.Field;

public class EndermanTweaks implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("enderman_tweaks");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");

		Config.init("enderman_tweaks", Config.class);

		registerCommands();

		LOGGER.info("registered commands");
		
	}

	public static void registerCommands() {
		CommandRegistrationCallback.EVENT.register(
			(dispatcher, registryAccess, environment) ->
				dispatcher.register(
					literal("enderman_tweaks")
					.then(literal("reload_config")
						.executes( context -> {
							Config.init("enderman_tweaks", Config.class);
							context.getSource().sendMessage(Text.of("reloaded config"));
							return Command.SINGLE_SUCCESS;
						}))	
					.then(literal("config").executes(EndermanTweaks::showConfig))));


	}

	public static int showConfig(CommandContext<ServerCommandSource> context){
		ServerCommandSource source = context.getSource();

		String message = "";
		for (Field field : Config.class.getFields()){
			String value;
			try{value = field.getBoolean(null) ? "true" : "false";}catch(Exception e){value = "-";}
			message += field.getName() + ": " + value  + "\n";
		}
	
		source.sendMessage(Text.of(message));
		
		return Command.SINGLE_SUCCESS;
	}


}