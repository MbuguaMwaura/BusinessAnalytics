import java.sql.Timestamp;
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
            model.put("accounts", Account.all());
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
            model.put("vendors", Vendor.all());
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
            model.put("incomes", Income.all());
            model.put("template", "templates/incomes.vtl");
            return new ModelAndView(model,layout);
        },new VelocityTemplateEngine());

        get("/expenses", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("expenses", Expense.all());
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

        get("/addvendor", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/addvendors.vtl");
            return new ModelAndView(model,layout);
        },new VelocityTemplateEngine());

        post("/addingvendor", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            int number =Integer.parseInt( request.queryParams("number"));
            String email = request.queryParams("email");
            Vendor vendor = new Vendor(name,email,number);
            vendor.save();
            String url = String.format("/vendors");
            response.redirect(url);
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/addingaccount", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            Account account = new Account(name);
            account.save();
            String url = String.format("/accounts");
            response.redirect(url);
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/addincome", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("accounts", Account.all());
            model.put("template", "templates/addincome.vtl");
            return new ModelAndView(model,layout);
        },new VelocityTemplateEngine());

        post("/addingincome", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            String description = request.queryParams("description");
            int amount = Integer.parseInt(request.queryParams("amount"));
            int account_id = Integer.parseInt(request.queryParams("account_id"));
            Income income = new Income(name,description,amount,account_id);
            income.save();
            String url = String.format("/incomes");
            response.redirect(url);
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/addexpense", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("accounts", Account.all());
            model.put("template", "templates/addexpense.vtl");
            return new ModelAndView(model,layout);
        },new VelocityTemplateEngine());

        post("/addingexpense", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            String description = request.queryParams("description");
            int amount = Integer.parseInt(request.queryParams("amount"));
            int account_id = Integer.parseInt(request.queryParams("account_id"));
            Expense expense = new Expense(name,description,amount,account_id);
            expense.save();
            String url = String.format("/expenses");
            response.redirect(url);
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/addproductpurchase", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/addproductpurchase.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/purchases", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("purchases", Purchases.all());
            model.put("template", "templates/purchases.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/addingproductpurchase", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            String description = request.queryParams("description");
            int amount = Integer.parseInt(request.queryParams("amount"));
            int vendor_id = Integer.parseInt(request.queryParams("vendor_id"));
            Purchases purchases = new Purchases(name,amount,vendor_id,description);
            purchases.save();
            String url = String.format("/purchases");
            response.redirect(url);
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    }
}