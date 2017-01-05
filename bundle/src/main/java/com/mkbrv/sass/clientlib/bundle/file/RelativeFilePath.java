package com.mkbrv.sass.clientlib.bundle.file;

/**
 * Represents a relative file path;
 * Created by mkbrv on 02/01/2017.
 */
public class RelativeFilePath implements FilePath {


    final String path;
    final String originalPath;

    AbsoluteFilePath decoratedPath;

    /**
     * @param path
     * @param originalPath
     */
    RelativeFilePath(final String path,
                     final String originalPath) {
        assert path != null;
        assert originalPath != null;
        this.path = path;
        this.originalPath = originalPath;
    }


    @Override
    public String getPath() {
        if (decoratedPath == null) {
            decoratedPath = new AbsoluteFilePath(findOriginLevel() + "/" + path);
        }
        return decoratedPath.getPath();
    }


    /**
     * Finds the same level with the original path;
     * TODO: Candidate for refactoring;
     *
     * @return level
     */
    protected String findOriginLevel() {
        if (originalPath.equalsIgnoreCase("/")
                || originalPath.length() == 0
                || !originalPath.contains("/")) {
            return "/";
        }
        String levelPath = originalPath;
        if (originalPath.endsWith("/")) {
            levelPath = originalPath.substring(0, originalPath.length() - 1);
        }
        levelPath = levelPath.substring(0, levelPath.lastIndexOf("/"));
        return levelPath.length() > 0 ? levelPath : "/";
    }

}
