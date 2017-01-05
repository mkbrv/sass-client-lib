package com.mkbrv.sass.clientlib.api;

/**
 * Created by mkbrv on 30/12/2016.
 */
public interface SassCompiler {

    /**
     * Compiles the given resource;
     *
     * @return css as compiled sass
     */
    String compile();

}
