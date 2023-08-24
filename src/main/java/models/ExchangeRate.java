package models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class ExchangeRate {
    private int id;

    private Currency baseCurrency;

    private Currency targetCurrency;
    @NonNull
    private double rate;

}
