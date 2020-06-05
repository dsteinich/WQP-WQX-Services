package gov.usgs.wma.wqp.openapi.model.post;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostParms {

	@JsonProperty("analyticalmethod")
	String[] analyticalmethod;
	@JsonProperty("bBox")
	String bBox;
	@JsonProperty("assemblage")
	String[] assemblage;
	@JsonProperty("characteristicName")
	String[] characteristicName;
	@JsonProperty("characteristicType")
	String[] characteristicType;
	@JsonProperty("countrycode")
	String[] countrycode;
	@JsonProperty("countycode")
	String[] countycode;
	@JsonProperty("dataProfile")
	String dataProfile;
	@JsonProperty("huc")
	String[] huc;
	@JsonProperty("lat")
	String lat;
	@JsonProperty("long")
	String longitude;
	@JsonProperty("mimeType")
	String mimeType;
	@JsonProperty("minactivities")
	String minactivities;
	@JsonProperty("minresults")
	String minresults;
	@JsonProperty("organization")
	String[] organization;
	@JsonProperty("pCode")
	String[] pCode;
	@JsonProperty("project")
	String[] project;
	@JsonProperty("sampleMedia")
	String[] sampleMedia;
	@JsonProperty("providers")
	String[] providers;
	@JsonProperty("siteid")
	String[] siteid;
	@JsonProperty("siteType")
	String[] siteType;
	@JsonProperty("statecode")
	String[] statecode;
	@JsonProperty("startDateHi")
	String startDateHi;
	@JsonProperty("startDateLo")
	String startDateLo;
	@JsonProperty("subjectTaxonomicName")
	String[] subjectTaxonomicName;
	@JsonProperty("nldiurl")
	String nldiurl;
	@JsonProperty("within")
	String within;
	@JsonProperty("zip")
	String zip;

}
