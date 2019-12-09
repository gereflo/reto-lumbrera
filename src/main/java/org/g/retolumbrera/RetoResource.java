package org.g.retolumbrera;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import java.io.IOException;
import java.util.*;

import javax.validation.constraints.Null;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/Reto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class RetoResource {

    int idCompanie = 0;

    private Set<Companie> companies = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));
    private Set<Product> products = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public RetoResource() {
/*        companies.add(new Companie(0, "Bimbo"));
        companies.add(new Companie(1, "Sabritas"));
        idCompanie = companies.size();

        Set<Variation> variations = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

            variations.add(new Variation(1,"Rojo", "Marca de ejemplo", "12345", 1,0));
            variations.add(new Variation(2,"Rosa", "Marca de ejemplo", "k70", 3,0));
            variations.add(new Variation(3,"Amarillo", "Marca de ejemplo", "k71", 3,0));

        products.add(new Product(0,"Producto 1", 1, 10, 20, true, 1, variations));
        products.add(new Product(1,"Producto 2", 1, 10, 20, true, 1));
        products.add(new Product(2,"Cambio de nombre", 1, 10, 20, true, 1, variations));*/
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String saludo() {
        return "Esto aun funciona";
    }

//---------Companies functions-----------------

    //Return all the companies in the list
    @GET
    @Produces("application/json")
    @Path("/getCompanies")
    public String companies_list_show() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(companies));
    }


    //Add a companie in the list via Json Post
    @POST
    @Produces("application/json")
    //@Produces(MediaType.TEXT_PLAIN)
    @Path("/addCompanie/")
    public String addCompanyJson(@PathParam String jsonCompanies) {

        ObjectMapper mapper = new ObjectMapper();

        //JSON string to Java object
        String jsonInString = jsonCompanies;

        try {
            Companie obj = mapper.readValue(jsonInString, Companie.class);
            companies.add(obj);
            String jsonInString2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(companies);
            return jsonInString2;
        }catch (IOException e) {
            e.printStackTrace();
            return jsonInString + " is not a valid Json 4 companies";
        }
    }

    //add a companie in the list via get
    @GET
    @Produces("application/json")
    //@Produces(MediaType.TEXT_PLAIN)
    @Path("/addCompanie/{companie}")
    public String addCompany(@PathParam String companie) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        Companie obj = new Companie(idCompanie,companie);

        companies.add(obj);
        String jsonInString2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(companies);
        return jsonInString2;
    }

//----------------Products functions---------------
    @GET
    @Produces("application/json")
    @Path("/getProducts")
    public String product_list_show() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        return(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(products));

    }

    @POST //Falta validar sku
    @Path("/addProduct")
    public String add(String product) {
        int flag = 0;

        //System.out.println("---------------------------");
        System.out.println(product);

        ObjectMapper mapper = new ObjectMapper();


        try {
            Product obj = mapper.readValue(product, Product.class);
            if(obj.stock > 0){
                System.out.println("Stock mayor a 0 " + obj.stock);
                if(obj.cost < obj.price){
                    System.out.println("Costo de " + obj.cost + " menor que "+ obj.price);
                    int id = obj.companies_id;
                    Boolean findCompanie = findCompanie(obj.companies_id);
                    if (findCompanie == true) {
                        System.out.println("Id de compañia existente");
                        if(obj.variations == null){
                            System.out.println("    Objeto sin variaciones");
                            flag = 1;
                        }
                        else{
                            System.out.println("    Objeto CON variaciones");
                            for (Variation temp2 : obj.variations) {
                                int stock = temp2.stock;
                                if(stock > 0){
                                    System.out.println("    Stock de " + stock);
                                    boolean sku = findsku(temp2.sku);
                                    if(sku == false){
                                        flag = 1;
                                    }
                                    else {
                                        return ("sku ya existente" + temp2.sku);
                                    }

                                }
                                else{
                                    System.out.println("    Stock de cero o menos " + stock);
                                    flag = 0;
                                }
                            }
                        }
                    }
                }
            }
            if(flag == 1){
                products.add(obj);
                String jsonInString2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(products);
                return "Agregdo";
            }
            else{
                return "Verifique su compañia";
            }


        }catch (IOException e) {
            e.printStackTrace();
            return " is not a valid Json 4 products";
        }
    }

    @POST//Falta validar sku
    @Path("/updateProduct")
    public String update(String product) throws JsonProcessingException {

        System.out.println("update product");
        //System.out.println(product);
        ObjectMapper mapper = new ObjectMapper();
        Product delete = new Product();
        int flag = 0;

        int id = 0;
        String name = "";
        int stock = 0;
        double cost = 0;
        double price = 0;
        boolean has_iva = true;
        int companie_id = 0;
        Set<Variation> var = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

        try{
            Product newObj = mapper.readValue(product, Product.class);

            int idNew = newObj.id;
            for (Product old : products) {
                if (old.id == idNew) {
                    System.out.println("Producto encontrado");
                    Boolean findCompanie = findCompanie(newObj.companies_id);
                    if (findCompanie == true){
                        //System.out.println(temp.id + " comparar " + newObj.companies_id);
                        System.out.println("Companhia existente");
                        delete = old;

                        id = idNew;
                        name = newObj.name;
                        stock = newObj.stock;
                        cost = newObj.cost;
                        price = newObj.price;
                        has_iva = newObj.has_iva;
                        var = newObj.variations;
                        companie_id = newObj.companies_id;
                        if (stock > 0) {
                            if (cost < price) {
                                System.out.println("Es posible hacer update");
                                flag =1;
                            }
                            else {
                                return ("No es posible hacer updade, costo mas alto que presio");
                            }
                        }
                        else{
                            return ("No es posible hacer updade, stock en 0");
                        }

                    }
                    else{
                        return ("Compania inexistente");
                    }

                }
            }

        }catch (IOException e) {
            e.printStackTrace();
            return " is not a valid Json 4 update";
        }

        if(flag == 1){
            drop(id);
            products.add(new Product(id,name, stock,cost,price,has_iva, companie_id, var));
            String show = product_list_show();
            return show;
        }
        return "Companhia no encontrada";
    }

    @GET
    @Path("/dropProduct/{idProduct}")
    public String drop(@PathParam int idProduct) throws JsonProcessingException {

        Product delete = new Product();


        for (Product temp : products) {
            int id = temp.id;
            if(idProduct == id){
                delete = temp;
                //products.remove(delete);

            }
        }

        products.remove(delete);
        System.out.println("Producto borrado");

        ObjectMapper mapper = new ObjectMapper();
        return(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(products));
    }

    @GET
    @Path("/test/{id}")
    public String find(@PathParam String id){
        System.out.println("busqueda de sku");

        boolean b = findsku(id);

        if (b == true){
            return ("sku encontrado");
        }
        else{
            return ("no se encontro nada");
        }
    }

    //Funcion que busca si un id de una compañia existe
    public boolean findCompanie(int idCompanie){

        boolean flag = false;

        for (Companie temp : companies){
            if(temp.id == idCompanie){
                flag = true;
                break;
            }
        }

        return flag;
    }


    public boolean findsku(String sku){

        boolean flag = false;
        ArrayList<String> skuList = new ArrayList<String>();

        for (Product tempP : products){
            Set<Variation> list = tempP.variations;
            if(tempP.variations != null){
                for (Variation tempV : list){
                    skuList.add(tempV.sku);
                }
            }
        }

        flag = skuList.contains(sku);
        //System.out.println(skuList);
        return flag;
    }

    public boolean findProduct(int idProduct){

        boolean flag = false;

        for (Product temp : products){
            if(temp.id == idProduct){
                flag = true;
                break;
            }
        }

        return flag;
    }
}