package gov.usgs.wma.wqp.openapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gov.usgs.wma.wqp.openapi.model.SummaryYears;
import gov.usgs.wma.wqp.service.ConfigurationService;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.HashSet;
//
//import org.apache.commons.lang3.ArrayUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.core.env.Environment;
//
//import com.fasterxml.classmate.TypeResolver;
//
//import gov.usgs.wma.wqp.openapi.model.ActivityCountJson;
//import gov.usgs.wma.wqp.openapi.model.ActivityMetricCountJson;
//import gov.usgs.wma.wqp.openapi.model.BiologicalMetricCountJson;
//import gov.usgs.wma.wqp.openapi.model.OrganizationCountJson;
//import gov.usgs.wma.wqp.openapi.model.PostParms;
//import gov.usgs.wma.wqp.openapi.model.ProjectCountJson;
//import gov.usgs.wma.wqp.openapi.model.ResDetectQntLmtCountJson;
//import gov.usgs.wma.wqp.openapi.model.ResultCountJson;
//import gov.usgs.wma.wqp.openapi.model.StationCountJson;
//import springfox.documentation.PathProvider;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiKey;
//import springfox.documentation.service.Tag;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.paths.AbstractPathProvider;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger.web.DocExpansion;
//import springfox.documentation.swagger.web.ModelRendering;
//import springfox.documentation.swagger.web.OperationsSorter;
//import springfox.documentation.swagger.web.TagsSorter;
//import springfox.documentation.swagger.web.UiConfigurationBuilder;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
public class ConfigOpenApi {

	public static final String ORGANIZATION_TAG_NAME = "Organization";
	public static final String ACTIVITY_TAG_NAME = "Activity";
	public static final String ACTIVITY_METRIC_TAG_NAME = "Activity Metric";
	public static final String ACTIVITY_ALL_TAG_NAME = "Activity All";
	public static final String BIOLOGICAL_METRIC_TAG_NAME = "Biological Metric";	
	public static final String PERIOD_OF_RECORD_TAG_NAME = "Period of Record";
	public static final String RES_DETECT_QNT_LMT_TAG_NAME = "Result Detection Quantitation Limit";
	public static final String RESULT_TAG_NAME = "Result";
	public static final String SIMPLE_STATION_TAG_NAME = "Simple Station (deprecated)";
	public static final String STATION_TAG_NAME = "Station";
	public static final String SUMMARY_ORGANIZATION_TAG_NAME = "Summary Organizaton";
	public static final String SUMMARY_MONITORING_LOCATION_TAG_NAME = "Summary Monitoring Location";
	public static final String VERSION_TAG_NAME = "Application Version";
	public static final String VERSION_TAG_DESCRIPTION = "Display Application Version";
	public static final String PROJECT_TAG_NAME = "Project";
	public static final String PROJECT_MONITORING_LOCATION_WEIGHTING_TAG_NAME = "Project Monitoring Location Weighting";
	public static final String TAG_DESCRIPTION = "Data Download";
	public static final String FILE_DOWNLOAD_TAG_NAME = "File Download";

	@Autowired
	private ConfigurationService configurationService;
//	@Autowired
//	private TypeResolver typeResolver;
//
//	@Autowired
//	private Environment environment;

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.addServersItem(new Server().url(configurationService.getSiteUrlBase()))
				.components(getComponents())
				.info(new Info()
						.title("Water Quality Portal Data API")
						.description("Documentation for the Water Quality Portal Data Download API")
						.version(configurationService.getAppVersion())
						);

	}

	public Components getComponents() {
		Components rtn = new Components();
		rtn.addSchemas("testy", SummaryYears.getIt());
		return rtn;
	}
//	@Bean
//	public Docket qwPortalServicesApi() {
//		Docket docket = new Docket(DocumentationType.SWAGGER_2)
//			.protocols(new HashSet<>(Arrays.asList("http")))
//			.host(swaggerDisplayHost)
//			.pathProvider(pathProvider())
//			.useDefaultResponseMessages(false)
//			.additionalModels(typeResolver.resolve(PostParms.class),
//					typeResolver.resolve(OrganizationCountJson.class),
//					typeResolver.resolve(StationCountJson.class),
//					typeResolver.resolve(ActivityCountJson.class),
//					typeResolver.resolve(ActivityMetricCountJson.class),
//					typeResolver.resolve(BiologicalMetricCountJson.class),
//					typeResolver.resolve(ResultCountJson.class),
//					typeResolver.resolve(ResDetectQntLmtCountJson.class),
//					typeResolver.resolve(ProjectCountJson.class))
//			.tags(new Tag(ORGANIZATION_TAG_NAME, TAG_DESCRIPTION),
//					new Tag(ACTIVITY_TAG_NAME, TAG_DESCRIPTION),
//					new Tag(ACTIVITY_METRIC_TAG_NAME, TAG_DESCRIPTION),	
//					new Tag(BIOLOGICAL_METRIC_TAG_NAME, TAG_DESCRIPTION),
//					new Tag(PERIOD_OF_RECORD_TAG_NAME,TAG_DESCRIPTION),
//					new Tag(RES_DETECT_QNT_LMT_TAG_NAME, TAG_DESCRIPTION),
//					new Tag(RESULT_TAG_NAME, TAG_DESCRIPTION),
//					new Tag(SIMPLE_STATION_TAG_NAME, TAG_DESCRIPTION),
//					new Tag(STATION_TAG_NAME, TAG_DESCRIPTION),
//					new Tag(SUMMARY_ORGANIZATION_TAG_NAME, TAG_DESCRIPTION),
//					new Tag(SUMMARY_MONITORING_LOCATION_TAG_NAME, TAG_DESCRIPTION),
//					new Tag(VERSION_TAG_NAME, VERSION_TAG_DESCRIPTION),
//					new Tag(FILE_DOWNLOAD_TAG_NAME, FILE_DOWNLOAD_TAG_NAME)
//				)
//			.select().paths(PathSelectors.any())
//			.apis(RequestHandlerSelectors.basePackage("gov.usgs.wma.wqp"))
//			.build()
//		;
//
//		if (ArrayUtils.contains(environment.getActiveProfiles(), "internal")) {
//			//Add in the Authorize button for WQP Internal
//			docket.securitySchemes(Collections.singletonList(apiKey()));
//		}
//
//		return docket;
//	}

//	@Bean
//	public PathProvider pathProvider() {
//		PathProvider rtn = new ProxyPathProvider();
//		return rtn;
//	}

//	public class ProxyPathProvider extends AbstractPathProvider {
//		@Override
//		protected String applicationPath() {
//			return swaggerDisplayPath;
//		}
//	
//		@Override
//		protected String getDocumentationPath() {
//			return swaggerDisplayPath;
//		}
//	}

//	private ApiKey apiKey() {
//		return new ApiKey("mykey", "Authorization", "header");
//	}

//	@Bean
//	public Object uiConfig() {
//		//This is needed in swagger 2 for the "try it" button on head requests - should not be needed with swagger 3
//		//It is needed in all WQP projects!!!
//		return UiConfigurationBuilder
//				.builder()
//				.deepLinking(true)
//				.displayOperationId(false)
//				.defaultModelsExpandDepth(1)
//				.defaultModelExpandDepth(1)
//				.defaultModelRendering(ModelRendering.EXAMPLE)
//				.displayRequestDuration(false)
//				.docExpansion(DocExpansion.NONE)
//				.filter(false)
//				.maxDisplayedTags(null)
//				.operationsSorter(OperationsSorter.ALPHA)
//				.showExtensions(false)
//				.tagsSorter(TagsSorter.ALPHA)
//				.supportedSubmitMethods(new String[] { "get", "post", "head" })
//				.validatorUrl(null)
//				.build();
//	}

}
