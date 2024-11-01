/*
 *
 *   Copyright (C) 2013 Geobeyond Srl
 *
 *   This library is free software; you can redistribute it and/or modify it under
 *   the terms of the GNU Lesser General Public License as published by the Free
 *   Software Foundation; either version 2.1 of the License, or (at your option)
 *   any later version.
 *
 *   This library is distributed in the hope that it will be useful, but WITHOUT
 *   ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 *   FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 *   details.
 *
 *   You should have received a copy of the GNU Lesser General Public License along
 *   with this library; if not, write to the Free Software Foundation, Inc., 59
 *   Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *   
 *
 */

package org.geotools.filter.function;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.geotools.api.feature.type.Name;
import org.geotools.api.filter.capability.FunctionName;
import org.geotools.api.filter.expression.Expression;
import org.geotools.api.filter.expression.Function;
import org.geotools.api.filter.expression.Literal;
import org.geotools.feature.NameImpl;
import org.geotools.filter.FunctionFactory;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class FluxoFunctionFactory implements FunctionFactory {

    static Cache<String, Double> cache_width;
    static boolean useCache=false;

    
    @Override
    public List<FunctionName> getFunctionNames() {

        List<FunctionName> functionList = new ArrayList<FunctionName>();
        functionList.add(FluxoFilterFunction.NAME);
        // initialize cache
        cache_width = CacheBuilder.newBuilder().maximumSize(1000000).build();

        return Collections.unmodifiableList(functionList);
    }

    @Override
    public Function function(String name, List<Expression> args, Literal fallback) {

        return function(new NameImpl(name), args, fallback);
    }

    @Override
    public Function function(Name name, List<Expression> args, Literal fallback) {

        if (FluxoFilterFunction.NAME.getFunctionName().equals(name)) {
            return new FluxoFilterFunction(args, fallback);
        }
        return null; // we do not implement that function
    }
}
