package models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Currency {
    private int id;
    @NonNull
    private String code;
    @NonNull
    private String fullName;
    @NonNull
    private String sign;
}
