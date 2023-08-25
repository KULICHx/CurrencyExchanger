package models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class ExchangeRate {
    private int id;
    @NonNull
    private Currency baseCurrency;
    @NonNull
    private Currency targetCurrency;
    @NonNull
    private double rate;

    public String toString() {
        return "\n\t{\n" +
                "\t\t\"id\": " + id + ", \n" +
                "\t\t\"baseCurrency\": "  + "\"" + baseCurrency + "\", \n" +
                "\t\t\"targetCurrency\": " + "\"" + targetCurrency + "\", \n" +
                "\t\t\"rate\": " + rate + "\n\t}";
    }
}
