/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unipiloto.testws.services;

import co.edu.unipiloto.testws.entity.Person;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Andres
 */
@Path("service")
public class Service {

    private static Map<Integer, Person> persons = new HashMap<Integer, Person>();

    static {
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            int id = i + 1;
            person.setId(id);
            person.setFullName("My wonderfull Person " + id);
            person.setAge(new Random().nextInt(40) + 1);
            person.setSalary(person.getAge() * 1000000 / 3);
            persons.put(id, person);
        }
    }

    @GET
    @Path("/getPersonByIdXml/{id}")
    @Produces("text/XML")
    public Person getPersonByIdXML(@PathParam("id") int id) {
        return persons.get(id);
    }

    @GET
    @Path("/getPersonByIdJson/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPersonByIdJson(@PathParam("id") int id) {
        return persons.get(id);
    }

    @GET
    @Path("/getAllPersonsInXml")
    @Produces(MediaType.APPLICATION_XML)
    public List<Person> getAllPersonsInXml() {
        return new ArrayList<>(persons.values());
    }

    @GET
    @Path("/getAllPersonsInJson")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAllPersonsInJson() {
        return new ArrayList<>(persons.values());
    }

    @GET
    @Path("/getAverageSalaryInXml")
    @Produces(MediaType.APPLICATION_XML)
    public String getAverageInSalaryXml() {
        int promedio = 0;
        ArrayList personas = new ArrayList<>(persons.values());
        for (int i = 0; i < persons.size(); i++) {
            promedio += ((Person) personas.get(i)).getSalary();
        }
        promedio /= persons.size();
        return "<promedio>" + promedio + "</promedio>";
    }

    @GET
    @Path("/getSalariesInJson")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSalariesInJson() {
        int sumaSalarios = 0;
        ArrayList personas = new ArrayList<>(persons.values());
        for (int i = 0; i < persons.size(); i++) {
            sumaSalarios += ((Person) personas.get(i)).getSalary();
        }
        return "{\"suma\" : " + sumaSalarios + "}";
    }

    @POST
    @Path("/addPersonInJson")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Person addPersonInJson(Person person) {
        System.out.println(person.getId());
        persons.put(new Integer(person.getId()), person);
        return person;
    }
}
