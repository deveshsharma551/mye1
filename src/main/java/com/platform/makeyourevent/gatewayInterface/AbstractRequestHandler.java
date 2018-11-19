package com.platform.makeyourevent.gatewayInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;
import com.google.gson.Gson;
import com.platform.makeyourevent.dataaccessService.DataAccessService;
import com.platform.makeyourevent.model.Merchant;
import com.platform.makeyourevent.propertyHandler.PropertyUtils;


public abstract class AbstractRequestHandler implements ServiceHandler {
	
	@Autowired
	public DataAccessService dataAccessService;
	
	
	@Autowired
	private RequestHandlerFactory requestHandlerFactory;
	
	private Gson gson = new Gson();

	@PostConstruct
	public void registerAllHandler()
	{
		requestHandlerFactory.registerAllHandler(this);
	}
	
	
	public ResponseEntity handleRequests(Map<String,Object> inputs)
	{
		if(inputs.get("methodName").equals("POST"))
		{
			return handlePost(inputs);	
		}
		
 		if(inputs.get("methodName").equals("GET"))
		{
			return handleGET(inputs);	
		}
		if(inputs.get("methodName").equals("PUT"))
		{
			return handlePUT(inputs);	
		}
		if(inputs.get("methodName").equals("DELETE"))
		{
			return handleDELETE(inputs);	
		}
		return null;
		
	}


	public ResponseEntity handleDELETE(Map<String, Object> inputs) {
		// TODO Auto-generated method stub
		return null;
	}


	public ResponseEntity handlePUT(Map<String, Object> inputs) {
		// TODO Auto-generated method stub
		return null;
	}


	public ResponseEntity handleGET(Map<String, Object> inputs) {
		// TODO Auto-generated method stub
		return null;
	}


	public ResponseEntity handlePost(Map<String, Object> inputs) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Merchant map(Object input,Type type)
	{
		return gson.fromJson(input.toString(),type);
	}
	
	public Object schemaValidate(Type type, Merchant merchant) throws ProcessingException, JsonProcessingException, IOException {
		// TODO Auto-generated method stub
		try {
			Scanner scanner = new Scanner(new File("C:/workspace2/mye1/src/main/resources/jsonSchema/merchant_temp.json"), "UTF-8");
			String schemaText = scanner.useDelimiter("//A").next();
			scanner.close();
			Gson gson = new Gson();
			String jsonText = gson.toJson(merchant, type);
			boolean isJsonValid = isJsonValid(schemaText, jsonText);
			if(!isJsonValid)
			{
				ObjectMapper mapper = new ObjectMapper();
				JsonNode schemaTextNode = mapper.readTree(schemaText);
				JsonNode jsonTextNode = mapper.readTree(jsonText);
				return generateErrorReportString(schemaTextNode, jsonTextNode);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	private boolean isJsonValid(String schemaText, String jsonText) throws ProcessingException {
		// TODO Auto-generated method stub
		try {
			final JsonNode schemaNode = JsonLoader.fromString(schemaText);
			final JsonNode jsonNode =  JsonLoader.fromString(jsonText);
			final JsonNode schemaIdentifier = jsonNode.get("$schema");
			if (null == schemaIdentifier) {
				((ObjectNode) schemaNode).put("$schema","http://json-schema.org/draft-04/schema#");
			}
			final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
			JsonSchema jsonSchema =  factory.getJsonSchema(schemaNode);
			ProcessingReport report  = jsonSchema.validate(jsonNode);
			return report.isSuccess();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public JsonNode generateErrorReportString(JsonNode schemaTextNode, JsonNode jsonTextNode)  { 
		JsonNode errorReportJson = null;
		JsonValidator validator = JsonSchemaFactory.byDefault().getValidator();
		try {
			ProcessingReport report = validator.validate(schemaTextNode, jsonTextNode);
			for (final ProcessingMessage reportLine : report) {
				errorReportJson = reportLine.asJson();
			}
		} catch (ProcessingException e) {
		}
		
		return errorReportJson;
	}
	
}
