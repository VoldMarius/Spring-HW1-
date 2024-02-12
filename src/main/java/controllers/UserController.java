package controllers;


import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import service.UserService;

import java.util.List;
@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
       /*  Получение данных о пользователях: Метод findAll  обьекта userService
        возвращает список объектов типа User, содержащих информацию о всех пользователях.
        */

        model.addAttribute("users", users);
        return "user-list";/*добавление атрибута "users" в объект модели (Model).
        Атрибут "users" будет содержать список пользователей (переменная users),
        который был получен и передан контроллеру.*/

    }

    @GetMapping("/user-create")
    /*Аннотация @GetMapping указывает на то,что данный метод контроллера обрабатывает GET запросы
    по указанному URL "/user-create". То есть, этот метод будет вызываться при обращении
    к URL "/user-create" методом GET.*/

    public String createUserForm(User user)
    /*метод принимает объект пользователя (User user) в качестве параметра.
     Объект пользователя будет передаваться в метод контроллера при отправке GET запроса на URL "/user-create".*/
    {return "user-create";}/* После обработки запроса и данных в методе контроллера,
     будет возвращено указанное представление для отображения формы создания пользователя -страницу с формой
      для создания нового пользователя, которая была предоставлена контроллером через представление..*/

    @PostMapping("/user-create")/*Аннотация @PostMapping указывает на то, что данный метод
    будет вызываться при отправке POST запроса на URL "/user-create".*/
    public String createUser(User user)
    {userService.saveUser(user);
        return "redirect:/users";    }/*После успешного сохранения пользователя,
        возвращается строка "redirect:/users". Это указывает на то, что клиент будет
        перенаправлен на URL "/users". То есть, после сохранения , пользователь
        будет перенаправлен на страницу, где отображаются все пользователи (например, список всех пользователей).*/

    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") int id)  /*метод принимает параметр id, который извлекается
     из пути URL и передается в метод контроллера как аргумент. Аннотация @PathVariable указывает
     Spring Framework извлекать значение переменной из пути URL и передавать его в качестве параметра методу.*/
    { userService.deleteById(id);/*вызывается метод deleteById() сервиса userService, который удаляет
         пользователя из базы данных по указанному идентификатору (id).*/
        return "redirect:/users";/*После успешного удаления пользователя,клиент будет перенаправлен на URL "/users".
         То есть, после удаления пользователя, пользователь будет перенаправлен на страницу,
         где отображаются все пользователи (например, список всех пользователей).*/
    }

    @GetMapping("/user-update/{id}")
    public String getOneUser(@PathVariable("id") int id, Model model)
    {
        User user = userService.getOneUserByID(id);/*получает информацию о пользователе из базы данных
        по указанному идентификатору (id).*/
        model.addAttribute("user", user);/* Полученная информация о пользователе сохраняется в объекте user.*/
        return "/user-update";/* будет отображено представление "user-update" с информацией о пользователе
        для редактирования или просмотра.*/
    }

    @PostMapping("/user-update")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }
}