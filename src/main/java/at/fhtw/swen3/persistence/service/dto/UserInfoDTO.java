package at.fhtw.swen3.persistence.service.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class UserInfoDTO {
    private int id;
    private String username;
    private String password;
}
