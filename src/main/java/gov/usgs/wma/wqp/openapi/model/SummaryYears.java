package gov.usgs.wma.wqp.openapi.model;

import io.swagger.v3.oas.models.media.Schema;

public class SummaryYears {

	public static Schema<String> getIt() {
		Schema<String> rtn = new Schema<>();
		rtn.setDescription("wow");
		return rtn;
	}
}
