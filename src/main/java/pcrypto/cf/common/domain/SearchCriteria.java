/*
 * Copyright (c) 2019 ChainFront LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pcrypto.cf.common.domain;

public class SearchCriteria
{
    private String key;
    private String operation;
    private Object value;

    public SearchCriteria( final String key,
                           final String operation,
                           final Object value )
    {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public String getKey()
    {
        return key;
    }

    public String getOperation()
    {
        return operation;
    }

    public Object getValue()
    {
        return value;
    }
}
