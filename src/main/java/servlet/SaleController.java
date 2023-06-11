package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Sale;
import model.modelDTO.GeneralDTO;
import model.modelDTO.saleDTO.SaleDeleteByIdRequestDTO;
import model.modelDTO.saleDTO.SaleIdRequestDTO;
import model.modelDTO.saleDTO.SaleSaveRequestDTO;
import model.modelDTO.saleDTO.SaleUpdateRequestDTO;
import service.SaleService;
import utils.Utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet("sale")
public class SaleController extends HttpServlet {
    private final ObjectMapper jacksonMapper = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
            .findAndRegisterModules();
    private final SaleService saleService = new SaleService();

    public SaleController() {
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {         //make a sale
        PrintWriter out = resp.getWriter();
        String rb = req.getReader().lines().collect(Collectors.joining());
        final SaleSaveRequestDTO request = jacksonMapper.readValue(rb, SaleSaveRequestDTO.class);
        GeneralDTO<Sale> response = saleService.save(request);

        if (response.getEntity() == null) {
            Utils.returnNullResponse(resp, out, response.getMessage());
        } else {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            String message = "Sale: <br>" + response.getEntity();
            out.print(message);
            out.flush();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {       //get sale by id or list
        PrintWriter out = resp.getWriter();
        String rb = req.getReader().lines().collect(Collectors.joining());
        final SaleIdRequestDTO request = jacksonMapper.readValue(rb, SaleIdRequestDTO.class);

        if (request.getSaleId() == 0) {
            GeneralDTO<Sale> responseAll = saleService.getAll();
            String json = jacksonMapper.writeValueAsString(responseAll.getEntityList());
            Utils.sendResponse(resp, json);
        } else {
            GeneralDTO<Sale> responseById = saleService.getById(request.getSaleId());
            if (responseById.getEntity() == null) {
                Utils.returnNullResponse(resp, out, responseById.getMessage());
            } else {
                String json = jacksonMapper.writeValueAsString(responseById.getEntity());
                Utils.sendResponse(resp, json);
            }
        }
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {       //update sale
        PrintWriter out = resp.getWriter();
        String rb = req.getReader().lines().collect(Collectors.joining());
        final SaleUpdateRequestDTO request = jacksonMapper.readValue(rb, SaleUpdateRequestDTO.class);
        GeneralDTO<Sale> response = saleService.update(request);

        if (response.getEntity() == null) {
            Utils.returnNullResponse(resp, out, response.getMessage());
        } else {
            String message = "Sale updated: " + response.getEntity();
            Utils.sendResponse(resp, message);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {    //delete sale by id
        PrintWriter out = resp.getWriter();
        String rb = req.getReader().lines().collect(Collectors.joining());
        final SaleDeleteByIdRequestDTO request =jacksonMapper.readValue(rb, SaleDeleteByIdRequestDTO.class);
        GeneralDTO<Sale> response = saleService.deleteById(request);

        if (response.getEntity() == null) {
            Utils.returnNullResponse(resp, out, response.getMessage());
        } else {
            String message = "Sale with id: " + response.getEntity().getId() + " was deleted <br>" + response.getEntity();
            Utils.sendResponse(resp, message);
        }
    }
}
