package gov.usgs.wma.wqp.webservice;

import static gov.usgs.wma.wqp.openapi.model.ActivityCountJson.HEADER_NWIS_ACTIVITY_COUNT;
import static gov.usgs.wma.wqp.openapi.model.ActivityMetricCountJson.HEADER_NWIS_ACTIVITY_METRIC_COUNT;
import static gov.usgs.wma.wqp.openapi.model.ResDetectQntLmtCountJson.HEADER_NWIS_RES_DETECT_QNT_LMT_COUNT;
import static gov.usgs.wma.wqp.openapi.model.ResultCountJson.HEADER_NWIS_RESULT_COUNT;
import static gov.usgs.wma.wqp.openapi.model.StationCountJson.HEADER_NWIS_SITE_COUNT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import gov.usgs.wma.wqp.mapping.Profile;
import gov.usgs.wma.wqp.mapping.delimited.ActivityMetricDelimitedTest;
import gov.usgs.wma.wqp.parameter.FilterParameters;
import gov.usgs.wma.wqp.util.HttpConstants;

public class ActivityMetricControllerTest {

	protected ActivityMetricController controller;

	@BeforeEach
	public void setup() {
		controller = new ActivityMetricController(null, null, null, null, null, null, null);
		ActivityMetricController.remove();
	}

	@Test
	public void addCountHeadersTest() {
		MockHttpServletResponse response = new MockHttpServletResponse();
		String countHeader = controller.addCountHeaders(response, BaseControllerTest.getNwisRawCounts());
		checkCountheaders(countHeader, response);
	}

	@Test
	public void addCountHeadersRestTest() {
		MockHttpServletResponse response = new MockHttpServletResponse();
		String countHeader = controller.addCountHeaders(response, BaseControllerTest.getNwisRawCounts());
		checkCountheaders(countHeader, response);
	}

	private void checkCountheaders(String countHeader, MockHttpServletResponse response) {
		assertEquals(HttpConstants.HEADER_TOTAL_ACTIVITY_METRIC_COUNT, countHeader);
		assertEquals(BaseControllerTest.TEST_NWIS_STATION_COUNT, response.getHeaderValue(HEADER_NWIS_SITE_COUNT));
		assertEquals(BaseControllerTest.TEST_TOTAL_STATION_COUNT, response.getHeaderValue(HttpConstants.HEADER_TOTAL_SITE_COUNT));
		assertEquals(BaseControllerTest.TEST_NWIS_ACTIVITY_COUNT, response.getHeaderValue(HEADER_NWIS_ACTIVITY_COUNT));
		assertEquals(BaseControllerTest.TEST_TOTAL_ACTIVITY_COUNT, response.getHeaderValue(HttpConstants.HEADER_TOTAL_ACTIVITY_COUNT));
		assertEquals(BaseControllerTest.TEST_NWIS_ACTIVITY_METRIC_COUNT, response.getHeaderValue(HEADER_NWIS_ACTIVITY_METRIC_COUNT));
		assertEquals(BaseControllerTest.TEST_TOTAL_ACTIVITY_METRIC_COUNT, response.getHeaderValue(HttpConstants.HEADER_TOTAL_ACTIVITY_METRIC_COUNT));
		assertFalse(response.containsHeader(HEADER_NWIS_RESULT_COUNT));
		assertFalse(response.containsHeader(HttpConstants.HEADER_TOTAL_RESULT_COUNT));
		assertFalse(response.containsHeader(HEADER_NWIS_RES_DETECT_QNT_LMT_COUNT));
		assertFalse(response.containsHeader(HttpConstants.HEADER_TOTAL_RES_DETECT_QNT_LMT_COUNT));
	}

	@Test
	public void getMappingTest() {
		ActivityMetricDelimitedTest.assertActivityMetricProfile(controller.getMapping(Profile.ACTIVITY_METRIC));
	}

	@Test
	public void determineProfileTest() {
		assertEquals(Profile.ACTIVITY_METRIC, controller.determineProfile(null));

		FilterParameters filter = new FilterParameters();
		filter.setDataProfile("biological");
		assertEquals(Profile.BIOLOGICAL, controller.determineProfile(filter));
	}

}
