package com.mkbrv.sass.clientlib.bundle.file;

/**
 * Created by mkbrv on 02/01/2017.
 */
public interface FilePath {


    String getPath();

    /**
     * Fixes the path by adding the extension if needed;
     * For the moment we provide only compilation for scss files;
     *
     * @return path;
     */
    default String pathWithExtension() {
        if (Extensions.SCSS.hasExtension(getPath())) {
            return getPath();
        }
        return getPath() + Extensions.SCSS.dotPrefixExtension();
    }

    /**
     * Acts as a factory.
     *
     * @param path
     * @param originPath
     * @return
     */
    static FilePath newInstance(final String path, final String originPath) {
        if (path.startsWith("/")) {
            return new AbsoluteFilePath(path);
        }
        return new RelativeFilePath(path, originPath);
    }

}
