package gov.usgs.wma.wqp.openapi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Organization
@SummaryYears
public @interface SummaryOrganizationFilterParameters {

}
