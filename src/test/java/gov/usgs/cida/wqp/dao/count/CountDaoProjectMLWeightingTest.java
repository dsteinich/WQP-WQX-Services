package gov.usgs.cida.wqp.dao.count;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.DBIntegrationTest;
import gov.usgs.cida.wqp.dao.CountDao;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.mapping.CountColumn;
import gov.usgs.cida.wqp.parameter.FilterParameters;


@Category(DBIntegrationTest.class)
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class CountDaoProjectMLWeightingTest extends BaseCountDaoTest {
	private static final Logger LOG = LoggerFactory.getLogger(CountDaoProjectMLWeightingTest.class);

	@Autowired
	CountDao countDao;

	@Test
	public void testHarness() {
		nullParameterTest();
		emptyParameterTest();
		analyticalMethodTest();
		assemblageTest();
//		bboxTest();
		characteristicNameTest();
		characteristicTypeTest();
//		countryTest();
//		countyTest();
//		huc2Test();
//		huc3Test();
//		huc4Test();
//		huc5Test();
//		huc6Test();
//		huc7Test();
//		huc8Test();
//		huc10Test();
//		huc12Test();
//		minActivitiesTest();
//		minResultsTest();
//		nldiUrlTest();
//		organizationTest();
		pcodeTest();
//		projectTest();
//		providersTest();
		sampleMediaTest();
//		siteIdTest();
//		manySitesTest();
//		siteTypeTest();
		startDateHiTest();
		startDateLoTest();
//		stateTest();
		subjectTaxonomicNameTest();
//		withinTest();
//		multipleParameterStationSumTest();
//		multipleParameterActivitySumTest();
//		multipleParameterActivitySumStationSumTest();
//		multipleParameterResultSumTest();
//		multipleParameterResultSumStationSumTests();
//		multipleParameterResultSumActivitySumTests();
	}

	public void nullParameterTest() {
		FilterParameters filter = new FilterParameters();
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, null);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, 5, TOTAL_PRJ_ML_WEIGHTING_COUNT, NWIS_PRJ_ML_WEIGHTING_COUNT, STEWARDS_PRJ_ML_WEIGHTING_COUNT, STORET_PRJ_ML_WEIGHTING_COUNT, BIODATA_PRJ_ML_WEIGHTING_COUNT);
	}

	public void emptyParameterTest() {
		FilterParameters filter = new FilterParameters();
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, 5, TOTAL_PRJ_ML_WEIGHTING_COUNT, NWIS_PRJ_ML_WEIGHTING_COUNT, STEWARDS_PRJ_ML_WEIGHTING_COUNT, STORET_PRJ_ML_WEIGHTING_COUNT, BIODATA_PRJ_ML_WEIGHTING_COUNT);
	}

	public void sampleMediaTest() {
		FilterParameters filter = new FilterParameters();
		filter.setSampleMedia(getSampleMedia());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, 5, "6", "2", "1", "2", "1");
	}

	public void startDateHiTest() {
		FilterParameters filter = new FilterParameters();
		filter.setStartDateHi(getStartDateHi());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, 5, "6", "2", "1", "2", "1");
	}

	public void startDateLoTest() {
		FilterParameters filter = new FilterParameters();
		filter.setStartDateLo(getStartDateLo());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, 4, "4", "2", "1", null, "1");
	}

	public void analyticalMethodTest() {
		FilterParameters filter = new FilterParameters();
		filter.setAnalyticalmethod(getAnalyticalMethod());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, 2, "2", "2", null, null, null);
	}

	public void assemblageTest() {
		FilterParameters filter = new FilterParameters();
		filter.setAssemblage(getAssemblage());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, 2, "1", null, null, null, "1");
	}

	public void characteristicNameTest() {
		FilterParameters filter = new FilterParameters();
		filter.setCharacteristicName(getCharacteristicName());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, 0, null, null, null, null, null);
	}

	public void characteristicTypeTest() {
		FilterParameters filter = new FilterParameters();
		filter.setCharacteristicType(getCharacteristicType());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, 2, "1", null, "1", null, null);
	}

	public void pcodeTest() {
		FilterParameters filter = new FilterParameters();
		filter.setPCode(getPcode());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, 2, "2", "2", null, null, null);
	}

	public void subjectTaxonomicNameTest() {
		FilterParameters filter = new FilterParameters();
		filter.setSubjectTaxonomicName(getSubjectTaxonomicName());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, 2, "1", null, null, null, "1");
	}
}