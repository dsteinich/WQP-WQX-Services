package gov.usgs.wma.wqp.mapping;

import static gov.usgs.wma.wqp.mapping.OrganizationColumn.KEY_ORGANIZATION_NAME;
import static gov.usgs.wma.wqp.mapping.OrganizationColumn.KEY_ORGANIZATION;
import static gov.usgs.wma.wqp.mapping.OrganizationColumn.KEY_ORGANIZATION_TYPE;
import static gov.usgs.wma.wqp.mapping.OrganizationColumn.KEY_ORGANIZATION_SUMMARY;
import static gov.usgs.wma.wqp.mapping.OrganizationColumn.KEY_ACTIVITY_COUNT;
import static gov.usgs.wma.wqp.mapping.OrganizationColumn.KEY_ORGANIZATION_SUMMARY_WQP_URL;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import gov.usgs.wma.wqp.BaseTest;

import static gov.usgs.wma.wqp.mapping.OrganizationColumn.KEY_LAST_RESULT;
import static gov.usgs.wma.wqp.mapping.OrganizationColumn.KEY_SITE_COUNT;

public class TestSummaryOrganizationMap {

	public static final String VALUE_ORGANIZATION_SUMMARY_CURRENT_YEAR = "[{\"year\": 2018,\"start\": \"2018-01-01\",\"end\": \"2018-12-31\",\"activityCount\": 2,\"resultCount\": 22,\"monitoringLocationsSampled\": 1,\"characteristicGroupResultCount\": {\"Nutrient\": 10,\"Physical\": 12},\"characteristicNameSummary\": [{\"characteristicName\": \"Ammonia\",\"characteristicType\": \"Nutrient\",\"activityCount\": 2,\"resultCount\": 2,\"monitoringLocationCount\": 1}, {\"characteristicName\": \"pH\",\"characteristicType\": \"Physical\",\"activityCount\": 2,\"resultCount\": 2,\"monitoringLocationCount\": 1}, {\"characteristicName\": \"Ammonia as NH3\",\"characteristicType\": \"Nutrient\",\"activityCount\": 2,\"resultCount\": 2,\"monitoringLocationCount\": 1}]}]";
	public static final String VALUE_ORGANIZATION_SUMMARY_FIVE_YEAR = "[{\"year\":2000, \"start\":\"2000-01-01\", \"end\":\"2000-12-31\", \"activityCount\":41, \"resultCount\":885, \"monitoringLocationsSampled\":35, \"characteristicGroupResultCount\":{\"Inorganics, Major, Metals\":120, \"Inorganics, Minor, Metals\":645, \"Inorganics, Minor, Non-metals\":120}, \"characteristicNameSummary\":[{\"characteristicName\":\"Calcium\", \"characteristicType\":\"Inorganics, Major, Metals\", \"activityCount\":30, \"resultCount\":30, \"monitoringLocationCount\":26}, {\"characteristicName\":\"Magnesium\", \"characteristicType\":\"Inorganics, Major, Metals\", \"activityCount\":30, \"resultCount\":30, \"monitoringLocationCount\":26}]}, {\"year\":2001, \"start\":\"2001-01-01\", \"end\":\"2001-12-31\", \"activityCount\":41, \"resultCount\":885, \"monitoringLocationsSampled\":35, \"characteristicGroupResultCount\":{\"Inorganics, Major, Metals\":120, \"Inorganics, Minor, Metals\":645, \"Inorganics, Minor, Non-metals\":120}, \"characteristicNameSummary\":[{\"characteristicName\":\"Calcium\", \"characteristicType\":\"Inorganics, Major, Metals\", \"activityCount\":30, \"resultCount\":30, \"monitoringLocationCount\":26}, {\"characteristicName\":\"Magnesium\", \"characteristicType\":\"Inorganics, Major, Metals\", \"activityCount\":30, \"resultCount\":30, \"monitoringLocationCount\":26}]}, {\"year\":2002, \"start\":\"2002-01-01\", \"end\":\"2002-12-31\", \"activityCount\":41, \"resultCount\":885, \"monitoringLocationsSampled\":35, \"characteristicGroupResultCount\":{\"Inorganics, Major, Metals\":120, \"Inorganics, Minor, Metals\":645, \"Inorganics, Minor, Non-metals\":120}, \"characteristicNameSummary\":[{\"characteristicName\":\"Calcium\", \"characteristicType\":\"Inorganics, Major, Metals\", \"activityCount\":30, \"resultCount\":30, \"monitoringLocationCount\":26}, {\"characteristicName\":\"Magnesium\", \"characteristicType\":\"Inorganics, Major, Metals\", \"activityCount\":30, \"resultCount\":30, \"monitoringLocationCount\":26}]}, {\"year\":2003, \"start\":\"2003-01-01\", \"end\":\"2003-12-31\", \"activityCount\":41, \"resultCount\":885, \"monitoringLocationsSampled\":35, \"characteristicGroupResultCount\":{\"Inorganics, Major, Metals\":120, \"Inorganics, Minor, Metals\":645, \"Inorganics, Minor, Non-metals\":120}, \"characteristicNameSummary\":[{\"characteristicName\":\"Calcium\", \"characteristicType\":\"Inorganics, Major, Metals\", \"activityCount\":30, \"resultCount\":30, \"monitoringLocationCount\":26}, {\"characteristicName\":\"Magnesium\", \"characteristicType\":\"Inorganics, Major, Metals\", \"activityCount\":30, \"resultCount\":30, \"monitoringLocationCount\":26}]}, {\"year\":2004, \"start\":\"2004-01-01\", \"end\":\"2004-12-31\", \"activityCount\":2, \"resultCount\":22, \"monitoringLocationsSampled\":1, \"characteristicGroupResultCount\":{\"Nutrient\":10, \"Physical\":12}, \"characteristicNameSummary\":[{\"characteristicName\":\"Ammonia\", \"characteristicType\":\"Nutrient\", \"activityCount\":2, \"resultCount\":2, \"monitoringLocationCount\":1}, {\"characteristicName\":\"pH\", \"characteristicType\":\"Physical\", \"activityCount\":2, \"resultCount\":2, \"monitoringLocationCount\":1}, {\"characteristicName\":\"Ammonia as NH3\", \"characteristicType\":\"Nutrient\", \"activityCount\":2, \"resultCount\":2, \"monitoringLocationCount\":1}]}, {\"year\":2005, \"start\":\"2005-01-01\", \"end\":\"2005-12-31\", \"activityCount\":2, \"resultCount\":22, \"monitoringLocationsSampled\":1, \"characteristicGroupResultCount\":{\"Nutrient\":10, \"Physical\":12}, \"characteristicNameSummary\":[{\"characteristicName\":\"Ammonia\", \"characteristicType\":\"Nutrient\", \"activityCount\":2, \"resultCount\":2, \"monitoringLocationCount\":1}, {\"characteristicName\":\"pH\", \"characteristicType\":\"Physical\", \"activityCount\":2, \"resultCount\":2, \"monitoringLocationCount\":1}, {\"characteristicName\":\"Ammonia as NH3\", \"characteristicType\":\"Nutrient\", \"activityCount\":2, \"resultCount\":2, \"monitoringLocationCount\":1}]}]";
	public static final String VALUE_ORGANIZATION_SUMMARY_ALL_YEAR = "[{\"year\":2000, \"start\":\"2000-01-01\", \"end\":\"2000-12-31\", \"activityCount\":41, \"resultCount\":885, \"monitoringLocationsSampled\":35, \"characteristicGroupResultCount\":{\"Inorganics, Major, Metals\":120, \"Inorganics, Minor, Metals\":645, \"Inorganics, Minor, Non-metals\":120}, \"characteristicNameSummary\":[{\"characteristicName\":\"Calcium\", \"characteristicType\":\"Inorganics, Major, Metals\", \"activityCount\":30, \"resultCount\":30, \"monitoringLocationCount\":26}, {\"characteristicName\":\"Magnesium\", \"characteristicType\":\"Inorganics, Major, Metals\", \"activityCount\":30, \"resultCount\":30, \"monitoringLocationCount\":26}]}, {\"year\":2001, \"start\":\"2001-01-01\", \"end\":\"2001-12-31\", \"activityCount\":41, \"resultCount\":885, \"monitoringLocationsSampled\":35, \"characteristicGroupResultCount\":{\"Inorganics, Major, Metals\":120, \"Inorganics, Minor, Metals\":645, \"Inorganics, Minor, Non-metals\":120}, \"characteristicNameSummary\":[{\"characteristicName\":\"Calcium\", \"characteristicType\":\"Inorganics, Major, Metals\", \"activityCount\":30, \"resultCount\":30, \"monitoringLocationCount\":26}, {\"characteristicName\":\"Magnesium\", \"characteristicType\":\"Inorganics, Major, Metals\", \"activityCount\":30, \"resultCount\":30, \"monitoringLocationCount\":26}]}, {\"year\":2002, \"start\":\"2002-01-01\", \"end\":\"2002-12-31\", \"activityCount\":41, \"resultCount\":885, \"monitoringLocationsSampled\":35, \"characteristicGroupResultCount\":{\"Inorganics, Major, Metals\":120, \"Inorganics, Minor, Metals\":645, \"Inorganics, Minor, Non-metals\":120}, \"characteristicNameSummary\":[{\"characteristicName\":\"Calcium\", \"characteristicType\":\"Inorganics, Major, Metals\", \"activityCount\":30, \"resultCount\":30, \"monitoringLocationCount\":26}, {\"characteristicName\":\"Magnesium\", \"characteristicType\":\"Inorganics, Major, Metals\", \"activityCount\":30, \"resultCount\":30, \"monitoringLocationCount\":26}]}, {\"year\":2003, \"start\":\"2003-01-01\", \"end\":\"2003-12-31\", \"activityCount\":41, \"resultCount\":885, \"monitoringLocationsSampled\":35, \"characteristicGroupResultCount\":{\"Inorganics, Major, Metals\":120, \"Inorganics, Minor, Metals\":645, \"Inorganics, Minor, Non-metals\":120}, \"characteristicNameSummary\":[{\"characteristicName\":\"Calcium\", \"characteristicType\":\"Inorganics, Major, Metals\", \"activityCount\":30, \"resultCount\":30, \"monitoringLocationCount\":26}, {\"characteristicName\":\"Magnesium\", \"characteristicType\":\"Inorganics, Major, Metals\", \"activityCount\":30, \"resultCount\":30, \"monitoringLocationCount\":26}]}, {\"year\":2004, \"start\":\"2004-01-01\", \"end\":\"2004-12-31\", \"activityCount\":2, \"resultCount\":22, \"monitoringLocationsSampled\":1, \"characteristicGroupResultCount\":{\"Nutrient\":10, \"Physical\":12}, \"characteristicNameSummary\":[{\"characteristicName\":\"Ammonia\", \"characteristicType\":\"Nutrient\", \"activityCount\":2, \"resultCount\":2, \"monitoringLocationCount\":1}, {\"characteristicName\":\"pH\", \"characteristicType\":\"Physical\", \"activityCount\":2, \"resultCount\":2, \"monitoringLocationCount\":1}, {\"characteristicName\":\"Ammonia as NH3\", \"characteristicType\":\"Nutrient\", \"activityCount\":2, \"resultCount\":2, \"monitoringLocationCount\":1}]}, {\"year\":2005, \"start\":\"2005-01-01\", \"end\":\"2005-12-31\", \"activityCount\":2, \"resultCount\":22, \"monitoringLocationsSampled\":1, \"characteristicGroupResultCount\":{\"Nutrient\":10, \"Physical\":12}, \"characteristicNameSummary\":[{\"characteristicName\":\"Ammonia\", \"characteristicType\":\"Nutrient\", \"activityCount\":2, \"resultCount\":2, \"monitoringLocationCount\":1}, {\"characteristicName\":\"pH\", \"characteristicType\":\"Physical\", \"activityCount\":2, \"resultCount\":2, \"monitoringLocationCount\":1}, {\"characteristicName\":\"Ammonia as NH3\", \"characteristicType\":\"Nutrient\", \"activityCount\":2, \"resultCount\":2, \"monitoringLocationCount\":1}]}]";

	public static final Map<String, Object> SUMMARY_ORGANIZATION_CURRENT_YEAR;
	static {
		SUMMARY_ORGANIZATION_CURRENT_YEAR = new LinkedHashMap<>();
		SUMMARY_ORGANIZATION_CURRENT_YEAR.put(KEY_ORGANIZATION_SUMMARY, VALUE_ORGANIZATION_SUMMARY_CURRENT_YEAR);
		SUMMARY_ORGANIZATION_CURRENT_YEAR.put(KEY_LAST_RESULT, "2018-12-31");
		SUMMARY_ORGANIZATION_CURRENT_YEAR.put(KEY_SITE_COUNT, BigDecimal.valueOf(45));
		SUMMARY_ORGANIZATION_CURRENT_YEAR.put(KEY_ACTIVITY_COUNT, BigDecimal.valueOf(70));
		SUMMARY_ORGANIZATION_CURRENT_YEAR.put(KEY_ORGANIZATION, "R10ELKHEADMINE");
		SUMMARY_ORGANIZATION_CURRENT_YEAR.put(KEY_ORGANIZATION_NAME, "EPA Region 10 Elkhead Mine, Douglas County, OR");
		SUMMARY_ORGANIZATION_CURRENT_YEAR.put(KEY_ORGANIZATION_TYPE, "Federal/US Government");
		SUMMARY_ORGANIZATION_CURRENT_YEAR.put(KEY_ORGANIZATION_SUMMARY_WQP_URL, BaseTest.getSiteUrlBase() + "/provider/STORET/R10ELKHEADMINE");
	}

	public static final Map<String, Object> SUMMARY_ORGANIZATION_FIVE_YEAR;
	static {
		SUMMARY_ORGANIZATION_FIVE_YEAR = new LinkedHashMap<>();
		SUMMARY_ORGANIZATION_FIVE_YEAR.put(KEY_ORGANIZATION_SUMMARY, VALUE_ORGANIZATION_SUMMARY_FIVE_YEAR);
		SUMMARY_ORGANIZATION_FIVE_YEAR.put(KEY_LAST_RESULT, "2005-12-31");
		SUMMARY_ORGANIZATION_FIVE_YEAR.put(KEY_SITE_COUNT, BigDecimal.valueOf(35));
		SUMMARY_ORGANIZATION_FIVE_YEAR.put(KEY_ACTIVITY_COUNT, BigDecimal.valueOf(75));
		SUMMARY_ORGANIZATION_FIVE_YEAR.put(KEY_ORGANIZATION, "R10ELKHEADMINE");
		SUMMARY_ORGANIZATION_FIVE_YEAR.put(KEY_ORGANIZATION_NAME, "EPA Region 10 Elkhead Mine, Douglas County, OR");
		SUMMARY_ORGANIZATION_FIVE_YEAR.put(KEY_ORGANIZATION_TYPE, "Federal/US Government");
		SUMMARY_ORGANIZATION_FIVE_YEAR.put(KEY_ORGANIZATION_SUMMARY_WQP_URL, BaseTest.getSiteUrlBase() + "/provider/STORET/R10ELKHEADMINE");

	}

	public static final Map<String, Object> SUMMARY_ORGANIZATION_ALL_YEAR;
	static {
		SUMMARY_ORGANIZATION_ALL_YEAR = new LinkedHashMap<>();
		SUMMARY_ORGANIZATION_ALL_YEAR.put(KEY_ORGANIZATION_SUMMARY, VALUE_ORGANIZATION_SUMMARY_ALL_YEAR);
		SUMMARY_ORGANIZATION_ALL_YEAR.put(KEY_LAST_RESULT, "2003-10-02");
		SUMMARY_ORGANIZATION_ALL_YEAR.put(KEY_SITE_COUNT, BigDecimal.valueOf(35));
		SUMMARY_ORGANIZATION_ALL_YEAR.put(KEY_ACTIVITY_COUNT, BigDecimal.valueOf(41));
		SUMMARY_ORGANIZATION_ALL_YEAR.put(KEY_ORGANIZATION, "R10ELKHEADMINE");
		SUMMARY_ORGANIZATION_ALL_YEAR.put(KEY_ORGANIZATION_NAME, "EPA Region 10 Elkhead Mine, Douglas County, OR");
		SUMMARY_ORGANIZATION_ALL_YEAR.put(KEY_ORGANIZATION_TYPE, "Federal/US Government");
		SUMMARY_ORGANIZATION_ALL_YEAR.put(KEY_ORGANIZATION_SUMMARY_WQP_URL, BaseTest.getSiteUrlBase() + "/provider/STORET/R10ELKHEADMINE");
	}
}
