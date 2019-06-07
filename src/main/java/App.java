import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App{
    public static void main(String[] args){
        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

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

           if(username.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty() || cpassword.trim().isEmpty()){
               System.out.println("Enter all the fields");
           }
           else {
               if(!User.allEmails().contains(email)){
                   if(password.equals(cpassword)){
                       User user = new User(email,password);
                       user.register();
                       model.put("username", user.getUsername());
                       model.put("template", "templates/index.vtl");
                   }
                   else{
                       System.out.println("Please confirm your password");
                   }
               }
               else{
                   System.out.println("Email already exists, Please login");
                   response.redirect("/login");
               }
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
                        model.put("template", "templates/index.vtl");
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