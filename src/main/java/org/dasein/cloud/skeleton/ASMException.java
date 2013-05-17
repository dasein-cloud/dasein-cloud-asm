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

package org.dasein.cloud.skeleton;

import org.dasein.cloud.CloudErrorType;
import org.dasein.cloud.CloudException;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * Base exception class for errors that occur in Dell ASM.
 * <p>Created by George Reese: 05/17/2013 8:58 AM</p>
 * @author George Reese
 * @version 2013.04 initial version
 * @since 2013.04
 */
public class ASMException extends CloudException {

    public ASMException(@Nonnull Throwable cause) {
        super(cause);
    }

    public ASMException(@Nonnull CloudErrorType type, @Nonnegative int httpCode, @Nonnull String providerCode, @Nonnull String message) {
        super(type, httpCode, providerCode, message);
    }
}
