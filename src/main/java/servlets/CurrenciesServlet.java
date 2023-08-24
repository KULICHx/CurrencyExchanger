package servlets;

import dao.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Currency;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "indexCurrenciesServlet", value = "/currencies")
public class CurrenciesServlet extends HttpServlet{
    private DAO DAO;
    public void init()
    {
        DAO = new DAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException{
        List<Currency> listCurrency = DAO.findAllCurrency();
        PrintWriter pw = response.getWriter();
        pw.write(String.valueOf(listCurrency));
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        String fullName = request.getParameter("fullName");
        String sign = request.getParameter("sign");
        Currency newCurrency = new Currency(code, fullName, sign);
        DAO.insertCurrency(newCurrency);
        PrintWriter pw = response.getWriter();
        pw.write(String.valueOf(newCurrency));
    }
}
