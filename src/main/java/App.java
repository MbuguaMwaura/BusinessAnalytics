import java.sql.Timestamp;
import java.util.*;


import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;



import static spark.Spark.*;


public class App{

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args){
        port(getHerokuAssignedPort());
//        ApiClient defaultClient = Configuration.getDefaultApiClient();
//
//        // Configure API key authorization: app_id
//        ApiKeyAuth app_id = (ApiKeyAuth) defaultClient.getAuthentication("app_id");
//        app_id.setApiKey("670fa679");
//
//        // Configure API key authorization: app_key
//        ApiKeyAuth app_key = (ApiKeyAuth) defaultClient.getAuthentication("app_key");
//        app_key.setApiKey("13b485a29c778cbb838844e853911a7b");


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

            model.put("total", Receipt.total());
            model.put("receipts", Receipt.all());
            model.put("template", "templates/receipts.vtl");
            return new ModelAndView(model,layout);
        },new VelocityTemplateEngine());

        get("/bills", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("total", Bill.total());
            model.put("prices", Bill.price());
            model.put("accounts", Account.all());
            model.put("vendors", Vendor.all());
            model.put("purchases", Purchases.all());
            model.put("bills", Bill.all());
            model.put("template", "templates/bills.vtl");
            return new ModelAndView(model,layout);
        },new VelocityTemplateEngine());

        get("/incomes", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("total", Income.total());
            model.put("incomes", Income.all());
            model.put("template", "templates/incomes.vtl");
            return new ModelAndView(model,layout);
        },new VelocityTemplateEngine());

        get("/expenses", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("total", Expense.total());
            model.put("expenses", Expense.all());
            model.put("template", "templates/expenses.vtl");
            return new ModelAndView(model,layout);
        },new VelocityTemplateEngine());

        get("/dash", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("unpaidbill", Bill.unpaid());
            model.put("unpaid", Receipt.unpaid());
            model.put("income", Income.total());
            model.put("expense", Expense.total());
            model.put("receipt", Receipt.total());
            model.put("bill", Bill.total());
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
            model.put("vendors", Vendor.all());
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

        get("/sales", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("sales", Sales.all());
            model.put("template", "templates/sales.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/addingproductsale", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            String description = request.queryParams("description");
            int amount = Integer.parseInt(request.queryParams("amount"));
            Sales sales= new Sales(name,amount,description);
            sales.save();
            String url = String.format("/sales");
            response.redirect(url);
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/addproductsale", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/addproductsale.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/addbill", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("vendors", Vendor.all());
            model.put("purchases", Purchases.all());
            model.put("accounts", Account.all());
            model.put("template", "templates/addbill.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/addingbill", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String description = request.queryParams("description");
            int quantity = Integer.parseInt(request.queryParams("quantity"));
            int price = Integer.parseInt(request.queryParams("price"));
            boolean paid = Boolean.parseBoolean(request.queryParams("paid"));
            int vendor_id = Integer.parseInt(request.queryParams("vendor_id"));
            int purchase_id = Integer.parseInt(request.queryParams("purchase_id"));
            int payment_id = Integer.parseInt(request.queryParams("account_id"));
            Bill bill= new Bill(description,quantity,price,paid,vendor_id,purchase_id,payment_id);
            bill.save();
            String url = String.format("/bills");
            response.redirect(url);
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/addreceipt", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("customers", Customer.all());
            model.put("sales", Sales.all());
            model.put("accounts", Account.all());
            model.put("template", "templates/addreceipt.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/addingreceipt", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String description = request.queryParams("description");
            int quantity = Integer.parseInt(request.queryParams("quantity"));
            int price = Integer.parseInt(request.queryParams("price"));
            boolean paid = Boolean.parseBoolean(request.queryParams("paid"));
            int customer_id = Integer.parseInt(request.queryParams("customer_id"));
            int sale_id = Integer.parseInt(request.queryParams("purchase_id"));
            int payment_id = Integer.parseInt(request.queryParams("account_id"));
            Receipt receipt= new Receipt(description,quantity,price,paid,customer_id,sale_id,payment_id);
            receipt.save();
            String url = String.format("/receipts");
            response.redirect(url);
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/dash", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/dash.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


        get("/", (request,response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String url = String.format("/dash");
            response.redirect(url);
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/signup", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template","templates/signup.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/signup", (request,response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String username = request.queryParams("username");
            String email = request.queryParams("email");
            String password = request.queryParams("password");
            String cpassword = request.queryParams("cpassword");

            if(!User.allEmails().contains(email)){
                if(password.equals(cpassword)){
                    User user = new User(email,password);
                    user.register();
                    model.put("email", user.getEmail());
//                    model.put("template", "templates/dash.vtl");
                    response.redirect("/dash");

                }
                else{
                    response.redirect("/signup");
                    System.out.println("Please confirm your password");
                }
            }
            else{
                System.out.println("Email already exists, Please login");
                response.redirect("/login");
            }
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/login", (request,response) ->{
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/login.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/login", (request,response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String email = request.queryParams("email");
            String password = request.queryParams("password");

            if (email.trim().isEmpty() || password.trim().isEmpty()){
                System.out.println("Please enter your username and password");
            }
            else {
                if(!(User.allEmails().contains(email))){
                    model.put("template", "templates/login.vtl");
                    System.out.println("Email does not exist");
                }
                else {
                    User user = new User(email,password);
                    if(user.getUserPassword().equals(password)){
                        model.put("email", email);
                        response.redirect("/dash");
                    }
                    else{
                        model.put("template","templates/login.vtl");
                        System.out.println("Wrong password");
                    }
                }
            }
            return new ModelAndView(model,layout);
        }, new VelocityTemplateEngine());

    }
}

