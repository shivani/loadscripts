

//Snippet to import array of json inside a test for running each HTTP Post with a different payload.
//The index is updated to read the next payload from the json file defined and read directly from the inbuilt Apache Jmeter function __FileToString

import java.util.List;
import groovy.json.JsonBuilder;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerBase;
import groovy.json.JsonOutput;
int prev = -1;
log.info(vars.get('INDEX'));
if(vars.get('INDEX')==null){
	vars.put('INDEX',"0");
	prev = 0;
	index = prev;
}
else{
	String prevStr = vars.get('INDEX');
	prev = Integer.valueOf(prevStr);
	index = prev+1;
	vars.put('INDEX',String.valueOf(index));
}
sampler.setPostBodyRaw(true);
def slurper = new groovy.json.JsonSlurper()
String jsonFile = vars.get('jsonArrayOfInputs'); //Create this in User Defined Variables jsonArrayOfInputs mapped to : ${__FileToString(jsonArrayOfInputs.json,,)}
List oJson = (List)slurper.parseText(jsonFile);
Map singularJsonInput = (Map)oJson.get(index);
def orig = new JsonBuilder(singularJsonInput).toString();
sampler.addNonEncodedArgument("",orig, "");

