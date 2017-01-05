package com.mkbrv.sass.clientlib.bundle.file;

import com.adobe.granite.ui.clientlibs.script.ScriptResource;
import com.adobe.granite.ui.clientlibs.script.ScriptResourceProvider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by mkbrv on 02/01/2017.
 */
public class ScriptResourceFile implements SassFile {


    private FilePath path;
    private ScriptResourceProvider provider;

    private ScriptResource scriptResource;

    /**
     * @param path
     * @param provider
     */
    public ScriptResourceFile(final FilePath path, final ScriptResourceProvider provider) {
        this.path = path;
        this.provider = provider;
    }

    @Override
    public FilePath getPath() {
        return path;
    }

    @Override
    public Reader getReader() throws IOException {
        if (this.scriptResource == null) {
            return load().getReader();
        }

        return scriptResource.getReader();
    }

    private int attemptedLoads = 0;

    @Override
    public SassFile load() throws IOException {
        final Integer MAX_ATTEMPTS = 3;
        if (this.scriptResource == null && attemptedLoads < MAX_ATTEMPTS) {
            attemptedLoads++;
            this.scriptResource = provider.getResource(this.path.pathWithExtension());
        } else {
            throw new FileNotFoundException("Failed to load the script resource: " + this.path.pathWithExtension());
        }
        return this;
    }
}
