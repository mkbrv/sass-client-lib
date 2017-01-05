package com.mkbrv.sass.clientlib.bundle.file;

/**
 * Represents an absolute file path;
 * Created by mkbrv on 02/01/2017.
 */
public class AbsoluteFilePath implements FilePath {

    final String path;

    /**
     * @param path
     */
    AbsoluteFilePath(final String path) {
        assert path != null;
        this.path = path;
    }


    @Override
    public String getPath() {
        return path;
    }


}
