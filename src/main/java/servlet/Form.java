package servlet;

import beans.MBeanManager;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ejb.FormEJB;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Points;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/form")
public class Form extends HttpServlet {

    public Form() {
        MBeanManager.registerBean(MBeanManager.getMissRatioMBean(), "MissRatio");
        MBeanManager.registerBean(MBeanManager.getAttemptsMBean(), "Attempts");
    }

    @EJB
    private FormEJB formEJB;

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        String idStr = req.getParameter("id");
        String firstStr = req.getParameter("first");
        String rowsStr = req.getParameter("rows");
        long id = Long.parseLong(idStr);
        int first = Integer.parseInt(firstStr);
        int rows = Integer.parseInt(rowsStr);
        List<Points> points = formEJB.getAllByUser(id, first, rows);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(points);
        resp.setContentType("text/plain");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        StringBuilder payloadJson = new StringBuilder();
        try(BufferedReader reader = req.getReader()){
            String line;
            while ((line = reader.readLine()) != null){
                payloadJson.append(line);
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(payloadJson.toString());
        long id = jsonNode.get("id").asLong();
        double x = jsonNode.get("x").asDouble();
        double y = jsonNode.get("y").asDouble();
        int r = jsonNode.get("r").asInt();
        Points result = formEJB.addNewElement(x, y, r, id);
        mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(result);
        resp.setContentType("text/plain");
        resp.getWriter().write(json);
    }
}