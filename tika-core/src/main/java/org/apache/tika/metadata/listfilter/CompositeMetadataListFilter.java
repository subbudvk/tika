/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.tika.metadata.listfilter;

import java.util.ArrayList;
import java.util.List;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;

public class CompositeMetadataListFilter extends MetadataListFilter {

    //no longer final to allow for no arg initialization during serialization
    private List<MetadataListFilter> filters;

    public CompositeMetadataListFilter() {
        filters = new ArrayList<>();
    }
    public CompositeMetadataListFilter(List<MetadataListFilter> filters) {
        this.filters = filters;
    }

    public void setFilters(List<MetadataListFilter> filters) {
        this.filters.clear();
        this.filters.addAll(filters);
    }

    public List<MetadataListFilter> getFilters() {
        return filters;
    }

    @Override
    public List<Metadata> filter(List<Metadata> metadataList) throws TikaException {
        for (MetadataListFilter filter : filters) {
            metadataList = filter.filter(metadataList);
        }
        return metadataList;
    }

    @Override
    public String toString() {
        return "CompositeMetadataListFilter{" + "filters=" + filters + '}';
    }
}
