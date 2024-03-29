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

package org.celeria.minecraft.backup;

import org.bukkit.ChatColor;

enum ConfigurationKey {
    BACKUP_FOLDER("backup-folder", "archives"),
    BACK_UP_ENDED_MESSAGE("messages.back-up-ended",
            ChatColor.GREEN + "[Archivist] Back up ended."),
    BACK_UP_STARTED_MESSAGE("messages.back-up-started",
            ChatColor.GREEN + "[Archivist] Back up started."),
    COMPRESSION_LEVEL("compression-level",
            CompressionLevel.DEFAULT.toString()),
    BACK_UP_PERIOD("back-up-period", "PT20M"),
    DURATION_TO_KEEP_BACKUPS("duration-to-keep-backups", "P10D");

    private final String key;
    private final String defaultValue;

    ConfigurationKey(final String key, final String defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public String key() {
        return key;
    }

    public String defaultValue() {
        return defaultValue;
    }
}
