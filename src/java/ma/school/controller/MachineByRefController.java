package ma.school.controller;

import com.google.gson.Gson;
import ma.school.beans.Machine;
import ma.school.service.MachineService;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MachineByRefController", urlPatterns = {"/MachineByRefController"})
public class MachineByRefController extends HttpServlet {

    MachineService mms = new MachineService();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String reference = request.getParameter("reference");
        response.setContentType("application/json;charset=UTF-8");

        List<Machine> machines = mms.findByRef(reference);
        String jsonResult = new Gson().toJson(machines);
        response.getWriter().write(jsonResult);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "MachineByRefController Servlet";
    }
}
