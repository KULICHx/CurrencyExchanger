package servlets;

import dao.DAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Currency;
import models.ExchangeRate;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "indexExchangeRate", value = "/exchangeRates")
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String baseCurrencyCode = request.getParameter("baseCurrencyCode");
        String targetCurrencyCode = request.getParameter("targetCurrencyCode");

        Currency baseCurrency = DAO.findCurrencyByCode(baseCurrencyCode);
        Currency targetCurrency = DAO.findCurrencyByCode(targetCurrencyCode);
        double rate = Double.parseDouble(request.getParameter("rate"));

        ExchangeRate exchangeRate = new ExchangeRate(baseCurrency,targetCurrency,rate);
        DAO.insertExchangeRate(exchangeRate);

        PrintWriter pw = response.getWriter();
        pw.write(String.valueOf(exchangeRate));
    }
}
