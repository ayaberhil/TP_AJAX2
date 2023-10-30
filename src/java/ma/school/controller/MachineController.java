

package ma.school.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ma.school.beans.Machine;
import ma.school.beans.Marque;
import ma.school.service.MachineService;
import ma.school.service.MarqueService;


@WebServlet(name = "MachineController", urlPatterns = {"/MachineController"})
public class MachineController extends HttpServlet {

    String op = null;

    @Override
    public void init() throws ServletException {
        super.init();
        op = null;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        op = request.getParameter("op");
        MachineService mms = new MachineService();
        MarqueService ms = new MarqueService();

        if (op != null) {
            if (op.equals("Envoyer")) {

                String ref = request.getParameter("ref");
                double prix = Double.parseDouble(request.getParameter("prix"));
                Marque marque = ms.findById(Integer.parseInt(request.getParameter("marque")));
                Date dateAchat = new Date(request.getParameter("dateAchat").replace("-", "/"));
                
                mms.create(new Machine(ref, dateAchat, prix, marque));

                response.sendRedirect("machineForm.jsp");
            } else if (op.equals("delete")) {
                int id = Integer.parseInt(request.getParameter("id"));
                Machine machine = mms.findById(id);

                if (machine != null) {
                    mms.delete(machine);
                } else {

                }

                response.sendRedirect("machineForm.jsp");
            } else if (op.equals("update")) {
                int id = Integer.parseInt(request.getParameter("id"));
                Machine m = mms.findById(id);

                if (m != null) {
                    String ref = request.getParameter("ref");
                    double prix = Double.parseDouble(request.getParameter("prix"));
                    String date = request.getParameter("dateAchat");
                    Date dateAchat = null;
                    
                    try {
                        dateAchat = new Date(date.replace("-", "/"));
                    } catch (IllegalArgumentException ell) {
                        ell.printStackTrace();
                    }
                    
                    Marque marque = ms.findById(Integer.parseInt(request.getParameter("marque")));
                  
                    m.setReference(ref);
                    m.setPrix(prix);
                    m.setDateAchat(dateAchat);
                    m.setMarque(marque);
                    
                    mms.update(m);
                } 
                response.sendRedirect("machineForm.jsp");
            }
        } 
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
