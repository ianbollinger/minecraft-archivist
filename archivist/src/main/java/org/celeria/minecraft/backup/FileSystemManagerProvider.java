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

import java.io.File;
import javax.annotation.concurrent.Immutable;
import org.apache.commons.vfs2.*;
import org.apache.commons.vfs2.cache.DefaultFilesCache;
import org.apache.commons.vfs2.impl.DefaultFileSystemManager;
import org.apache.commons.vfs2.provider.local.DefaultLocalFileProvider;

@Immutable
class FileSystemManagerProvider implements FileProvider<FileSystemManager> {
    private final DefaultFileSystemManager manager;
    private final DefaultLocalFileProvider provider;
    private final DefaultFilesCache cache;

    FileSystemManagerProvider(final DefaultFileSystemManager manager,
            final DefaultLocalFileProvider provider,
            final DefaultFilesCache cache) {
        this.manager = manager;
        this.provider = provider;
        this.cache = cache;
    }

    @Override
    public FileSystemManager get() throws FileSystemException {
        build();
        manager.init();
        return manager;
    }

    private void build() throws FileSystemException {
        manager.addProvider("file", provider);
        manager.setFilesCache(cache);
        manager.setCacheStrategy(CacheStrategy.ON_RESOLVE);
        manager.setBaseFile(new File("."));
    }
}
