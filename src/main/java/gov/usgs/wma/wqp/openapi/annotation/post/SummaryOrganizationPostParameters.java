package gov.usgs.wma.wqp.openapi.annotation.post;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.fasterxml.jackson.annotation.JsonProperty;

import gov.usgs.wma.wqp.openapi.annotation.SummaryYears;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Retention(RUNTIME)
@Target(METHOD)
@RequestBody(
		content = {
				@Content (
						mediaType = "application/json",
						schema=@Schema(implementation = SummaryOrganizationPostParameters.SummaryOrganization.class)
				)
		})
public @interface SummaryOrganizationPostParameters {

	public class SummaryOrganization {
		@JsonProperty("organization")
		String[] organization;
		@JsonProperty("summaryYears")
		@SummaryYears
		String[] summaryYears;
	}
}
