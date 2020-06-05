package gov.usgs.wma.wqp.openapi.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

@Retention(RUNTIME)
@Target({ANNOTATION_TYPE, FIELD})
@Parameter(
		in = ParameterIn.QUERY,
		name = "summaryYears",
		description = "Select summary data for 1, 5, or all years. If no summary year is selected, all years is returned by default.",
		schema = @Schema(type = "string"),
		examples = {
				@ExampleObject(name="One Year", description="Return data for the past year.", value="1"),
				@ExampleObject(name="Five Years", description="Return data for the past five years.", value="5"),
				@ExampleObject(name="All Years", description="Return data for the all years.", value="all")
				}
)
public @interface SummaryYears {

}
