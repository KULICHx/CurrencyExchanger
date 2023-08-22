package servlets;

import dao.DAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Currency;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "indexCurrencyServlet", value = "/currency/*")
public class CurrencyServlet extends HttpServlet{
    private DAO DAO;
    public void init()
    {
        DAO = new DAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String currencyCode = request.getPathInfo().replaceFirst("/", "").toUpperCase();
        Currency currency = DAO.selectCurrency(currencyCode);
        PrintWriter pw = response.getWriter();
        pw.write(String.valueOf(currency));
    }
}
