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
    private int rate;
}
