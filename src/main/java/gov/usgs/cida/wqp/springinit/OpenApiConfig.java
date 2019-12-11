package gov.usgs.cida.wqp.springinit;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gov.usgs.cida.wqp.swagger.SwaggerConfig;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components())
				.info(new Info().title("Water Quality Portal Data API").description(
						"Documentation for the Water Quality Portal Data Download API"))
				.tags(Arrays.asList(
						new Tag().name(SwaggerConfig.ACTIVITY_TAG_NAME).description(SwaggerConfig.TAG_DESCRIPTION),
						new Tag().name(SwaggerConfig.ACTIVITY_METRIC_TAG_NAME).description(SwaggerConfig.TAG_DESCRIPTION)
						))
		;

	}

}
