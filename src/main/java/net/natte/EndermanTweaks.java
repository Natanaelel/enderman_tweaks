package net.natte;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.natte.config.Config;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

import static net.minecraft.server.command.CommandManager.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EndermanTweaks implements ModInitializer {

    public static final String MOD_ID = "enderman_tweaks";

    @Override
    public void onInitialize() {
        Config.init(MOD_ID, Config.class);

        registerCommands();
    }

    private static void registerCommands() {
        CommandRegistrationCallback.EVENT.register(
                (dispatcher, registryAccess, environment) -> dispatcher.register(
                        literal(MOD_ID)
                                .then(reloadConfigCommand())
                                .then(showConfigCommand())));

    }

    private static int showConfig(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();

        List<String> lines = new ArrayList<String>();

        for (Field field : Config.class.getFields()) {
            String value = "(null)";
            try {
                value = field.get(null).toString();
            } catch (Exception ignored) {
            }
            if (!field.getName().equals("configClass"))
                lines.add(field.getName() + ": " + value);
        }

        source.sendMessage(Text.of(String.join("\n", lines)));

        return Command.SINGLE_SUCCESS;
    }

    private static LiteralArgumentBuilder<ServerCommandSource> reloadConfigCommand() {
        return literal("reload").requires(source -> source.hasPermissionLevel(2))
                .executes(context -> {
                    Config.init(MOD_ID, Config.class);
                    context.getSource().sendMessage(Text.of("reloaded config"));
                    return Command.SINGLE_SUCCESS;
                });
    }

    private static LiteralArgumentBuilder<ServerCommandSource> showConfigCommand() {
        return literal("info").executes(EndermanTweaks::showConfig);
    }
}
