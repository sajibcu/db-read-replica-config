package com.tutorial.readreplica.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<Route> ctx = new ThreadLocal<Route>();

    public enum Route{

        PRIMARY, REPLICA;
    }

    public static void clearReplica(){

        ctx.remove();
    }

    public static void setReplica(){

        ctx.set( Route.REPLICA );
    }

    @Override
    protected Object determineCurrentLookupKey() {

        return ctx.get();
    }
}
