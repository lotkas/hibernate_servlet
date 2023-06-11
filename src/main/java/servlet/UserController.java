package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import model.modelDTO.GeneralDTO;
import model.modelDTO.userDTO.UserIdRequestDTO;
import model.modelDTO.userDTO.UserSaveRequestDTO;
import model.modelDTO.userDTO.UserUpdateDTO;
import service.UserService;
import utils.Utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;


@WebServlet("/user")
public class UserController extends HttpServlet {
    private final ObjectMapper jacksonMapper = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
            .findAndRegisterModules();
    private final UserService userService = new UserService();

    public UserController() {
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {         //add new User
        PrintWriter out = resp.getWriter();
        String rb = req.getReader().lines().collect(Collectors.joining());
        final UserSaveRequestDTO request = jacksonMapper.readValue(rb, UserSaveRequestDTO.class);
        GeneralDTO<User> response = userService.save(request);

        if (response.getEntity() == null) {
            Utils.returnNullResponse(resp, out, response.getMessage());
        } else {
            String message = "Thanks!<br>" + response.getEntity();
            Utils.sendResponse(resp, message);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {       //get users by id or list
        PrintWriter out = resp.getWriter();
        String rb = req.getReader().lines().collect(Collectors.joining());
        final UserIdRequestDTO request = jacksonMapper.readValue(rb, UserIdRequestDTO.class);

        if (request.getUserId() == 0) {
            GeneralDTO<User> responseAll = userService.getAll();
            String json = jacksonMapper.writeValueAsString(responseAll.getEntityList());
            Utils.sendResponse(resp, json);
        } else {
            GeneralDTO<User> responseById = userService.getById(request.getUserId());
            if (responseById.getEntity() == null) {
                Utils.returnNullResponse(resp, out, responseById.getMessage());
            } else {
                String json = jacksonMapper.writeValueAsString(responseById.getEntity());
                Utils.sendResponse(resp, json);
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {       //update user
        PrintWriter out = resp.getWriter();
        String rb = req.getReader().lines().collect(Collectors.joining());
        final UserUpdateDTO request = jacksonMapper.readValue(rb, UserUpdateDTO.class);
        GeneralDTO<User> response = userService.update(request);

        if (response.getEntity() == null) {
            Utils.returnNullResponse(resp, out, response.getMessage());
        } else {
            String message = "User updated: " + response.getEntity();
            Utils.sendResponse(resp, message);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {    //delete manager by id
        PrintWriter out = resp.getWriter();
        String rb = req.getReader().lines().collect(Collectors.joining());
        final UserIdRequestDTO request =jacksonMapper.readValue(rb, UserIdRequestDTO.class);
        GeneralDTO<User> response = userService.deleteById(request.getUserId());

        if (response.getEntity() == null) {
            Utils.returnNullResponse(resp, out, response.getMessage());
        } else {
            String message = "User with id: " + response.getEntity().getId() + " was deleted <br>" + response.getEntity();
            Utils.sendResponse(resp, message);
        }
    }
}
