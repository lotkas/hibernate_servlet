package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Manager;
import model.modelDTO.GeneralDTO;
import model.modelDTO.managerDTO.ManagerIdRequestDTO;
import model.modelDTO.managerDTO.ManagerSaveRequestDTO;
import model.modelDTO.managerDTO.ManagerUpdateRequestDTO;
import service.ManagerService;
import utils.Utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet("/manager")
public class ManagerController extends HttpServlet {
    private final ObjectMapper jacksonMapper = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
            .findAndRegisterModules();
    private final ManagerService managerService = new ManagerService();

    public ManagerController() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {       //get List or id manager
        PrintWriter out = resp.getWriter();
        String rb = req.getReader().lines().collect(Collectors.joining());
        final ManagerIdRequestDTO request = jacksonMapper.readValue(rb, ManagerIdRequestDTO.class);

        if (request.getManagerId() == 0) {
            GeneralDTO<Manager> responseAll = managerService.getAll();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            String json = jacksonMapper.writeValueAsString(responseAll.getEntityList());
            out.println(json);
            out.flush();
        } else {
            GeneralDTO<Manager> responseById = managerService.getById(request.getManagerId());
            if (responseById.getEntity() == null) {
                Utils.returnNullResponse(resp, out, responseById.getMessage());
            } else {
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                String json = jacksonMapper.writeValueAsString(responseById.getEntity());
                out.println(json);
                out.flush();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {      //add new manager
        PrintWriter out = resp.getWriter();
        String rb = req.getReader().lines().collect(Collectors.joining());
        final ManagerSaveRequestDTO request = jacksonMapper.readValue(rb, ManagerSaveRequestDTO.class);
        GeneralDTO<Manager> response = managerService.save(request);

        if (response.getEntity() == null) {
            Utils.returnNullResponse(resp, out, response.getMessage());
        } else {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print("Manager: " + response.getEntity() + "<br>has been added");
            out.flush();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {       //update manager
        PrintWriter out = resp.getWriter();
        String rb = req.getReader().lines().collect(Collectors.joining());
        final ManagerUpdateRequestDTO request = jacksonMapper.readValue(rb, ManagerUpdateRequestDTO.class);
        GeneralDTO<Manager> response = managerService.update(request);

        if (response.getEntity() == null) {
            Utils.returnNullResponse(resp, out, response.getMessage());
        } else {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print("Manager: <br>" + response.getEntity());
            out.flush();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {    //delete manager by id
        PrintWriter out = resp.getWriter();
        String rb = req.getReader().lines().collect(Collectors.joining());
        final ManagerIdRequestDTO request =jacksonMapper.readValue(rb, ManagerIdRequestDTO.class);
        GeneralDTO<Manager> response = managerService.deleteById(request.getManagerId());

        if (response.getEntity() == null) {
            Utils.returnNullResponse(resp, out, response.getMessage());
        } else {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            String message = "Manager with id: " + response.getEntity().getId() + " was deleted <br>" + response.getEntity();
            out.print(message);
            out.flush();
        }
    }
}
