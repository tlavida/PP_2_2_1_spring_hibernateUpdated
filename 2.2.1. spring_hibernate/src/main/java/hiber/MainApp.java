package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Model1", 1)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Model2", 2)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Model3", 3)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Model4", 4)));


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car model = "+user.getCar().getModel());
         System.out.println("Car series = "+user.getCar().getSeries());
         System.out.println();
      }


      List<Car> cars = carService.listCars();
      for (Car car : cars) {
         System.out.println("Car model = "+car.getModel());
         System.out.println("Car series = "+car.getSeries());
         System.out.println();
      }


      System.out.println("Получение пользователя по модели и серии автомобиля:");

      User user = userService.getUserByCar("Model2", 2);
      System.out.println("Автомобиль принадлежит пользователю с данными: " + user.getCar().getModel() + " " + user.getCar().getSeries() + " - " + user.getFirstName());


      context.close();
   }
}
