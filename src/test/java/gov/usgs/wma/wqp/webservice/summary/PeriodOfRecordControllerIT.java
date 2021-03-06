package gov.usgs.wma.wqp.webservice.summary;

import static gov.usgs.wma.wqp.openapi.model.StationCountJson.HEADER_BIODATA_SITE_COUNT;
import static gov.usgs.wma.wqp.openapi.model.StationCountJson.HEADER_NWIS_SITE_COUNT;
import static gov.usgs.wma.wqp.openapi.model.StationCountJson.HEADER_STEWARDS_SITE_COUNT;
import static gov.usgs.wma.wqp.openapi.model.StationCountJson.HEADER_STORET_SITE_COUNT;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.wma.wqp.Application;
import gov.usgs.wma.wqp.CsvDataSetLoader;
import gov.usgs.wma.wqp.mapping.Profile;
import gov.usgs.wma.wqp.parameter.Parameters;
import gov.usgs.wma.wqp.springinit.DBTestConfig;
import gov.usgs.wma.wqp.util.HttpConstants;
import gov.usgs.wma.wqp.webservice.BaseControllerIntegrationTest;


@EnableWebMvc
@AutoConfigureMockMvc()
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.MOCK,
	classes={DBTestConfig.class, Application.class})
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_CLASS)
public class PeriodOfRecordControllerIT extends BaseControllerIntegrationTest {
	protected static final Profile PROFILE = Profile.PERIOD_OF_RECORD;
	protected static final String TOTAL_SITE_SUM_COUNT = "5";
	protected static final String BIODATA_SITE_SUM_COUNT = null;
	protected static final String NWIS_SITE_SUM_COUNT = "2";
	protected static final String STEWARDS_SITE_SUM_COUNT = "1";
	protected static final String STORET_SITE_SUM_COUNT = "2";
	protected static final String TOTAL_SITE_SUM_ONE_YEAR_COUNT = "1";
	protected static final String STORET_SITE_SUM_ONE_YEAR_COUNT = "1";

	protected static final boolean POSTABLE = true;
	protected static final String ENDPOINT = HttpConstants.PERIOD_OF_RECORD_ENDPOINT + "?sorted=yes&"
			+ Parameters.DATA_PROFILE + "=" + PROFILE + "&mimeType=";

	@Test
	public void testHarness() throws Exception {
		getAsCsvTest();
		getAsCsvZipTest();
		getAllParametersTest();
		postGetCountTest();
	}
	public void getAsCsvTest() throws Exception {
		getAsDelimitedTest(ENDPOINT + CSV, HttpConstants.MIME_TYPE_CSV, CSV, PROFILE, POSTABLE);
	}

	public void getAsCsvZipTest() throws Exception {
		getAsDelimitedZipTest(ENDPOINT + CSV_AND_ZIP, HttpConstants.MIME_TYPE_ZIP, CSV, PROFILE, POSTABLE);
	}
	
	public void getAllParametersTest() throws Exception {
		getAllParametersTest(ENDPOINT + CSV, HttpConstants.MIME_TYPE_CSV, CSV,
				PROFILE, POSTABLE);
	}	

	public void postGetCountTest() throws Exception {
		String urlPrefix = HttpConstants.PERIOD_OF_RECORD_ENDPOINT + "/count?mimeType=";
		String compareObject = "{\"" + HttpConstants.HEADER_TOTAL_SITE_COUNT + "\":\"" + TOTAL_SITE_SUM_ONE_YEAR_COUNT
				+ "\",\"" + HEADER_STORET_SITE_COUNT + "\":\"" + STORET_SITE_SUM_ONE_YEAR_COUNT
				+ "\"}";
				
		postGetCountTest(urlPrefix, compareObject, PROFILE);
	}

	@Override
	public ResultActions unFilteredHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_SITE_COUNT, TOTAL_SITE_SUM_COUNT))
				.andExpect(header().string(HEADER_NWIS_SITE_COUNT, NWIS_SITE_SUM_COUNT))
				.andExpect(header().string(HEADER_STEWARDS_SITE_COUNT, STEWARDS_SITE_SUM_COUNT))
				.andExpect(header().string(HEADER_STORET_SITE_COUNT, STORET_SITE_SUM_COUNT))
				.andExpect(header().string(HEADER_BIODATA_SITE_COUNT, BIODATA_SITE_SUM_COUNT));
	}

	@Override
	public ResultActions unFilteredGeomHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_SITE_COUNT, TOTAL_SITE_SUM_COUNT))
				.andExpect(header().string(HEADER_NWIS_SITE_COUNT, NWIS_SITE_SUM_COUNT))
				.andExpect(header().string(HEADER_STEWARDS_SITE_COUNT, STEWARDS_SITE_SUM_COUNT))
				.andExpect(header().string(HEADER_STORET_SITE_COUNT, STORET_SITE_SUM_COUNT))
				.andExpect(header().string(HEADER_BIODATA_SITE_COUNT, BIODATA_SITE_SUM_COUNT));
	}

	@Override
	public ResultActions filteredHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_SITE_COUNT, TOTAL_SITE_SUM_ONE_YEAR_COUNT))
				.andExpect(header().string(HEADER_STORET_SITE_COUNT, STORET_SITE_SUM_ONE_YEAR_COUNT));
	}

	@Override
	public ResultActions noResultHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_SITE_COUNT, "0"));
	}
}
