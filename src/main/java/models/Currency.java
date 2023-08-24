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

    @Override
    public String toString() {
        return "\n\t\t{\n" +
                "\t\t\t\"id\": " + id + ", \n" +
                "\t\t\t\"name\": " + "\"" + fullName + "\", \n" +
                "\t\t\t\"code\": " + "\"" + code + "\", \n" +
                "\t\t\t\"sign\": " + "\"" + sign + "\"\n\t\t}";
    }
}
