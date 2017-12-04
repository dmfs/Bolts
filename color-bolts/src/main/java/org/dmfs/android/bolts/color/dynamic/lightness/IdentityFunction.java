/*
 * Copyright 2017 dmfs GmbH
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dmfs.android.bolts.color.dynamic.lightness;

import org.dmfs.jems.function.Function;


/**
 * Identity function. (Returns the argument)
 *
 * @author Gabor Keszthelyi
 * @deprecated Use it from jems when available
 */
@Deprecated
final class IdentityFunction<Argument> implements Function<Argument, Argument>
{
    @Override
    public Argument value(Argument argument)
    {
        return argument;
    }
}