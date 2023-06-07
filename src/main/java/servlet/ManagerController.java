package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import model.modelDTO.GeneralDTO;
import model.modelDTO.ManagerAddDTO;
import model.modelDTO.ManagerDeleteDTO;
import model.modelDTO.ManagerUpdateDTO;
import service.serviceImpl.ManagerServiceImpl;
import service.serviceImpl.ProductServiceImpl;
import utils.Utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet("/manager")
public class ManagerController extends HttpServlet {
    private final ObjectMapper jacksonMapper = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
            .findAndRegisterModules();
    private final ProductServiceImpl productsService = new ProductServiceImpl();
    private final ManagerServiceImpl managerService = new ManagerServiceImpl();

    public ManagerController() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {       //get List of products
        PrintWriter pw = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        GeneralDTO<Product> products = productsService.getAll();
        String json = jacksonMapper.writeValueAsString(products);
        pw.println(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {      //add new product
        PrintWriter out = resp.getWriter();
        String rb = req.getReader().lines().collect(Collectors.joining());
        final ManagerAddDTO request = jacksonMapper.readValue(rb, ManagerAddDTO.class);
        GeneralDTO<Product> response = managerService.saveProduct(request);

        if (response.getEntity() == null) {
            Utils.returnNullResponse(resp, out, response.getMessage());
        } else {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print("Product: " + response + "<br>has been added");
            out.flush();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {       //update product
        PrintWriter out = resp.getWriter();
        String rb = req.getReader().lines().collect(Collectors.joining());
        final ManagerUpdateDTO request = jacksonMapper.readValue(rb, ManagerUpdateDTO.class);
        GeneralDTO<Product> response = managerService.updateAvailableProduct(request);

        if (response.getEntity() == null) {
            Utils.returnNullResponse(resp, out, response.getMessage());
        } else {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print("Product - " + response.getEntity().getName() + "<br>Available - " + response.getEntity().getAvailable());
            out.flush();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {    //delete product
        PrintWriter out = resp.getWriter();
        String rb = req.getReader().lines().collect(Collectors.joining());
        final ManagerDeleteDTO request = jacksonMapper.readValue(rb, ManagerDeleteDTO.class);
        GeneralDTO<Product> response = managerService.deleteProductById(request);

        if (response.getEntity() == null) {
            Utils.returnNullResponse(resp, out, response.getMessage());
        } else {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            String message = "Product was deleted: <br>" + response.getEntity();
            out.print(message);
            out.flush();
        }
    }
}
