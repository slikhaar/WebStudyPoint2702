package rest;

import com.google.gson.Gson;
import exception.PersonNotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import model.IPersonFacade;
import model.MockPersonFacade;
import model.Person;

@Path("person")
public class PersonResource {

    static IPersonFacade facade = MockPersonFacade.getFacade(false, true);
    static Gson gson = new Gson();

    @Context
    private UriInfo context;

    public PersonResource() {
    }

    @GET
    @Produces("application/json")
    public String getAll() {
        return gson.toJson(facade.getPersons());
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public String getPerson(@PathParam("id") int id) throws PersonNotFoundException {
        return gson.toJson(facade.getPerson(id));
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public String AddPerson(String jsonPerson) {
        Person p = gson.fromJson(jsonPerson, Person.class);
        return gson.toJson(facade.addPerson(p));
    }

    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    public String EditPerson(String jsonPerson) throws PersonNotFoundException {
        Person p = gson.fromJson(jsonPerson, Person.class);
        return gson.toJson(facade.editPerson(p));
    }

    @DELETE
    @Consumes("text/plain")
    @Path("/{id}")
    public String deletePerson(@PathParam("id") int id) throws PersonNotFoundException {
        return gson.toJson(facade.deletePerson(id));
    }
}
