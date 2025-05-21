package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import static beans.MBeanManager.getAttemptsMBean;
import static beans.MBeanManager.getMissRatioMBean;

@WebServlet("/my_metric")
public class Metrics extends HttpServlet {

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
        resp.setContentType("text/plain");

        StringBuilder metrics = new StringBuilder();
//        metrics.append("Miss_ratio_total_attempts ").append(getHitRatioMBean().getTotalAttempts()).append("\n");
        metrics.append("Miss_ratio_miss_ratio ").append(getMissRatioMBean().getMissRatio()).append("\n");

        metrics.append("Attempt_total_attempts ").append(getAttemptsMBean().getTotalAttempts()).append("\n");
        metrics.append("Attempt_total_misses ").append(getAttemptsMBean().getTotalMisses()).append("\n");

        resp.getWriter().write(metrics.toString());
    }
}