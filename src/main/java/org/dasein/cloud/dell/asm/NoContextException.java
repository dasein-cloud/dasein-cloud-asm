/**
 * Copyright (C) 2013 Dell, Inc.
 * See annotations for authorship information
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ====================================================================
 */

package org.dasein.cloud.dell.asm;

/**
 * Simple error representing a failure to set up a configuration.
 * <p>Created by George Reese: 05/17/2013 9:36 AM</p>
 * @author George Reese
 * @version 2013.04 initial version
 * @since 2013.04
 */
public class NoContextException extends ConfigurationException {
    /**
     * Constructs an exception representing the lack of context.
     */
    public NoContextException() { super("No context was set for this request"); }
}

