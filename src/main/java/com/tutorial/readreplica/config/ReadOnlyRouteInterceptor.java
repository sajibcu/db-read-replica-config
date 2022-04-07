package com.tutorial.readreplica.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
@Order( 0 )
@Slf4j
public class ReadOnlyRouteInterceptor {

    @Around( value = "@annotation(transactional)" )
    public Object proceed(ProceedingJoinPoint pjp, Transactional transactional ) throws Throwable {

        try{
            log.info( "trying to set datasource" );

            if( transactional.readOnly() ){
                RoutingDataSource.setReplica();
                log.info( "read only db set");
            }

            return pjp.proceed();
        } catch (Throwable throwable) {

            throwable.printStackTrace();
            log.error( "Error while intercepting transaction to set datasource rout" );
            throw throwable;
        } finally {

            RoutingDataSource.clearReplica();
        }
    }
}
