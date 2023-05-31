package school.sptech.limpee.api.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import school.sptech.limpee.api.configuration.security.jwt.GerenciadorTokenJwt;
import school.sptech.limpee.service.usuario.autenticacao.AutenticacaoService;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguracao {

    private static final String ORIGENS_PERMTIDAS = "*";
    @Autowired
    private AutenticacaoEntryPoint autenticacaoJwtEntryPoint;

    @Autowired
    private AutenticacaoService autenticacaoService;

    private static final AntPathRequestMatcher[] URLS_PERMITIDAS = {
            new AntPathRequestMatcher("/h2-console/**"),
            new AntPathRequestMatcher("/usuarios/login"),
            new AntPathRequestMatcher("/usuarios"),
            new AntPathRequestMatcher("/swagger-ui/**"),
            new AntPathRequestMatcher("/swagger-ui.html"),
            new AntPathRequestMatcher("/swagger-resources"),
            new AntPathRequestMatcher("/swagger-resources/**"),
            new AntPathRequestMatcher("/configuration/ui"),
            new AntPathRequestMatcher("/configuration/security"),
            new AntPathRequestMatcher("/api/public/**"),
            new AntPathRequestMatcher("/api/public/authenticate"),
            new AntPathRequestMatcher("/webjars/**"),
            new AntPathRequestMatcher("/v3/api-docs/**"),
            new AntPathRequestMatcher("/actuator/*"),
            new AntPathRequestMatcher("/error/**"),
            new AntPathRequestMatcher("/administradores/lista"),
            new AntPathRequestMatcher("/imagens"),
            new AntPathRequestMatcher("/imagens/foto/*"),
            new AntPathRequestMatcher("/documentos"),
            new AntPathRequestMatcher("/documentos/*"),
            new AntPathRequestMatcher("/csvs"),
            new AntPathRequestMatcher("/csvs/export"),
            new AntPathRequestMatcher("/txts/import"),
            new AntPathRequestMatcher("/txts/export"),
            new AntPathRequestMatcher("/imagens"),
            new AntPathRequestMatcher("/imagens/*"),
            new AntPathRequestMatcher("/imagens/**"),




            //  new AntPathRequestMatcher("/registros"),
            //  new AntPathRequestMatcher("/registros/{id}"),
    };

//    O método "public SecurityFilterChain
//    filterChain(HttpSecurity http)" é responsável por configurar o CORS e os endpoints que não
//    exigem autenticação, incluindo o endpoint de login.
//    SecurityFilterChain é uma interface do Spring Security que define um filtro de segurança para
//    uma solicitação HTTP. Ela é responsável por garantir que as solicitações sejam autorizadas e
//    autenticadas corretamente antes de permitir o acesso ao endpoint. A interface define um
//            único método, "doFilter", que processa a solicitação e a encaminha para o próximo filtro na
//    cadeia. A configuração da cadeia de filtros é feita por meio do método "filterChain" em uma
//    classe que implementa a interface "WebSecurityConfigurerAdapter"

//    O método antMatchers() permite especificar os padrões de URL e as regras de
//    autorização que se aplicam a esses padrões. No nosso caso, não queremos restringir o
//    acesso para os endpoint de /usuarios/login e /h2-console/ com tudo que vier depois
//do /


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.headers()
                .frameOptions().disable()
                .and()
                .cors()
                .configurationSource(request -> buildCorsConfiguration())
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests(authorize -> authorize.requestMatchers(URLS_PERMITIDAS)
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .exceptionHandling()
                .authenticationEntryPoint(autenticacaoJwtEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtAuthenticationFilterBean(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
//
//    O método authenticationManager cria um gerenciador de autenticação que será usado na aplicação.
//    O gerenciador é configurado com um provedor de autenticação personalizado, que é uma classe chamada
//    AutenticacaoProvider. Esta classe é responsável por verificar as credenciais de autenticação do usuário
//    e determinar se ele está autorizado a acessar a aplicação.

    @Bean
    public AuthenticationManager authManager(HttpSecurity  http) throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(new AutenticacaoProvider(autenticacaoService, passwordEncoder()));
        return authenticationManagerBuilder.build();
    }


    @Bean
    public AutenticacaoEntryPoint jwtAuthenticationEntryPointBean(){
        return new AutenticacaoEntryPoint();
    }
//
//    O método jwtAuthenticationFilterBean() retorna um objeto da classe AutenticacaoFilter, que é o
//    filtro usado para verificar a autenticação do usuário em cada requisição. Esse filtro é injetado
//    na cadeia de filtros do Spring Security por meio do método addFilterBefore() na classe SecurityFilterChain.

    @Bean
    public AutenticacaoFilter jwtAuthenticationFilterBean(){
        return new AutenticacaoFilter(autenticacaoService, jwtAuthenticationUtilBean());
    }

    //    O método jwtAuthenticationUtilBean() retorna um objeto da classe GerenciadorTokenJwt, que é
//    usado para gerenciar os tokens JWT (JSON Web Token) usados na autenticação do usuário.
    @Bean
    public GerenciadorTokenJwt jwtAuthenticationUtilBean(){
        return new GerenciadorTokenJwt();
    }

//    O método passwordEncoder() retorna um objeto da classe BCryptPasswordEncoder, que é usado para
//    codificar a senha do usuário antes de armazená-la no banco de dados. Esse é um recurso de segurança
//    para garantir que a senha do usuário não seja armazenada em texto puro no banco de dados.

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    private CorsConfiguration buildCorsConfiguration() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns(Collections.singletonList(ORIGENS_PERMTIDAS));
        configuration.setAllowedMethods(
                Arrays.asList(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.DELETE.name())
        );
        configuration.setAllowedHeaders(Arrays.asList(HttpHeaders.CONTENT_TYPE, HttpHeaders.AUTHORIZATION));
        return configuration;
    }
}
