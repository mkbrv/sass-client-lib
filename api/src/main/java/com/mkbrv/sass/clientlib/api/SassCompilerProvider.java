package com.mkbrv.sass.clientlib.api;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

/**
 * Created by mkbrv on 30/12/2016.
 */
public interface SassCompilerProvider {

    /**
     * @param builder
     * @return
     */
    SassCompiler getCompiler(final SassCompilerBuilder builder);


    /**
     * Required Builder for the Sass Compiler
     */
    class SassCompilerBuilder {

        /**
         *
         */
        Resource resource;

        public SassCompilerBuilder forResource(final Resource resource) {
            this.resource = resource;
            return this;
        }

        public ResourceResolver getResolver() {
            return resource.getResourceResolver();
        }

        public Resource getResource() {
            return resource;
        }
    }


}
