package servlet;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import model.modelDTO.GeneralDTO;
import model.modelDTO.productDTO.ProductDeleteByIdRequestDTO;
import model.modelDTO.productDTO.ProductIdRequestDTO;
import model.modelDTO.productDTO.ProductSaveRequestDTO;
import model.modelDTO.productDTO.ProductUpdateRequestDTO;
import service.ProductService;
import utils.Utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet("/product")
public class ProductController extends HttpServlet {
    private final ObjectMapper jacksonMapper = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
            .findAndRegisterModules();
    private final ProductService productService = new ProductService();

    public ProductController() {
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {         //add new product
        PrintWriter out = resp.getWriter();
        String rb = req.getReader().lines().collect(Collectors.joining());
        final ProductSaveRequestDTO request = jacksonMapper.readValue(rb, ProductSaveRequestDTO.class);
        GeneralDTO<Product> response = productService.save(request);

        if (response.getEntity() == null) {
            Utils.returnNullResponse(resp, out, response.getMessage());
        } else {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            String message = "New product: <br>" + response.getEntity();
            out.print(message);
            out.flush();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {       //get products by id or list
        PrintWriter out = resp.getWriter();
        String rb = req.getReader().lines().collect(Collectors.joining());
        final ProductIdRequestDTO request = jacksonMapper.readValue(rb, ProductIdRequestDTO.class);

        if (request.getProductId() == 0) {
            GeneralDTO<Product> responseAll = productService.getAll();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            String json = jacksonMapper.writeValueAsString(responseAll.getEntityList());
            out.println(json);
            out.flush();
        } else {
            GeneralDTO<Product> responseById = productService.getById(request.getProductId());
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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {       //update product
        PrintWriter out = resp.getWriter();
        String rb = req.getReader().lines().collect(Collectors.joining());
        final ProductUpdateRequestDTO request = jacksonMapper.readValue(rb, ProductUpdateRequestDTO.class);
        GeneralDTO<Product> response = productService.update(request);

        if (response.getEntity() == null) {
            Utils.returnNullResponse(resp, out, response.getMessage());
        } else {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            String message = "User updated: " + response.getEntity();
            out.print(message);
            out.flush();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {    //delete product by id
        PrintWriter out = resp.getWriter();
        String rb = req.getReader().lines().collect(Collectors.joining());
        final ProductDeleteByIdRequestDTO request =jacksonMapper.readValue(rb, ProductDeleteByIdRequestDTO.class);
        GeneralDTO<Product> response = productService.deleteById(request);

        if (response.getEntity() == null) {
            Utils.returnNullResponse(resp, out, response.getMessage());
        } else {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            String message = "Product with id: " + response.getEntity().getId() + " was deleted <br>" + response.getEntity();
            out.print(message);
            out.flush();
        }
    }
}
