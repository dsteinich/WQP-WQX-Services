package gov.usgs.wma.wqp.openapi.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

@Retention(RUNTIME)
@Target(ANNOTATION_TYPE)
@Parameter(
		in = ParameterIn.QUERY,
		name = "organization",
		description = "One or more case-sensitive Organization Identifiers.",
		array = @ArraySchema(schema = @Schema(type = "string"))
)
public @interface Organization {

}
