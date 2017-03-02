/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.elasticsearch.plugins;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.script.NativeScriptFactory;
import org.elasticsearch.script.ScriptContext;
import org.elasticsearch.script.ScriptEngineService;
import org.elasticsearch.script.TemplateService;

import java.util.Collections;
import java.util.List;

/**
 * An additional extension point for {@link Plugin}s that extends Elasticsearch's scripting functionality.
 */
public interface ScriptPlugin {

    /**
     * Returns a {@link ScriptEngineService} instance or <code>null</code> if this plugin doesn't add a new script engine
     */
    default ScriptEngineService getScriptEngineService(Settings settings) {
        return null;
    }

    /**
     * Returns a {@link TemplateService.Backend} if this plugin implements a template backend or null if it doesn't. Note that Elasticsearch
     * will refuse to start if there is more than one template backend and it is bundled with Mustache. To replace that backend you'd have
     * to remove the Mustache module which is super untested.
     */
    default TemplateService.Backend getTemplateBackend() {
        return null;
    }

    /**
     * Returns a list of {@link NativeScriptFactory} instances.
     */
    default List<NativeScriptFactory> getNativeScripts() {
        return Collections.emptyList();
    }

    /**
     * Returns a {@link ScriptContext.Plugin} instance or <code>null</code> if this plugin doesn't add a new script context plugin
     */
    default ScriptContext.Plugin getCustomScriptContexts() {
        return null;
    }
}
