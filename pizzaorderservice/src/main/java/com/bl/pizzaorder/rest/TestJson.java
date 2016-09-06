package com.bl.pizzaorder.rest;

import java.io.File;
import java.io.IOException;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.bl.pizzaorder.model.Order;
import com.bl.pizzaorder.model.Pizza;

public class TestJson {
   public static void main(String[] args) {

     try {

	JsonFactory jfactory = new JsonFactory();

	/*** write to file ***/
	JsonGenerator jGenerator = jfactory.createJsonGenerator(new File(
			"c:\\Code\\user.json"), JsonEncoding.UTF8);
	jGenerator.writeStartObject(); // {
	Order o = new Order();
	   o.setOrderId(1);
	   Pizza p = new Pizza();
	   p.setPizzaId(3);
	   p.setPizzaType("us");
	   p.setSize("small");
	  /* p.setToppings("tp1,top2");
	   o.getPizzas().add(p);*/
	   
	   ObjectMapper mapper = new ObjectMapper();
	  
	   //Object to JSON in file
	   mapper.writeValue(new File("c:\\Code\\user.json"), o);

	   //Object to JSON in String
	   String jsonInString = mapper.writeValueAsString(o);

	jGenerator.close();

     } catch (JsonGenerationException e) {

	e.printStackTrace();

     } catch (JsonMappingException e) {

	e.printStackTrace();

     } catch (IOException e) {

	e.printStackTrace();

     }

   }

}