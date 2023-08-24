package servlets;

import dao.DAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.ExchangeRate;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "indexExchangeRates", value = "/exchangeRates")
public class ExchangeRatesServlet extends HttpServlet {
    private dao.DAO DAO;
    public void init()
    {
        DAO = new DAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException{
        List<ExchangeRate> listExchangeRate = DAO.findAllExchangeRate();
        PrintWriter pw = response.getWriter();
        pw.write(String.valueOf(listExchangeRate));
    }
}
