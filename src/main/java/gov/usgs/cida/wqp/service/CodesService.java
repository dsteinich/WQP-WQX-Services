package gov.usgs.cida.wqp.service;

import static gov.usgs.cida.wqp.exception.WqpExceptionId.METHOD_PARAM_BOUNDS;
import static gov.usgs.cida.wqp.exception.WqpExceptionId.METHOD_PARAM_EMPTY;
import static gov.usgs.cida.wqp.exception.WqpExceptionId.METHOD_PARAM_NULL;
import static gov.usgs.cida.wqp.exception.WqpExceptionId.URL_PARSING_EXCEPTION;
import gov.usgs.cida.wqp.exception.WqpException;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.util.WqpEnvProperties;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class CodesService implements WqpEnvProperties {
	private static final Logger LOG = LoggerFactory.getLogger(CodesService.class);
	
	private final String codesUrl;
	private final String mimeType;

	public CodesService(String codesUrl, String mimeType) {
		this.codesUrl = codesUrl;
		this.mimeType = mimeType;
	}
	
	public boolean validate(Parameters codeType, String code) throws WqpException {
		LOG.trace("validating {}={}",codeType, code);
		boolean response = false;
		
		try {
			URL url = makeCodesUrl(codeType, code);
			url.getContent();
			response = true;
//		} catch (FileNotFoundException e) {
//			//invalid parameter value
		} catch (IOException e) {
			// TODO better error handling? might be some exceptions we want to bubble up to top?
			e.printStackTrace();
		} catch (WqpException e) {
			// the empty sting will be a considered false validation
			if (e.getExceptionid() != METHOD_PARAM_EMPTY) {
				throw e;
			}
			
		}
		
		return response;
	}

	protected URL makeCodesUrl(Parameters codeType, String code) throws WqpException {
		LOG.trace("making codes url");
		
		if (codeType == null) {
			throw new WqpException(METHOD_PARAM_NULL, getClass(), "makeCodesUrl", "codeType");
		}
		if (code == null) {
			throw new WqpException(METHOD_PARAM_NULL, getClass(), "makeCodesUrl", "code");
		}
		if (StringUtils.isEmpty(code)) {
			throw new WqpException(METHOD_PARAM_EMPTY, getClass(), "makeCodesUrl", "code");
		}
		
		URL url = null;
		try {
			String urlStr = codesUrl +"/"+ codeType +"?value="+ URLEncoder.encode(code, HttpConstants.DEFAULT_ENCODING) + "&mimeType="+ mimeType;
			LOG.trace("making codes url : {}", urlStr);
			url = new URL(urlStr);
		} catch (MalformedURLException e ) {
			throw new WqpException(URL_PARSING_EXCEPTION, getClass(), "makeCodesUrl",
					"Invalid Code Lookup URL. Ensure that the wqpgateway.properties has a properly formated URL for " + CODES_URL);
		} catch (UnsupportedEncodingException e) {
			throw new WqpException(METHOD_PARAM_BOUNDS, getClass(), "makeCodesUrl",
					"Unable to encode code value " + code);
		}
		return url;
	}
}
