package servlets;

import dao.DAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Currency;
import models.ExchangeRate;
import utils.Utils;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "indexExchangeRates", value = "/exchangeRate/*")
public class ExchangeRateServlet extends HttpServlet {
    private dao.DAO DAO;

    public void init() {
        DAO = new DAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String currenciesCodes = request.getPathInfo().replaceFirst("/", "").toUpperCase();
        ExchangeRate exchangeRate = DAO.findExchangeRateByCodes(
                currenciesCodes.substring(0, 3), currenciesCodes.substring(3, 6));
        PrintWriter pw = response.getWriter();
        pw.write(String.valueOf(exchangeRate));
    }
}