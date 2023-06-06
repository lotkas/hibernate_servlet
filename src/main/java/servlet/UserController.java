package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import model.Sale;
import model.modelDTO.UserBuyDTO;
import model.modelDTO.UserDonateDTO;
import service.serviceImpl.ProductServiceImpl;
import service.serviceImpl.UserServiceImpl;
import utils.Utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet("/user")
public class UserController extends HttpServlet {
    private final ObjectMapper jacksonMapper = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
            .findAndRegisterModules();
    private final UserServiceImpl usersService = new UserServiceImpl();
    private final ProductServiceImpl productsService = new ProductServiceImpl();

    public UserController() {
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {         //buy product
        PrintWriter out = resp.getWriter();
        String rb = req.getReader().lines().collect(Collectors.joining());
        final UserBuyDTO request = jacksonMapper.readValue(rb, UserBuyDTO.class);
        Sale response = usersService.buyProduct(request);

        if (response == null) {
            Utils.returnNullResponse(resp, out);
        } else {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            String message = "Thanks!<br>" + response;
            out.print(message);
            out.flush();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {       //get List of products
        PrintWriter pw = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        List<Product> products = productsService.getAll();
        String json = jacksonMapper.writeValueAsString(products);
        pw.println(json);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {       //update user balance
        PrintWriter out = resp.getWriter();
        String rb = req.getReader().lines().collect(Collectors.joining());
        final UserDonateDTO request = jacksonMapper.readValue(rb, UserDonateDTO.class);
        UserDonateDTO response = usersService.update(request);

        if (response == null) {
            Utils.returnNullResponse(resp, out);
        } else {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            String message = "Balance updated!";
            out.print(message);
            out.flush();
        }
    }
}
