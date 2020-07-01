/**
 * Copyright 2015-2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *     http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazon.dataloader.datadownloader;

import android.content.Context;

import java.util.Map;

/**
 * Abstract class for URL generators. The purpose of this class is to generate valid urls using
 * parameters. All implementations must implement {@link #createInstance(Context)} method to
 * provide a concrete implementation to {@link UrlGeneratorFactory#createUrlGenerator(Context,
 * String)}. If the implementation is a singleton, override the {@link #isSingleton()} method to
 * return true and provide the singleton instance via {@link #getInstance(Context)}.
 */
public abstract class AUrlGenerator extends AObjectCreator {

    /**
     * Exception class created for UrlGenerator.
     */
    public static class UrlGeneratorException extends Exception {

        /**
         * {@inheritDoc}
         */
        public UrlGeneratorException(String msg, Throwable cause) {

            super(msg, cause);
        }
    }

    /**
     * Constructor for URl generators.
     * {@inheritDoc}
     */
    public AUrlGenerator(Context context) throws ObjectCreatorException {

        super(context);
    }

    /**
     * Generates a URL string that incorporates parameters.
     *
     * @param params Any parameters that are needed to create the URL string.
     * @return The generated URL string.
     * @throws UrlGeneratorException Any exception generated by this method will be wrapped in
     *                               UrlGeneratorException.
     */
    public abstract String getUrl(Map params) throws UrlGeneratorException;

    /**
     * Searches for a key in the params map. If the key is not found in the params map, it searches
     * for the key in the configuration map. If the key is not found in the configuration map, an
     * error is thrown.
     *
     * @param params The map in which to search for the key in.
     * @param key    The key to search for.
     * @return The value associated with the key.
     * @throws IllegalArgumentException If the key is not found.
     */
    protected String getKey(Map params, String key) {

        if (params.containsKey(key)) {
            return (String) params.get(key);
        }
        if (mConfiguration.containsItem(key)) {
            return mConfiguration.getItemAsString(key);
        }
        throw new IllegalArgumentException(key + " could not be found");
    }

}
