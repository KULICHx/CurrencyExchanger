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

@WebServlet(name = "indexExchange", value = "/exchange")
public class Exchange extends HttpServlet {
    private dao.DAO DAO;

    public void init() {
        DAO = new DAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String from = request.getParameter("from");
        String to = request.getParameter("to");
        double amount = Double.parseDouble(request.getParameter("amount"));

        double rate = getRate(from, to);

        Currency baseCurrency = DAO.findCurrencyByCode(from);
        Currency targetCurrency = DAO.findCurrencyByCode(to);

        PrintWriter pw = response.getWriter();
        pw.write("\t\t\"baseCurrency:\"" + baseCurrency +
                "\n\t\t\"TargetCurrency:\"" + targetCurrency +
                "\n\t\t\"rate\" = "+rate+
                "\n\t\t\"amount\""+amount +
                "\n\t\t\"convertedAmount\""+rate*amount);
    }


    private double getRate(String from, String to) {
        ExchangeRate exchangeRate = DAO.findExchangeRateByCodes(from, to);

        if (exchangeRate != null)
            return exchangeRate.getRate();

        ExchangeRate reverseExchangeRate = DAO.findExchangeRateByCodes(to, from);

        if (reverseExchangeRate != null)
            return reverseExchangeRate.getRate();

        ExchangeRate exchangeRateUSD_A = DAO.findExchangeRateByCodes("USD", from);
        ExchangeRate exchangeRateUSD_B = DAO.findExchangeRateByCodes("USD", to);

        if (exchangeRateUSD_A != null && exchangeRateUSD_B != null) {
            return exchangeRateUSD_A.getRate() / (exchangeRateUSD_B.getRate());
        }

        return 0;
    }
}
