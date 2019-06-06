import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;



import static spark.Spark.*;


public class App{



    public static void main(String[] args){


        staticFileLocation("/public");
        String layout = "templates/layoutMbugua.vtl";


        get("/accounts", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/accounts.vtl");
            return new ModelAndView(model,layout);
        },new VelocityTemplateEngine());

        get("/customers", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("customers", Customer.all());
            model.put("template", "templates/customers.vtl");
            return new ModelAndView(model,layout);
        },new VelocityTemplateEngine());

        get("/productssales", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/productssales.vtl");
            return new ModelAndView(model,layout);
        },new VelocityTemplateEngine());

        get("/productspurchases", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/productspurchases.vtl");
            return new ModelAndView(model,layout);
        },new VelocityTemplateEngine());

        get("/vendors", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/vendors.vtl");
            return new ModelAndView(model,layout);
        },new VelocityTemplateEngine());

        get("/receipts", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/receipts.vtl");
            return new ModelAndView(model,layout);
        },new VelocityTemplateEngine());

        get("/bills", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/bills.vtl");
            return new ModelAndView(model,layout);
        },new VelocityTemplateEngine());

        get("/incomes", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/incomes.vtl");
            return new ModelAndView(model,layout);
        },new VelocityTemplateEngine());

        get("/expenses", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/expenses.vtl");
            return new ModelAndView(model,layout);
        },new VelocityTemplateEngine());

        get("/dash", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/dash.vtl");
            return new ModelAndView(model,layout);
        },new VelocityTemplateEngine());

        get("/addcustomer", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/addcustomer.vtl");
            return new ModelAndView(model,layout);
        },new VelocityTemplateEngine());

        post("/addingcustomer", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            int number =Integer.parseInt( request.queryParams("number"));
            String email = request.queryParams("email");
            Customer customer = new Customer(name,email,number);
            customer.save();
            String url = String.format("/customers");
            response.redirect(url);
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    }
}