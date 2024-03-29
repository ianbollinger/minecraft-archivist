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

import com.google.inject.*;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class AbstractJavaPlugin extends JavaPlugin {
    private volatile BukkitPlugin plugin;

    @Override
    public final void onDisable() {
        plugin.stop();
    }

    @Override
    public final void onEnable() {
        final Module module = new BukkitPluginModule(this, getModule());
        final Injector injector = Guice.createInjector(module);
        plugin = injector.getInstance(BukkitPlugin.class);
        plugin.run();
    }

    protected abstract Module getModule();
}
