package nghiem.learning.web;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("dashboard.backend")
public class DashboardAppViewConfiguration {

    private String protocol;
    private String host;
    private String port;
}
