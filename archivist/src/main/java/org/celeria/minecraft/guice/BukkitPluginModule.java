/*
 * Copyright 2011 Ian D. Bollinger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.celeria.minecraft.guice;

import java.util.Locale;
import javax.annotation.concurrent.Immutable;
import ch.qos.cal10n.*;
import com.google.inject.*;
import org.bukkit.Server;
import org.bukkit.plugin.*;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.config.Configuration;
import org.slf4j.cal10n.*;

@Immutable
final class BukkitPluginModule extends AbstractModule {
    private static final String BUKKIT_LOGGER_NAME = "Minecraft";
    private final Plugin plugin;
    private final Module module;

    @Inject
    public BukkitPluginModule(final Plugin plugin, final Module module) {
        this.plugin = plugin;
        this.module = module;
    }

    @Override
    protected void configure() {
        bind(Plugin.class).toInstance(plugin);
        bindServer(plugin.getServer());
        install(module);
    }

    private void bindServer(final Server server) {
        bind(Server.class).toInstance(server);
        bind(BukkitScheduler.class).toInstance(server.getScheduler());
        bind(PluginManager.class).toInstance(server.getPluginManager());
    }

    @Provides @Singleton
    public Configuration providesConfiguration() {
        final Configuration configuration = plugin.getConfiguration();
        configuration.load();
        return configuration;
    }

    @Provides @Singleton
    public LocLogger provideLogger() {
        final IMessageConveyor messageConveyor = new MessageConveyor(Locale.US);
        final LocLoggerFactory factory = new LocLoggerFactory(messageConveyor);
        return factory.getLocLogger(BUKKIT_LOGGER_NAME);
    }
}
