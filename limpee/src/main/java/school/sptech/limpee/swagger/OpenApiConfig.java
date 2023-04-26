package school.sptech.limpee.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Documentação de Swagger",
                description ="Implementação da API do projeto Limpee\n\n" +
                        "\nIntegrantes:\n" +
                        "\nDavid Lucca Campos da Silva,\n" +
                        "\nHenrique Yuzo Takahashi,\n" +
                        "\nGustavo Henrique Santos Gonzaga Bolanho,\n" +
                        "\nMaciel Victor Silva de Freitas,\n" +
                        "\nMatheus da Silva Rosa,\n" +
                        "\nMatheus dos Santos Felix",
                contact = @Contact(
                        name = "Limpee",
                        url = "https://github.com/limpee"
                ),
                version = "2.4.1"
        )
)

@SecurityScheme(
        name = "Bearer", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT"
)

public class OpenApiConfig {
}


